package hu.unideb.assistant

import hu.unideb.assistant.api.*
import retrofit2.http.GET

interface ApiRequest {
    @GET("random.json?language=en")
    suspend fun getUselessFact(): FactJson

    @GET("jokes")
    suspend fun getYoMomma(): MommaJson

    @GET("https://v2.jokeapi.dev/joke/Any?type=single")
    suspend fun getDarkHumor(): DarkJson

    @GET("activity")
    suspend fun getBored(): BoredJson

    @GET("ipgeo")
    suspend fun getIP(): IPJson

    @GET("advice")
    suspend fun getAdvice(): AdviceJson

}