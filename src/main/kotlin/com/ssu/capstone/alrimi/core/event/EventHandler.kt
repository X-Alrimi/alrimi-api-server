package com.ssu.capstone.alrimi.core.event

import com.ssu.capstone.alrimi.api.service.celebrity.CelebrityService
import com.ssu.capstone.alrimi.api.service.company.CompanyService
import com.ssu.capstone.alrimi.api.service.device.DeviceService
import com.ssu.capstone.alrimi.api.service.excel.ExcelService
import com.ssu.capstone.alrimi.core.util.ngram.CustomNgramAnalyzer
import org.apache.lucene.analysis.ko.dict.UserDictionary
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.event.ApplicationStartedEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import java.io.File
import java.io.StringReader

@Component
class EventHandler(
    private val excelService: ExcelService,
    private val celebrityService: CelebrityService,
    private val companyService: CompanyService,
    private val deviceService: DeviceService,
    @Value("\${spring.jpa.hibernate.ddl-auto}")
    val type: String
) {
    @EventListener
    fun applicationStartEventListener(event: ApplicationStartedEvent) {
        if (type == "create") {
            val company: File = File("src/main/kotlin/com/ssu/capstone/alrimi/excel/company")
            val celebrity: File = File("src/main/kotlin/com/ssu/capstone/alrimi/excel/celebrity")

            for (file in company.listFiles()!!)
                excelService.inputCompany(file)
            for (file in celebrity.listFiles()!!)
                excelService.inputCelebrity(file)
        }
        CustomNgramAnalyzer.createInstance(makeUserDictionary())
    }

    @EventListener
    fun alarmEventListener(event: AlarmEvent) {
        deviceService.sendAlarm(event)
    }

    private fun makeUserDictionary(): UserDictionary {
        val sb: StringBuilder = StringBuilder()
        companyService.getSimpleCompanyList().forEach { company ->
            celebrityService.getCelebritiesList(company.id).forEach { celebrity ->
                sb.append(celebrity.name).append("\n")
                celebrity.member.forEach { member ->
                    sb.append(member.name).append("\n")
                }
            }
        }
        return UserDictionary.open(StringReader(sb.toString()))
    }
}