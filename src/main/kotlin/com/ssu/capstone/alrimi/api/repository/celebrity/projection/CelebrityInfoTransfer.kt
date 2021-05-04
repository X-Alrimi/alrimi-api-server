package com.ssu.capstone.alrimi.api.repository.celebrity.projection

interface CelebrityInfoTransfer {
    val id: Long
    val name: String
    val member: List<CelebrityInfoTransfer>
}