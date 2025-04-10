package ua.edu.chnu.kkn.dag.familytree.postgres

import jakarta.persistence.*

@Entity
@Table(name = "connection")
data class ConnectionSqlEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne
    @JoinColumn(name = "child", referencedColumnName = "id", nullable = false)
    val child: IndividualSqlEntity,

    @ManyToOne
    @JoinColumn(name = "father", referencedColumnName = "id", nullable = true)
    val father: IndividualSqlEntity?,

    @ManyToOne
    @JoinColumn(name = "mother", referencedColumnName = "id", nullable = true)
    val mother: IndividualSqlEntity?

)
