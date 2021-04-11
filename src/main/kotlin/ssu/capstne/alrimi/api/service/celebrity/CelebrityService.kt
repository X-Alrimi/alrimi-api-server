package ssu.capstne.alrimi.api.service.celebrity

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ssu.capstne.alrimi.api.repository.company.CompanyRepository
import ssu.capstne.alrimi.api.repository.celebrity.CelebrityRepository
import ssu.capstne.alrimi.api.repository.celebrity.projection.CelebrityInfoTransfer

@Service
@Transactional
class CelebrityService(
    private val companyRepository: CompanyRepository,
    private val celebrityRepository: CelebrityRepository
) {

    @Transactional(readOnly = true)
    fun getCelebritiesList(companyId: Long): List<CelebrityInfoTransfer> {
        return celebrityRepository.findAllByCompany_IdAndParentIdIsNull(companyId)
    }
}