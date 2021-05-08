package com.ssu.capstone.alrimi.api.controller.dtos.company

import com.ssu.capstone.alrimi.api.repository.celebrity.projection.CelebrityInfoTransfer


class DetailCompanyDto(
    val company: SimpleCompanyDto,
    val celebrities: List<CelebrityInfoTransfer>
) {

}