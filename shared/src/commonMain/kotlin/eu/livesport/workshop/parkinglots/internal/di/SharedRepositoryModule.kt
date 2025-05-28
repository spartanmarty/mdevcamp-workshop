package eu.livesport.workshop.parkinglots.internal.di

import eu.livesport.workshop.parkinglots.build.BuildConfigProvider
import eu.livesport.workshop.parkinglots.internal.repository.ParkingRepositoryImpl
import eu.livesport.workshop.parkinglots.internal.repository.api.ParkingApi
import eu.livesport.workshop.parkinglots.internal.repository.api.ParkingApiImpl
import eu.livesport.workshop.parkinglots.internal.repository.api.mock.MockParkingApi
import eu.livesport.workshop.parkinglots.internal.repository.datasource.ParkingDataSource
import eu.livesport.workshop.parkinglots.repository.ParkingRepository
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module

internal val sharedRepositoryModule: Module =
    module {
        single<ParkingApi>(named(NAMED_KEY_PARKING_API_DEV)) { MockParkingApi() }
        single<ParkingApi>(named(NAMED_KEY_PARKING_API_PROD)) { ParkingApiImpl(networkExecutor = get()) }
        single<ParkingApi> {
            val buildConfigProvider: BuildConfigProvider = get()
            val qualifierName: String =
                if (buildConfigProvider.isDevBuild) NAMED_KEY_PARKING_API_DEV else NAMED_KEY_PARKING_API_PROD

            get<ParkingApi>(named(qualifierName))
        }

        singleOf(::ParkingDataSource)
        singleOf(::ParkingRepositoryImpl) bind ParkingRepository::class
    }

private const val NAMED_KEY_PARKING_API_PROD: String = "parking_api_prod"
private const val NAMED_KEY_PARKING_API_DEV: String = "parking_api_dev"
