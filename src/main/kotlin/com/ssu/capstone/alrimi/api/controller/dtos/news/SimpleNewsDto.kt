package com.ssu.capstone.alrimi.api.controller.dtos.news

import com.ssu.capstone.alrimi.api.model.news.News

class SimpleNewsDto(val title: String, val link: String, val createdAt: String) {
    constructor(news: News) : this(news.title, news.link, news.createdAt)
}