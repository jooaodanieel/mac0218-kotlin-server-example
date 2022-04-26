package com.example.domain

interface DisciplineRepository {
    fun findAll(): List<Discipline>

    fun findOne(code: String): Discipline
}