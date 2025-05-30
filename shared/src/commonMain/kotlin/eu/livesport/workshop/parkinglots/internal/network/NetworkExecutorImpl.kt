package eu.livesport.workshop.parkinglots.internal.network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.statement.HttpResponse
import io.ktor.serialization.kotlinx.json.json
import io.ktor.util.reflect.TypeInfo
import kotlinx.serialization.json.Json
import kotlin.reflect.KClass

internal class NetworkExecutorImpl : NetworkExecutor {

    private val httpClient: HttpClient =
        HttpClient {
            install(ContentNegotiation) {
                json(
                    Json {
                        prettyPrint = true
                        isLenient = true
                        ignoreUnknownKeys = true
                    }
                )
            }
        }

    override suspend fun <T : Any> get(url: String, clazz: KClass<T>): Result<T> =
        try {
            val response: HttpResponse = httpClient.get(url) { addDefaultHeaders() }
            Result.success(response.body(TypeInfo(clazz)))
        } catch (e: Exception) {
            Result.failure(e)
        }

    private fun HttpRequestBuilder.addDefaultHeaders() {
        headers {
            append(HEADER_ACCESS_TOKEN, GOLEMIO_API_KEY)
        }
    }
}

private const val HEADER_ACCESS_TOKEN: String = "X-Access-Token"

// TODO: Replace with your Golemio API key.
// Keep it for yourself. You can move it to a different file and keep it untracked in GIT.
private const val GOLEMIO_API_KEY: String = "<GOLEMIO_API_KEY>"
