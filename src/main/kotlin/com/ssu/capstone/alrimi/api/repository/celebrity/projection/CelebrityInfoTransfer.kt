package com.ssu.capstone.alrimi.api.repository.celebrity.projection

interface CelebrityInfoTransfer {
    val name: String
    val birth: String?
    val member: List<CelebrityInfoTransfer>
}