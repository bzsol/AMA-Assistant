package hu.unideb.assistant

import hu.unideb.assistant.api.FactJson
import retrofit2.Call
import retrofit2.http.GET

interface ApiRequest {
    @GET("random.json?language=en")
    suspend fun getUselessFact(): FactJson

}