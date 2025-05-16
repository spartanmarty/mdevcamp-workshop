package eu.livesport.workshop.parkinglots

public interface Platform {
    public val name: String
}

public expect fun getPlatform(): Platform