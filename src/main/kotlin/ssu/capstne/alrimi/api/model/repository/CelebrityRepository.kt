package ssu.capstne.alrimi.api.model.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import ssu.capstne.alrimi.api.model.entity.Celebrity
import ssu.capstne.alrimi.api.model.entity.Company

interface CelebrityRepository : JpaRepository<Celebrity, Long> {
    @Query("SELECT c From Celebrity c WHERE c.company =:company AND parent_id IS NULL")
    fun findAllByCompanyAndParentIdIsNull(company: Company): List<Celebrity>
}