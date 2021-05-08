package com.ssu.capstone.alrimi.api.repository

import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort

class PageUtil(page: Int) : PageRequest(page, 4, Sort.by("id").descending()) {
}