package com.ssu.capstone.alrimi.api.controller.dtos.news

class PagingNewsDto(val news: List<SimpleNewsDto>, val currentPage: Int, val totalPage: Int) {
}