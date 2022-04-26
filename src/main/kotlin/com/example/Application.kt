package com.example

import com.example.api.plugins.configureHTTP
import com.example.api.plugins.configureRouting
import com.example.api.plugins.configureSerialization
import com.example.database.DisciplineRepositoryPostgresImpl
import io.ktor.server.application.*

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {

    val db = connect(
        url = environment.config.property("datasource.url").getString(),
        driver = environment.config.property("datasource.driver").getString(),
        user = environment.config.property("datasource.user").getString(),
        password = environment.config.property("datasource.password").getString()
    )

    createTables(db)

    val disciplineRepository = DisciplineRepositoryPostgresImpl(db)

    configureHTTP()
    configureSerialization()
    configureRouting(disciplineRepository)
}
