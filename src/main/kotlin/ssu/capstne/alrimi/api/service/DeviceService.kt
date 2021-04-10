package ssu.capstne.alrimi.api.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ssu.capstne.alrimi.api.controller.dtos.token.TokenDto
import ssu.capstne.alrimi.api.model.Device
import ssu.capstne.alrimi.api.repository.DeviceRepository

@Service
@Transactional
class DeviceService(private val deviceRepository: DeviceRepository) {

    fun saveToken(dto: TokenDto): Device {
        return deviceRepository.save(Device(dto.token))
    }

    fun deleteToken(dto: TokenDto) {
        val device = deviceRepository.findById(dto.token).orElseThrow()
        return deviceRepository.delete(device)
    }
}