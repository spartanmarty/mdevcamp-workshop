package eu.livesport.workshop.parkinglots.ui

import eu.livesport.workshop.parkinglots.repository.model.ParkingProhibitions
import eu.livesport.workshop.parkinglots.ui.resources.DrawableRes

public class ProhibitionIconResolver(
    private val icons: Icons,
) {
    public fun resolveIcon(prohibition: ParkingProhibitions): DrawableRes {
        return when (prohibition) {
            ParkingProhibitions.LPG_CNG -> icons.lpgCng
            ParkingProhibitions.BUS -> icons.bus
            ParkingProhibitions.TRUCK -> icons.truck
            ParkingProhibitions.MOTORCYCLE -> icons.motorcycle
            ParkingProhibitions.BICYCLE -> icons.bicycle
            ParkingProhibitions.TRAILER -> icons.trailer
        }
    }
}