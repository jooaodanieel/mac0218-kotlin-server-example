package com.example.database

import com.example.domain.Discipline
import com.example.domain.DisciplineNotFoundException
import com.example.domain.DisciplineRepository
import com.example.database.tables.Disciplines
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

class DisciplineRepositoryPostgresImpl(private val db: Database) : DisciplineRepository {
    override fun findAll() = transaction(db) {
        Disciplines
            .selectAll()
            .map {
                Discipline(
                    code = it[Disciplines.code],
                    name = it[Disciplines.name],
                    classCredit = it[Disciplines.classCredit],
                    workCredit = it[Disciplines.workCredit]
                )
            }
    }

    override fun findOne(code: String): Discipline {
        val disciplines = transaction(db) {
            Disciplines
                .select { Disciplines.code eq code }
                .map {
                    Discipline(
                        code = it[Disciplines.code],
                        name = it[Disciplines.name],
                        classCredit = it[Disciplines.classCredit],
                        workCredit = it[Disciplines.workCredit]
                    )
                }
        }

        if (disciplines.isEmpty())
            throw DisciplineNotFoundException(code)

        return disciplines.first()
    }
}