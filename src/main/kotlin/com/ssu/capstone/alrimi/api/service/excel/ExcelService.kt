package com.ssu.capstone.alrimi.api.service.excel

import com.ssu.capstone.alrimi.api.controller.dtos.news.FilterNewsDto
import java.io.File

interface ExcelService {
    fun inputCompany(file: File)
    fun inputCelebrity(file: File)
    fun inputNews(file: File): MutableList<FilterNewsDto>
    fun writeCSV(list: MutableList<FilterNewsDto>)
    fun filterCSV(list: MutableList<FilterNewsDto>): List<FilterNewsDto>
}