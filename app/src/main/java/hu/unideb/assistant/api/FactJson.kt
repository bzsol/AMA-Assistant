package hu.unideb.assistant.api

data class FactJson(
    val id: String,
    val language: String,
    val permalink: String,
    val source: String,
    val source_url: String,
    val text: String
)