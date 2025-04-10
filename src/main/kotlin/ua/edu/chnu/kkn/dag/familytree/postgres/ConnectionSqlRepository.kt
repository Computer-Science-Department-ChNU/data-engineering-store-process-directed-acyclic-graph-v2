package ua.edu.chnu.kkn.dag.familytree.postgres

import org.springframework.data.repository.CrudRepository

interface ConnectionSqlRepository : CrudRepository<ConnectionSqlEntity, Long>