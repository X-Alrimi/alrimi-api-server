package com.ssu.capstone.alrimi.api.controller.dtos.news

data class PagingNewsDto(val news: List<SimpleNewsDto>, val currentPage: Int, val totalPage: Int) {
}