package com.ssu.capstone.alrimi.api.controller

import com.ssu.capstone.alrimi.api.controller.dtos.token.TokenDto
import com.ssu.capstone.alrimi.api.model.device.Device
import com.ssu.capstone.alrimi.api.service.device.DeviceService
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.*
import javax.validation.Valid


@RestController
@RequestMapping("/device")
class DeviceController(private val deviceService: DeviceService) {

    @PostMapping("/token")
    @ApiOperation("FCM 디바이스 토큰 저장하기")
    fun makeToken(@RequestBody @Valid dto: TokenDto): Device {
        return deviceService.saveToken(dto)
    }

    @DeleteMapping("/token")
    @ApiOperation("FCM 디바이스 토큰 삭제하기")
    fun deleteToken(@RequestBody @Valid dto: TokenDto): Boolean {
        return deviceService.deleteToken(dto)
    }
}