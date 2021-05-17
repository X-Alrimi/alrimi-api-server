package com.ssu.capstone.alrimi.core.execption

enum class ExceptionCode(val code: String, val message: String) {
    COMPANY_001("COMPANY_001", "Company Not Found"),
    CELEBRITY_001("CELEBRITY_001", "Celebrity Not Found"),
    DEVICE_001("DEVICE_001", "Token is Not Exist"),
    SYSTEM_001("SYSTEM_001", "Argument is Not Valid"),
    SYSTEM_002("SYSTEM_002", "Invalid Page Request")
}