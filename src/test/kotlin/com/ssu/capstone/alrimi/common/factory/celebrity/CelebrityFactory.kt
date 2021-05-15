package com.ssu.capstone.alrimi.common.factory.celebrity

import com.ssu.capstone.alrimi.api.model.celebrity.Celebrity
import com.ssu.capstone.alrimi.api.repository.celebrity.projection.CelebrityInfoTransfer
import com.ssu.capstone.alrimi.common.factory.comany.CompanyFactory

object CelebrityFactory {

    private fun createMockCelebrity(keyword: String?): Celebrity {
        return if (keyword == null)
            Celebrity(1, "MockCelebrity", CompanyFactory.createMockCompany(1), mutableListOf())
        else
            Celebrity(1, keyword, CompanyFactory.createMockCompany(1), mutableListOf())
    }

    fun findAllByCompany_IdAndParentIdIsNullMockingFunction(): List<Celebrity> {
        val parent1 = createMockCelebrity("parent1")
        val child1 = createMockCelebrity("child1")
        val child2 = createMockCelebrity("child2")

        val parent2 = createMockCelebrity("parent2")
        val child3 = createMockCelebrity("child3")
        val child4 = createMockCelebrity("child4")

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