import Shared

final class ParkingDetailViewModel: ObservableObject {

	enum ViewState {
		case loading
		case data(model: ParkingDetailComponentModel)
		case empty
	}

	@Published
	var viewState: ViewState = .loading

	func fetchData(parkId: String) {
		Task {
			await MainActor.run {
				viewState = .data(model: .mock)
			}
		}
	}
}
