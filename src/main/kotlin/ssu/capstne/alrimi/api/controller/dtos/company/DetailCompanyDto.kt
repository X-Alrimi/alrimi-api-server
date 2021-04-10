package ssu.capstne.alrimi.api.controller.dtos.company

import ssu.capstne.alrimi.api.repository.celebrity.projection.CelebrityInfoTransfer

class DetailCompanyDto(val company: SimpleCompanyDto, val celebrities: List<CelebrityInfoTransfer>)