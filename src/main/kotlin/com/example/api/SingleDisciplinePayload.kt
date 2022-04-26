package com.example.api

import com.example.domain.Discipline

import kotlinx.serialization.Serializable

@Serializable
data class SingleDisciplinePayload(
    val discipline: Discipline
)