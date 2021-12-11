package hu.unideb.assistant.api

data class IPJson(
    val `as`: String,
    val city: String,
    val continent: String,
    val country: String,
    val countryCode: String,
    val currency: String,
    val ip: String,
    val isp: String,
    val lat: Double,
    val lon: Double,
    val mobile: Boolean,
    val org: String,
    val proxy: Boolean,
    val regionName: String,
    val reverse: String,
    val status: String,
    val timezone: String,
    val zip: String
)