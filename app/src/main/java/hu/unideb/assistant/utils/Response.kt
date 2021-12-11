package hu.unideb.assistant.utils

import android.util.Log
import hu.unideb.assistant.ApiRequest
import hu.unideb.assistant.api.ApiUrls
import hu.unideb.assistant.utils.Constants.OPEN_GOOGLE
import hu.unideb.assistant.utils.Constants.OPEN_SEARCH
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Response {
    @DelicateCoroutinesApi
    suspend fun basicResponse(message: String): String {
        val random = (0..2).random()
        val message = message.lowercase()

        return when {
            //Hello
            message.contains("hello there") -> {
                "General Kenobi!"
            }
            message.contains("hello") -> {
                when (random) {
                    0 -> "Hello there!"
                    1 -> "Hi!"
                    2 -> "Hello World!"
                    else -> "Error"
                }

            }
            // Time
            message.contains("time") && message.contains("?") -> {
                Time.TimeStamp()
            }
            message.contains("bread") -> {
                "\uD83C\uDF5E"
            }
            message.contains("fact") -> {
                val ans = CoroutineScope(Dispatchers.IO).async {
                    val api = Retrofit.Builder().baseUrl(ApiUrls.USELESS_FACT_URL)
                        .addConverterFactory(GsonConverterFactory.create()).build()
                        .create(ApiRequest::class.java)
                    val respond = api.getUselessFact()
                    return@async respond.text
                }
                return ans.await()
            }
            message.contains("joke") -> {
                val ans = CoroutineScope(Dispatchers.IO).async {
                    val api = Retrofit.Builder().baseUrl(ApiUrls.DARK_HUMOR)
                        .addConverterFactory(GsonConverterFactory.create()).build()
                        .create(ApiRequest::class.java)
                    val respond = api.getDarkHumor()
                    return@async respond.joke
                }
                return ans.await()
            }
            message.contains("yo momma") -> {
                val ans = CoroutineScope(Dispatchers.IO).async {
                    val api = Retrofit.Builder().baseUrl(ApiUrls.YO_MOMMA)
                        .addConverterFactory(GsonConverterFactory.create()).build()
                        .create(ApiRequest::class.java)
                    val respond = api.getYoMomma()
                    return@async respond.joke
                }
                return ans.await()
            }
            message.contains("bored") || message.contains("activity") -> {
                val ans = CoroutineScope(Dispatchers.IO).async {
                    val api = Retrofit.Builder().baseUrl(ApiUrls.BORED_URL)
                        .addConverterFactory(GsonConverterFactory.create()).build()
                        .create(ApiRequest::class.java)
                    val respond = api.getBored()
                    return@async respond.activity
                }
                return ans.await()
            }
            message.contains("ip") -> {
                val ans = CoroutineScope(Dispatchers.IO).async {
                    val api = Retrofit.Builder().baseUrl(ApiUrls.IP_URL)
                        .addConverterFactory(GsonConverterFactory.create()).build()
                        .create(ApiRequest::class.java)
                    val respond = api.getIP()
                    return@async "${respond.country}\n${respond.regionName}\n${respond.isp}\n${respond.ip}"
                }
                return ans.await()
            }
            message.contains("advice") -> {
                val ans = CoroutineScope(Dispatchers.IO).async {
                    val api = Retrofit.Builder().baseUrl(ApiUrls.ADVICE_URL)
                        .addConverterFactory(GsonConverterFactory.create()).build()
                        .create(ApiRequest::class.java)
                    val respond = api.getAdvice()
                    return@async respond.slip.advice
                }
                return ans.await()
            }
            message.contains("aki ver") -> {
                "Az nem haver \uD83D\uDC80"
            }

            message.contains("lorem") -> {
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."
            }
            //Open Google
            message.contains("open") && message.contains("google") -> {
                OPEN_GOOGLE
            }
            // Search for the string
            message.contains("search") -> {
                OPEN_SEARCH
            }
            message.contains("how are you?") || message.contains("what's up?") -> {
                when (random) {
                    0 -> "I'm fine thanks!"
                    1 -> "Pretty good actually! And you?"
                    2 -> "I'm tired of this job."
                    else -> "Error"
                }
            }
            else -> {
                when (random) {
                    0 -> "What do you mean?"
                    1 -> "Would you kindly repeat that to me again?"
                    2 -> "I don't know....."
                    else -> "Error"
                }
            }
        }
    }
}