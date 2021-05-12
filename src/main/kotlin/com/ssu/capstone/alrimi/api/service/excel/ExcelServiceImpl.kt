package com.ssu.capstone.alrimi.api.service.excel


import com.ssu.capstone.alrimi.api.controller.dtos.news.FilterNewsDto
import com.ssu.capstone.alrimi.api.model.company.Company
import com.ssu.capstone.alrimi.api.repository.celebrity.CelebrityRepository
import com.ssu.capstone.alrimi.api.repository.company.CompanyRepository
import com.ssu.capstone.alrimi.core.util.ngram.CustomNgramAnalyzer
import org.apache.commons.io.FilenameUtils
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.io.*
import java.nio.charset.Charset


@Service
class ExcelServiceImpl(
    private val celebrityRepository: CelebrityRepository,
    private val companyRepository: CompanyRepository,
) : ExcelService {

    @Transactional
    override fun inputCompany(file: File) {
        val ext: String = FilenameUtils.getExtension(file.name)

        if (ext != "xlsx")
            throw Exception("Xlsx Input Error")

        val workbook: Workbook = XSSFWorkbook(FileInputStream(file) as InputStream)
        val workSheet: Sheet = workbook.getSheetAt(0)

        for (i in 1 until workSheet.physicalNumberOfRows) {
            val row: Row = workSheet.getRow(i)

            val id: Long = row.getCell(0).numericCellValue.toLong()
            val name: String = row.getCell(1).stringCellValue

            companyRepository.save(Company(id, name))
        }
    }

    override fun inputCelebrity(file: File) {
        val ext: String = FilenameUtils.getExtension(file.name)
        if (ext != "xlsx")
            throw Exception("Xlsx Input Error")
        val workbook: Workbook = XSSFWorkbook(FileInputStream(file) as InputStream)
        val workSheet: Sheet = workbook.getSheetAt(0)
        for (i in 1 until workSheet.physicalNumberOfRows) {
            val row: Row = workSheet.getRow(i)
            val id: Long = row.getCell(0).numericCellValue.toLong()
            val companyId: Long = row.getCell(1).numericCellValue.toLong()
            val groupId = row.getCell(2).numericCellValue.toLong()
            val name: String = row.getCell(3).stringCellValue

            if (groupId == -1L)
                celebrityRepository.save(id, companyId, null, name)
            else
                celebrityRepository.save(id, companyId, groupId, name)
        }
    }

    override fun inputNews(file: File): MutableList<FilterNewsDto> {
        val ext: String = FilenameUtils.getExtension(file.name)
        if (ext != "csv")
            throw Exception("Csv Input Error")

        val br: BufferedReader = file.bufferedReader()
        var line: String?
        var cols: List<String>? = null
        val list: MutableList<FilterNewsDto> = mutableListOf()
        br.readLine()
        do {
            line = br.readLine()
            if (line != null) {
                cols = line.split(",")
                list.add(FilterNewsDto(cols.get(0), cols.get(1), cols.get(2), cols.get(3), cols.get(4)))
            }
        } while (line != null)
        br.close()
        return list;
    }

    override fun writeCSV(list: MutableList<FilterNewsDto>) {
        val writeFile = File("src/main/kotlin/com/ssu/capstone/alrimi/excel/news/result.csv")
        val filteredList = filterCSV(list)
        val bw = FileWriter(writeFile,true)

        filteredList.forEach { row ->
            bw.write(row.anchor)
            bw.write(",")
            bw.write(row.title)
            bw.write(",")
            bw.write(row.title)
            bw.write(",")
            bw.write(row.content)
            bw.write(",")
            bw.write("critical")
            bw.write("\n")
            println("Save!${row.title}")
        }
        bw.flush()
        bw.close()

    }

    override fun filterCSV(list: MutableList<FilterNewsDto>): List<FilterNewsDto> {
        return list.filter { news ->
            CustomNgramAnalyzer.getKeywordNgram(news.content).any { nGram ->
                CustomNgramAnalyzer.keyword.any { criticalWord ->
                    criticalWord == nGram
                }
            }
        }
    }
}