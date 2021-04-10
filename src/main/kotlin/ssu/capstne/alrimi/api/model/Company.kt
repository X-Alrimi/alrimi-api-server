package ssu.capstne.alrimi.api.model

import javax.persistence.*

@Entity
class Company(
    @Id @GeneratedValue
    val id: Long? = null,

    @Column(nullable = false)
    var name: String,

    @Column(length = 1)
    var evaluation: Int,

    @OneToMany(mappedBy = "company")
    val celebrities: List<Celebrity>
) {

}