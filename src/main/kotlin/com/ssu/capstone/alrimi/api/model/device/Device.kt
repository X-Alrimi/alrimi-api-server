package com.ssu.capstone.alrimi.api.model.device

import javax.persistence.Entity
import javax.persistence.Id

@Entity
class Device(@Id val token: String) {
}