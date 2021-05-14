package com.ssu.capstone.alrimi.api.model.company

import com.ssu.capstone.alrimi.api.model.device.Device
import java.util.*
import javax.persistence.*

@Entity
@Table(indexes = [Index(name = "company_name", columnList = "name")])
data class Company(
    @Id @GeneratedValue
    val id: Long? = null,

    @Column(nullable = false)
    var name: String
) {
    @ManyToMany
    val devices: MutableList<Device> = mutableListOf()

    var recentAlarm: Date? = null
}