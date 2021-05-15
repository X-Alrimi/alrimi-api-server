package com.ssu.capstone.alrimi.common.factory.celebrity

import com.ssu.capstone.alrimi.api.model.celebrity.Celebrity
import com.ssu.capstone.alrimi.api.repository.celebrity.projection.CelebrityInfoTransfer
import com.ssu.capstone.alrimi.common.factory.comany.CompanyFactory
import java.util.*

object CelebrityFactory {

    fun createMockCelebrity(id: Long, keyword: String?): Celebrity {
        return if (keyword == null)
            Celebrity(id, "MockCelebrity", CompanyFactory.createMockCompany(1), mutableListOf())
        else
            Celebrity(id, keyword, CompanyFactory.createMockCompany(1), mutableListOf())
    }

    fun createOptionalMockCelebrity(id: Long, keyword: String?): Optional<Celebrity> {
        return Optional.of(createMockCelebrity(id, keyword))
    }

    fun findAllByCompany_IdAndParentIdIsNullMockingFunction(): List<Celebrity> {
        val parent1 = createMockCelebrity(1, "parent1")
        val child1 = createMockCelebrity(3, "child1")
        val child2 = createMockCelebrity(4, "child2")

        val parent2 = createMockCelebrity(2, "parent2")
        val child3 = createMockCelebrity(5, "child3")
        val child4 = createMockCelebrity(6, "child4")

        parent1.member.addAll(listOf(child1, child2))
        parent2.member.addAll(listOf(child3, child4))

        return listOf(parent1, parent2)
    }

    fun makeMockCelebrityInfoTransfer(celebrities: List<Celebrity>): List<CelebrityInfoTransfer> {
        return celebrities.map {
            MockCelebrityInfoTransfer(it.id!!, it.name, makeMockCelebrityInfoTransfer(it.member))
        }
    }
}