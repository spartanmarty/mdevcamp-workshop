package eu.livesport.workshop.parkinglots.viewmodel

import eu.livesport.workshop.parkinglots.repository.model.ParkingLot

public sealed class State {

    public data object Loading : State()

    public data class Data(val parkingLots: List<ParkingLot>) : State()

    public data class Error(val type: Type) : State() {

        public enum class Type {
            NETWORK,
            NO_DATA_FOUND,
            UNKNOWN,
        }
    }
}
