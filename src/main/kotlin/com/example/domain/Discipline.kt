package com.example.domain

import kotlinx.serialization.Serializable

@Serializable
data class Discipline(
    val code: String,
    val name: String,
    val classCredit: Int,
    val workCredit: Int
)