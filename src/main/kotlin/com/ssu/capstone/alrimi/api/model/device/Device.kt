package com.ssu.capstone.alrimi.api.model.device

import com.ssu.capstone.alrimi.api.model.company.Company
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.ManyToMany

@Entity
class Device(@Id val token: String) {

    @ManyToMany(mappedBy = "devices")
    val companies: MutableList<Company> = mutableListOf()
}