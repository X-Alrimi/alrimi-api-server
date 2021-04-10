package ssu.capstne.alrimi.api.controller

import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ssu.capstne.alrimi.api.controller.dtos.token.TokenDto
import ssu.capstne.alrimi.api.controller.response.Response
import ssu.capstne.alrimi.api.service.DeviceService

import ssu.capstne.alrimi.core.util.ResponseUtil.succeed

@RestController
@RequestMapping("/device")
class DeviceController(private val deviceService: DeviceService) {

    @PostMapping("/token")
    fun makeToken(dto: TokenDto): Response {
        return succeed(deviceService.saveToken(dto))
    }

    @DeleteMapping("/token")
    fun deleteToken(dto: TokenDto): Response {
        return succeed(deviceService.deleteToken(dto))
    }
}