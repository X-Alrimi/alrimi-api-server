package com.ssu.capstone.alrimi.api.service.company.exception

import com.ssu.capstone.alrimi.core.execption.ApiException

class CompanyNotFoundException() : ApiException("Company Not Found") {
}