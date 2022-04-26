package com.example.database.tables

import org.jetbrains.exposed.dao.id.IntIdTable

object Disciplines : IntIdTable() {
    val code = varchar("code", 255).uniqueIndex()
    val name = varchar("name", 255)
    val classCredit = integer("class_credit")
    val workCredit = integer("work_credit")
}