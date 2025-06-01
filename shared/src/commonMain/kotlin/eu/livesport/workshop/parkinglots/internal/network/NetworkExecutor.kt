package eu.livesport.workshop.parkinglots.internal.network

import kotlin.reflect.KClass

internal interface NetworkExecutor {
    suspend fun <T : Any> get(url: String, clazz: KClass<T>): Result<T>
}

internal suspend inline fun <reified T : Any> NetworkExecutor.get(url: String): Result<T> = get(url, T::class)
