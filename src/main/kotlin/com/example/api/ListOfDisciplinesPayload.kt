package com.example.api

import com.example.domain.Discipline

import kotlinx.serialization.Serializable

@Serializable
data class ListOfDisciplinesPayload(
    val disciplines: List<Discipline>
)