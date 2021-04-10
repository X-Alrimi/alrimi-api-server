package ssu.capstne.alrimi.api.model

import javax.persistence.Entity
import javax.persistence.Id

@Entity
class Device(@Id val token: String) {
}