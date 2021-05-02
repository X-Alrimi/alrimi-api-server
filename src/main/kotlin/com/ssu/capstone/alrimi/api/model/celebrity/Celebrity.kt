package com.ssu.capstone.alrimi.api.model.celebrity

import com.ssu.capstone.alrimi.api.model.company.Company
import javax.persistence.*

@Entity
class Celebrity(
    @Id @GeneratedValue
    val id: Long? = null,

    @JoinColumn(name = "company_id", nullable = false)
    @ManyToOne
    val company: Company,
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "parent_id", nullable = true)
    val children: List<Celebrity>,

    @Column(nullable = false)
    val name: String
){
}