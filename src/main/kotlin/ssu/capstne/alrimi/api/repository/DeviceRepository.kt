package ssu.capstne.alrimi.api.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ssu.capstne.alrimi.api.model.Device

@Repository
interface DeviceRepository : JpaRepository<Device, String> {
    override fun findAll(): List<Device>
    fun deleteByToken(token: String): Boolean
}