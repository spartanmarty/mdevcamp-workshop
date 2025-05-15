package eu.livesport.workshop.parkinglots.internal.network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.statement.HttpResponse
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

internal class NetworkExecutor {

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

    suspend inline fun <reified T> get(url: String): Result<T> =
        try {
            val response: HttpResponse = httpClient.get(url) { addDefaultHeaders() }
            Result.success(response.body())
        } catch (e: Exception) {
            Result.failure(e)
        }

    private fun HttpRequestBuilder.addDefaultHeaders() {
        headers {
            append(HEADER_ACCESS_TOKEN, GOLEMIO_API_KEY)
        }
    }

    companion object {
        // TODO: Use DI to inject the repository.
        val INSTANCE: NetworkExecutor by lazy { NetworkExecutor() }
    }
}

private const val HEADER_ACCESS_TOKEN: String = "X-Access-Token"

// TODO: Replace with your Golemio API key.
private const val GOLEMIO_API_KEY: String = "<GOLEMIO_API_KEY>"
