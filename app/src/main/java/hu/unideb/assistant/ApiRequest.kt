package hu.unideb.assistant

import hu.unideb.assistant.api.*
import hu.unideb.assistant.api.ApiUrls.API_KEY
import retrofit2.http.*

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

    @Headers("Authorization: BVb6iTWgJi5o")
    @GET("ai")
    suspend fun getAI(@Query("message") message: String,@Query("server") server: String): AIJson

    @Headers("Authorization: BVb6iTWgJi5o")
    @GET("weather")
    suspend fun getWeather(@Query("city") city: String)

}