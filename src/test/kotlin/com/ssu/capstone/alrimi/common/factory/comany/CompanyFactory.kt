package com.ssu.capstone.alrimi.common.factory.comany

import com.ssu.capstone.alrimi.api.model.company.Company
import java.util.*

object CompanyFactory {

    fun createMockCompany(id: Long): Company {
        return Company(id, "MockCompany")
    }

    fun returnOptionalMockCompany(): Optional<Company> {
        return Optional.of(createMockCompany(1))
    }

    fun returnMockCompanyList(): List<Company> {
        return listOf(createMockCompany(1), createMockCompany(2))
    }
}