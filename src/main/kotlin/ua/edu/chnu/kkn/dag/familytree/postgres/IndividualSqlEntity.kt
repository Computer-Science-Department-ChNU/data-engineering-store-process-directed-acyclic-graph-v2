package ua.edu.chnu.kkn.dag.familytree.postgres

import jakarta.persistence.*
import ua.edu.chnu.kkn.dag.familytree.common.Sex
import java.time.LocalDate

@Entity
@Table(name = "individual")
data class IndividualSqlEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val realId: String,
    val name: String,
    @Enumerated(EnumType.STRING)
    val sex: Sex,
    val birthDate: LocalDate?,
    val deathDate: LocalDate?,
)
