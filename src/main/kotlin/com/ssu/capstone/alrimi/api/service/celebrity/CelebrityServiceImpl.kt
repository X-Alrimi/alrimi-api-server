package com.ssu.capstone.alrimi.api.service.celebrity

import com.ssu.capstone.alrimi.api.repository.celebrity.CelebrityRepository
import com.ssu.capstone.alrimi.api.repository.celebrity.projection.CelebrityInfoTransfer
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
@Transactional
class CelebrityServiceImpl(
    private val celebrityRepository: CelebrityRepository
) : CelebrityService {

    @Transactional(readOnly = true)
    @Cacheable(cacheNames = ["celebrities"], key = "#companyId")
    override fun getCelebritiesList(companyId: Long): List<CelebrityInfoTransfer> {
        return celebrityRepository.findAllByCompany_IdAndParentIdIsNull(companyId)
    }
}