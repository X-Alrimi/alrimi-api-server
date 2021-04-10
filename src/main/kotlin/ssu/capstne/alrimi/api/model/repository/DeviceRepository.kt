package ssu.capstne.alrimi.api.model.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ssu.capstne.alrimi.api.model.entity.Device

@Repository
interface DeviceRepository : JpaRepository<Device, String> {
    override fun findAll(): List<Device>
}