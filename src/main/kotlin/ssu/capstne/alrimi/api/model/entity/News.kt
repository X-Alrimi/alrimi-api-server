package ssu.capstne.alrimi.api.model.entity

import javax.persistence.*

@Entity
class News(
    @Id @GeneratedValue
    val id: Long? = null,

    @Column(nullable = false)
    val title: String,

    @Column(nullable = false)
    val link: String,

    @Column(nullable = false)
    val createdAt: String,

    @ManyToOne
    val company: Company
) {
}