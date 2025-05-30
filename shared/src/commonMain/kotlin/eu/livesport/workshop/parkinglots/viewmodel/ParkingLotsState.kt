package eu.livesport.workshop.parkinglots.viewmodel

import eu.livesport.workshop.parkinglots.repository.model.ParkingLot

public sealed class ParkingLotsState {

    public data object Loading : ParkingLotsState()

    public data class Data(val parkingLots: List<ParkingLot>) : ParkingLotsState()

    public data class Error(val type: Type) : ParkingLotsState() {

        public enum class Type {
            NETWORK,
            NO_DATA_FOUND,
            UNKNOWN,
        }
    }
}
