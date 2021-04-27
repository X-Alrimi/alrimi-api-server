package com.ssu.capstone.alrimi.api.service.celebrity

import com.ssu.capstone.alrimi.api.repository.celebrity.projection.CelebrityInfoTransfer

interface CelebrityService {
    fun getCelebritiesList(companyId: Long): List<CelebrityInfoTransfer>
}