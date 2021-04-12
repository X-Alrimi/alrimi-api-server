package com.ssu.capstone.alrimi.api.repository.device

import com.ssu.capstone.alrimi.api.model.device.Device
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DeviceRepository : JpaRepository<Device, String> {
    override fun findAll(): List<Device>
    fun deleteByToken(token: String): Boolean
}