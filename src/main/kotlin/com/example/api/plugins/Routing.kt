package com.example.api.plugins

import com.example.api.ListOfDisciplinesPayload
import com.example.api.SingleDisciplinePayload
import com.example.domain.DisciplineNotFoundException
import com.example.domain.DisciplineRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting(disciplineRepository: DisciplineRepository) {
    routing {
        get("/disciplines/{code}") {
            val code = call.parameters["code"] ?: return@get call.respond(
                status = HttpStatusCode.BadRequest,
                message = mapOf("error" to "Discipline code must be provided")
            )

            try {
                val discipline = disciplineRepository.findOne(code)

                call.respond(
                    status = HttpStatusCode.OK,
                    message = SingleDisciplinePayload(discipline)
                )
            } catch (e: DisciplineNotFoundException) {
                call.respond(
                    status = HttpStatusCode.NotFound,
                    message = mapOf("error" to e.message)
                )
            }
        }

        get("/disciplines") {
            val disciplines = disciplineRepository.findAll()

            call.respond(
                status = HttpStatusCode.OK,
                message = ListOfDisciplinesPayload(disciplines)
            )
        }
    }
}
