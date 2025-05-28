package eu.livesport.workshop.parkinglots.build

import eu.livesport.workshop.parkinglots.BuildConfig

class BuildConfigProviderImpl : BuildConfigProvider {
    override val isDebug: Boolean = BuildConfig.DEBUG
    override val isDevBuild: Boolean = BuildConfig.DEVELOPMENT_BUILD
}
