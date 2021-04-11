package ssu.capstne.alrimi.api.repository.device

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ssu.capstne.alrimi.api.model.device.Device

@Repository
interface DeviceRepository : JpaRepository<Device, String> {
    override fun findAll(): List<Device>
    fun deleteByToken(token: String): Boolean
}