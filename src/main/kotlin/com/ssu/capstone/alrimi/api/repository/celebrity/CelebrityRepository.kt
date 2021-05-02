package com.ssu.capstone.alrimi.api.repository.celebrity

import com.ssu.capstone.alrimi.api.model.celebrity.Celebrity
import com.ssu.capstone.alrimi.api.repository.celebrity.projection.CelebrityInfoTransfer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
interface CelebrityRepository : JpaRepository<Celebrity, Long> {

    @Query("SELECT c From Celebrity c WHERE c.company.id =:companyId AND parent_id IS NULL")
    fun findAllByCompany_IdAndParentIdIsNull(companyId: Long): List<CelebrityInfoTransfer>

    @Modifying
    @Transactional
    @Query("INSERT INTO celebrity(id,company_id,parent_id,name) VALUES(:id,:companyId,:parentId,:name)",nativeQuery = true)
    fun  save(id: Long, companyId: Long, parentId: Long?, name: String)
}