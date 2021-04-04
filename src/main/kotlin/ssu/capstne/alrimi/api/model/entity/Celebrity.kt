package ssu.capstne.alrimi.api.model.entity

import javax.persistence.*

@Entity
class Celebrity(
    @Id @GeneratedValue
    val id: Long? = null,

    @ManyToOne
    val company: Company,

    @Column(nullable = false)
    val name: String,

    val birth: String
) {
}