package com.ssu.capstone.alrimi.common

import com.ssu.capstone.alrimi.api.repository.celebrity.CelebrityRepository
import com.ssu.capstone.alrimi.api.repository.company.CompanyRepository
import com.ssu.capstone.alrimi.api.repository.device.DeviceRepository
import com.ssu.capstone.alrimi.api.repository.news.NewsRepository
import com.ssu.capstone.alrimi.api.service.celebrity.CelebrityService
import com.ssu.capstone.alrimi.api.service.celebrity.CelebrityServiceImpl
import com.ssu.capstone.alrimi.api.service.company.CompanyService
import com.ssu.capstone.alrimi.api.service.company.CompanyServiceImpl
import com.ssu.capstone.alrimi.api.service.device.DeviceService
import com.ssu.capstone.alrimi.api.service.device.DeviceServiceImpl
import com.ssu.capstone.alrimi.api.service.news.NewsService
import com.ssu.capstone.alrimi.api.service.news.NewsServiceImpl
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
open class UnitTestBase {

    @Mock
    lateinit var newsRepository: NewsRepository

    @Mock
    lateinit var companyRepository: CompanyRepository

    @Mock
    lateinit var celebrityRepository: CelebrityRepository

    @Mock
    lateinit var deviceRepository: DeviceRepository


    @InjectMocks
    lateinit var celebrityService: CelebrityServiceImpl

    @InjectMocks
    lateinit var companyService: CompanyServiceImpl

    @InjectMocks
    lateinit var deviceService: DeviceServiceImpl

    @InjectMocks
    lateinit var newsService: NewsServiceImpl

}