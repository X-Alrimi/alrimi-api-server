package ssu.capstne.alrimi.api.model.entity

import javax.persistence.*

@Entity
@Table(indexes = [Index(name = "n_index_company_id", columnList = "company_id")])
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
    @JoinColumn(name = "company_id", nullable = false)
    val company: Company
) {
}