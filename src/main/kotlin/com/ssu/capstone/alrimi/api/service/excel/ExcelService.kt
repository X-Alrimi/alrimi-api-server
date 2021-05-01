package com.ssu.capstone.alrimi.api.service.excel

import java.io.File

interface ExcelService {
    fun inputCompany(file: File)
    fun inputCelebrity(file: File)
}