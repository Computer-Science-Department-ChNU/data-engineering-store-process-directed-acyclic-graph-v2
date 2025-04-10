package ua.edu.chnu.kkn.dag.familytree.postgres

import jakarta.transaction.Transactional
import org.folg.gedcom.model.GedcomTag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ua.edu.chnu.kkn.dag.familytree.common.*

@Service
class IndividualSqlService {

    @Autowired
    lateinit var individualSqlRepository: IndividualSqlRepository

    @Autowired
    lateinit var connectionSqlRepository: ConnectionSqlRepository

    @Transactional
    fun saveAll(gedcomTags: List<GedcomTag>){
        saveIndividuals(gedcomTags)
    }

    private fun saveIndividuals(gedcomTags: List<GedcomTag>) {
        val individuals = gedcomTags
            .filter { it.isIndividual() }
            .map(this::toIndividual)
            .sortedBy { it.id }
        individualSqlRepository.saveAll(individuals)
    }

    private fun toIndividual(tag: GedcomTag) =
        IndividualSqlEntity(
            realId = tag.id,
            name = tag.children[0].value,
            sex = tag.children.find { it.isSex() }?.getSex() ?: Sex.UNDEFINED,
            birthDate = tag.children
                .find { it.isBirthday() }
                ?.children?.find { it.isDate() }
                ?.let { parseDate(it.value) },
            deathDate = tag.children
                .find { it.isDeath() }
                ?.children?.find { it.isDate() }
                ?.let { parseDate(it.value) },
        )
}