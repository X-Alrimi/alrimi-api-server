package com.ssu.capstone.alrimi.common.factory.celebrity

import com.ssu.capstone.alrimi.api.model.celebrity.Celebrity
import com.ssu.capstone.alrimi.api.repository.celebrity.projection.CelebrityInfoTransfer

class MockCelebrityInfoTransfer(
    override val id: Long,
    override val name: String,
    override val member: List<CelebrityInfoTransfer>
) : CelebrityInfoTransfer {
}