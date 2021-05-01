package com.ssu.capstone.alrimi.core.event

import com.ssu.capstone.alrimi.api.service.excel.ExcelService
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.event.ApplicationStartedEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import java.io.File

@Component
class CreateEventHandler(
    private val excelService: ExcelService,
    @Value("\${spring.jpa.hibernate.ddl-auto}")
    val type: String
) {

    @EventListener
    fun applicationStartEventListener(event: ApplicationStartedEvent) {
        println("EventStart")
        if (type == "create") {
            val company: File = File("src/main/kotlin/com/ssu/capstone/alrimi/excel/company")
            val celebrity: File = File("src/main/kotlin/com/ssu/capstone/alrimi/excel/celebrity")

            for (file in company.listFiles()!!)
                excelService.inputCompany(file)
        }
    }
}