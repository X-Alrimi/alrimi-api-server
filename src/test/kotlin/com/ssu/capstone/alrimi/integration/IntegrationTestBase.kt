package com.ssu.capstone.alrimi.integration

import com.ssu.capstone.alrimi.api.repository.celebrity.CelebrityRepository
import com.ssu.capstone.alrimi.api.repository.company.CompanyRepository
import com.ssu.capstone.alrimi.api.repository.device.DeviceRepository
import com.ssu.capstone.alrimi.api.repository.news.NewsRepository
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.transaction.annotation.Transactional

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
class IntegrationTestBase {

    fun <T> any(type: Class<T>): T {
        Mockito.any<T>()
        return null as T
    }

    @Autowired
    protected lateinit var mockMvc: MockMvc

    @Autowired
    protected lateinit var newsRepository: NewsRepository

    @Autowired
    protected lateinit var deviceRepository: DeviceRepository

    @Autowired
    protected lateinit var companyRepository: CompanyRepository

    @Autowired
    protected lateinit var celebrityRepository: CelebrityRepository

}