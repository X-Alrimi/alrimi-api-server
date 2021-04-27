package com.ssu.capstone.alrimi.api.controller

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@RequestMapping("/news")
class NewsController {

    @PostMapping
    fun getNews() {

    }
}