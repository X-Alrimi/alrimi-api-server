package com.ssu.capstone.alrimi.api.controller.dtos.news

class CriticalNewsDto(val news: AlarmNewsDto, val isCritical: Boolean, val similarity: List<Double>) {
}