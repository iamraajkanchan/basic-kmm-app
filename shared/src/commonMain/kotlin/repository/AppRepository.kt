package repository

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.JsonConvertException
import io.ktor.serialization.kotlinx.json.json
import model.BirdResponse
import model.TodosResponse

class AppRepository private constructor() {
    private val httpClient = HttpClient() {
        install(ContentNegotiation) {
            json()
        }
    }

    suspend fun getBirds(): List<BirdResponse>? {
        return try {
            httpClient.get("https://sebastianaigner.github.io/demo-image-api/pictures.json")
                .body<List<BirdResponse>>()
        } catch (e: JsonConvertException) {
            e.printStackTrace()
            null
        }
    }

    suspend fun getTodos(): List<TodosResponse>? {
        return try {
            httpClient.get("https://jsonplaceholder.typicode.com/posts")
                .body<List<TodosResponse>>()
        } catch (e: JsonConvertException) {
            e.printStackTrace()
            null
        }
    }

    fun shutHttpClient() {
        httpClient.close()
    }

    companion object {
        fun getInstance(): AppRepository = AppRepository()
    }
}