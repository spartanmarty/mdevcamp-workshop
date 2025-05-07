package eu.livesport.workshop.parkinglots

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform