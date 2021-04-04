package ssu.capstne.alrimi.api.model.entity

import javax.persistence.*

@Entity
class Company(
    @Id @GeneratedValue
    val id: Long? = null,

    @Column(nullable = false)
    var name: String,

    @Column(length = 1)
    var evaluation: Int,

    @OneToMany(mappedBy = "company",fetch = FetchType.EAGER)
    val celebrities:List<Celebrity>
) {

}