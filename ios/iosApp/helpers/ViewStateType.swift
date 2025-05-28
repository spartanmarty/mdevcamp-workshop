import Shared

enum ViewStateType {
	case loading
	case data(model: [ParkingLot])
	case error(State.Error)
}
