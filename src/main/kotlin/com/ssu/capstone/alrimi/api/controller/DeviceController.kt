package com.ssu.capstone.alrimi.api.controller

import com.ssu.capstone.alrimi.api.controller.dtos.token.TokenDto
import com.ssu.capstone.alrimi.api.model.device.Device
import com.ssu.capstone.alrimi.api.service.device.DeviceService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


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