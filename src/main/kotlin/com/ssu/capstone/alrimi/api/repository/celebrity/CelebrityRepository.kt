package com.ssu.capstone.alrimi.api.repository.celebrity

import com.ssu.capstone.alrimi.api.model.celebrity.Celebrity
import com.ssu.capstone.alrimi.api.repository.celebrity.projection.CelebrityInfoTransfer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface CelebrityRepository : JpaRepository<Celebrity, Long> {
    @Query("SELECT c From Celebrity c WHERE c.company.id =:companyId AND parent_id IS NULL")
    fun findAllByCompany_IdAndParentIdIsNull(companyId: Long): List<CelebrityInfoTransfer>
}