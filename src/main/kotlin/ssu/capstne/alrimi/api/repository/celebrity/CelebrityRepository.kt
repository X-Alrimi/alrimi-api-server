package ssu.capstne.alrimi.api.repository.celebrity

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import ssu.capstne.alrimi.api.model.Celebrity
import ssu.capstne.alrimi.api.repository.celebrity.projection.CelebrityInfoTransfer

interface CelebrityRepository : JpaRepository<Celebrity, Long> {
    @Query("SELECT c From Celebrity c WHERE c.company =:company AND parent_id IS NULL")
    fun findAllByCompany_IdAndParentIdIsNull(companyId: Long): List<CelebrityInfoTransfer>
}