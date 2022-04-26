package com.example.domain

class DisciplineNotFoundException(code: String) : Exception("Discipline code=$code not found") {
}