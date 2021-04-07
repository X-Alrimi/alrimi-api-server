package ssu.capstne.alrimi.api.model.entity

import javax.persistence.*

@Entity
@Table(indexes = [Index(name="c_index_company_id",columnList = "company_id")])
class Celebrity(
    @Id @GeneratedValue
    val id: Long? = null,

    @JoinColumn(name="company_id",nullable = false)
    @ManyToOne
    val company: Company,

    @Column(nullable = false)
    val name: String,

    val birth: String
) {
}