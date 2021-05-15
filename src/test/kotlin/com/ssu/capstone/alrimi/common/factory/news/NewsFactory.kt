package com.ssu.capstone.alrimi.common.factory.news

import com.ssu.capstone.alrimi.api.controller.dtos.news.DetailNewsDto

object NewsFactory {

    fun getNewsDto(): DetailNewsDto {
        return DetailNewsDto("MockTitle", "https://mocknews.com", "mocking description", "2021-05-15")
    }
}