package com.ssu.capstone.alrimi.api.controller

import com.ssu.capstone.alrimi.api.controller.dtos.token.KeywordDto
import com.ssu.capstone.alrimi.api.controller.dtos.token.TokenDto
import com.ssu.capstone.alrimi.api.model.device.Device
import com.ssu.capstone.alrimi.api.service.device.DeviceService
import io.swagger.annotations.ApiOperation
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid


@RestController
@RequestMapping("/device")
class DeviceController(private val deviceService: DeviceService) {

    @PostMapping("/token")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("FCM 디바이스 토큰 저장하기")
    fun makeToken(@RequestBody @Valid dto: TokenDto): Device {
        return deviceService.saveToken(dto)
    }

    @DeleteMapping("/token")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("FCM 디바이스 토큰 삭제하기")
    fun deleteToken(@RequestBody @Valid dto: TokenDto): Boolean {
        return deviceService.deleteToken(dto)
    }

    @PostMapping("/keyword")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("키워드 추가")
    fun addKeyword(@RequestBody @Valid keywordDto: KeywordDto): Boolean {
        return deviceService.addKeyword(keywordDto)
    }

    @DeleteMapping("/keyword")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("키워드 삭제")
    fun deleteKeyword(@RequestBody @Valid keywordDto: KeywordDto): Boolean {
        return deviceService.deleteKeyword(keywordDto)
    }
}