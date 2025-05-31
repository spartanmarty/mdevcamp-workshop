package eu.livesport.workshop.parkinglots.di

import org.koin.core.parameter.ParametersDefinition
import org.koin.core.qualifier.Qualifier
import org.koin.mp.KoinPlatformTools
import kotlin.reflect.KClass

public fun <T> koinGet(
    clazz: KClass<*>,
    qualifier: Qualifier? = null,
    parameters: ParametersDefinition? = null,
): T {
    val koin = KoinPlatformTools.defaultContext().get()
    return koin.get(clazz, qualifier, parameters)
}
