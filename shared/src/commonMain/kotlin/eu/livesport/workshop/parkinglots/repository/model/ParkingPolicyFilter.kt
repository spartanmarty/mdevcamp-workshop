package eu.livesport.workshop.parkinglots.repository.model

public enum class ParkingPolicyFilter(
    public val value: String,
) {
    NO_FILTER(""),
    COMMERCIAL("commercial"),
    CUSTOMER_ONLY("customer_only"),
    PARK_AND_RIDE("park_and_ride"),
    KISS_AND_RIDE("kiss_and_ride"),
    PARK_SHARING("park_sharing"),
    ZONE("zone"),
    ;
}