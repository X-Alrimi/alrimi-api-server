package com.ssu.capstone.alrimi.api.service.celebrity.exception

import com.ssu.capstone.alrimi.core.execption.ApiException

class CelebrityNotFoundException() : ApiException("Celebrity Not Found") {
}