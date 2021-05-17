package com.ssu.capstone.alrimi.integration.device

import com.fasterxml.jackson.databind.ObjectMapper
import com.ssu.capstone.alrimi.api.controller.dtos.token.KeywordDto
import com.ssu.capstone.alrimi.api.controller.dtos.token.TokenDto
import com.ssu.capstone.alrimi.api.model.device.Device
import com.ssu.capstone.alrimi.api.service.device.DeviceService
import com.ssu.capstone.alrimi.integration.IntegrationTestBase
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status


class DeviceIntegrationTest : IntegrationTestBase() {

    @Autowired
    lateinit var deviceService: DeviceService

    @Autowired
    lateinit var objectMapper: ObjectMapper

    @Test
    @DisplayName("정상적인 Device Token 저장 테스트")
    fun saveDeviceTokenTest() {
        val tokenDto = TokenDto("Not Nullable Token")
        Assertions.assertDoesNotThrow { deviceService.saveToken(tokenDto) }

        mockMvc.perform(
            post("/device/token")
                .content(objectMapper.writeValueAsString(tokenDto))
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect {
            status().isCreated

        }
    }

    @Test
    @DisplayName("deviceToken RequestBody Validation test")
    fun failedWhenTokenIsEmpty(){
        val tokenDto = TokenDto("")
        mockMvc.perform(
            post("/device/token")
                .content(objectMapper.writeValueAsString(tokenDto))
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect {
            status().isBadRequest
            jsonPath("error").isNotEmpty
            jsonPath("error.code").value("SYSTEM_001")

        }
    }

    @Test
    @DisplayName("정상적인 Device Token 삭제 테스트")
    fun deleteDeviceTokenTest() {
        val tokenDto = TokenDto("Not Nullable Token")
        Assertions.assertDoesNotThrow { deviceService.saveToken(tokenDto) }

        mockMvc.perform(
            delete("/device/token")
                .content(objectMapper.writeValueAsString(tokenDto))
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect {
            status().isOk

        }
    }

    @Test
    @DisplayName("정상적인 Device <---> Keyword 저장 테스트")
    fun addKeywordTest(){
        val keywordDto = KeywordDto("Not Nullable Token","YG")
        deviceRepository.save(Device(keywordDto.token))
        Assertions.assertDoesNotThrow { deviceService.addKeyword(keywordDto) }

        mockMvc.perform(post("/device/keyword")
            .content(objectMapper.writeValueAsString(keywordDto))
            .contentType(MediaType.APPLICATION_JSON)).andExpect {
                status().isCreated
        }
    }

    @Test
    @DisplayName("비정삭적인 keywordDto(Token is empty) Validation Test")
    fun failedInvalidKeywordTest1(){
        val keywordDto = KeywordDto("","YG")
        mockMvc.perform(post("/device/keyword")
            .content(objectMapper.writeValueAsString(keywordDto))
            .contentType(MediaType.APPLICATION_JSON)).andExpect {
            status().isBadRequest
            jsonPath("error").isNotEmpty
            jsonPath("error.code").value("SYSTEM_001")
        }
    }

    @Test
    @DisplayName("비정삭적인 keywordDto(존재하지 않는 키워드) Validation Test")
    fun failedInvalidKeywordTest2(){
        val keywordDto = KeywordDto("Not Nullable Token","NotKeyword")
        deviceRepository.save(Device(keywordDto.token))
        mockMvc.perform(post("/device/keyword")
            .content(objectMapper.writeValueAsString(keywordDto))
            .contentType(MediaType.APPLICATION_JSON)).andExpect {
            status().isBadRequest
            jsonPath("error").isNotEmpty
            jsonPath("error.code").value("COMPANY_001")
        }
    }

    @Test
    @DisplayName("저장되지 않았던 토큰이면 에러 리턴")
    fun failedWhenTokenIsNotExist(){
        val keywordDto = KeywordDto("Not Nullable Token","YG")
        mockMvc.perform(post("/device/keyword")
            .content(objectMapper.writeValueAsString(keywordDto))
            .contentType(MediaType.APPLICATION_JSON)).andExpect {
            status().isBadRequest
            jsonPath("error").isNotEmpty
            jsonPath("error.code").value("DEVICE_001")
        }
    }

    @Test
    @DisplayName("정상적인 키워드 삭제 테스트")
    fun deleteKeywordTest(){
        val keywordDto = KeywordDto("Not Nullable Token","YG")
        deviceRepository.save(Device(keywordDto.token))
        mockMvc.perform(delete("/device/keyword")
            .content(objectMapper.writeValueAsString(keywordDto))
            .contentType(MediaType.APPLICATION_JSON)).andExpect {
            status().isOk
        }
    }
}