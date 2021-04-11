package com.ssu.capstone.alrimi.api.model.news

import com.ssu.capstone.alrimi.api.model.company.Company
import java.util.*
import javax.persistence.*

@Entity
@Table(indexes = [Index(name = "news_title", columnList = "title")])
class News(
    @Id @GeneratedValue
    val id: Long? = null,

    @Column(nullable = false)
    val title: String,

    @Column(nullable = false)
    val link: String,

    @Column(nullable = false)
    val createdAt: Date,

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    val company: Company
) {
}