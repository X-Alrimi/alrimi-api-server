package com.ssu.capstone.alrimi.api.model.company

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Company(
    @Id @GeneratedValue
    val id: Long? = null,

    @Column(nullable = false)
    var name: String,

    @Column(length = 1)
    var evaluation: Int = 0,
)