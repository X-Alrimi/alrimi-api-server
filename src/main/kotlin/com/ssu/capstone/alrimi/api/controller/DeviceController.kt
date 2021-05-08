package com.ssu.capstone.alrimi.api.controller

import com.ssu.capstone.alrimi.api.controller.dtos.token.TokenDto
import com.ssu.capstone.alrimi.api.model.device.Device
import com.ssu.capstone.alrimi.api.service.device.DeviceService
import org.springframework.web.bind.annotation.*
import javax.validation.Valid


@RestController
@RequestMapping("/device")
class DeviceController(private val deviceService: DeviceService) {

    @PostMapping("/token")
    fun makeToken(@RequestBody @Valid dto: TokenDto): Device {
        return deviceService.saveToken(dto)
    }

    @DeleteMapping("/token")
    fun deleteToken(@RequestBody @Valid dto: TokenDto): Boolean {
        return deviceService.deleteToken(dto)
    }
}