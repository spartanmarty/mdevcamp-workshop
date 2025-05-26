package eu.livesport.workshop.parkinglots.viewmodel

import eu.livesport.workshop.parkinglots.repository.model.ParkingLot

public sealed class State {
    public data object Loading : State()
    public data class Data(val parkingLots: List<ParkingLot>) : State()
}