package com.example

import com.example.database.tables.Disciplines
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

fun connect(url: String, driver: String, user: String, password: String): Database {
    return Database.connect(url, driver, user, password)
}

fun createTables(db: Database) {
    transaction(db) {
        SchemaUtils.create(Disciplines)
    }
}