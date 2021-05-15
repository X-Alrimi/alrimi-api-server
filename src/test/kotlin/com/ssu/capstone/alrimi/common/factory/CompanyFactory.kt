package com.ssu.capstone.alrimi.common.factory

import com.ssu.capstone.alrimi.api.model.company.Company
import java.util.*

object CompanyFactory {

    fun createMockCompany(): Company {
        return Company(1, "MockCompany")
    }

    fun returnOptionalMockCompany(): Optional<Company> {
        return Optional.of(createMockCompany())
    }
}