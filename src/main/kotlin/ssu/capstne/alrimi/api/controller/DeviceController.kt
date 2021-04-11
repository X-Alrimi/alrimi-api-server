package ssu.capstne.alrimi.api.controller

import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ssu.capstne.alrimi.api.controller.dtos.token.TokenDto
import ssu.capstne.alrimi.api.model.device.Device
import ssu.capstne.alrimi.api.service.device.DeviceService

@RestController
@RequestMapping("/device")
class DeviceController(private val deviceService: DeviceService) {

    @PostMapping("/token")
    fun makeToken(dto: TokenDto): Device {
        return deviceService.saveToken(dto)
    }

    @DeleteMapping("/token")
    fun deleteToken(dto: TokenDto): Boolean {
        return deviceService.deleteToken(dto)
    }
}