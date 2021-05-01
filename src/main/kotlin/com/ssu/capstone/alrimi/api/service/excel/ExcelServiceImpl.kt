package com.ssu.capstone.alrimi.api.service.excel


import com.ssu.capstone.alrimi.api.model.company.Company
import com.ssu.capstone.alrimi.api.repository.celebrity.CelebrityRepository
import com.ssu.capstone.alrimi.api.repository.company.CompanyRepository
import org.apache.commons.io.FilenameUtils
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.io.File
import java.io.FileInputStream
import java.io.InputStream

@Service
@Transactional
class ExcelServiceImpl(
    private val celebrityRepository: CelebrityRepository,
    private val companyRepository: CompanyRepository
) : ExcelService {
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

    }
}