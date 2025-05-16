import Shared

final class ParkingListViewModel: ObservableObject {

	enum ViewState {
		case loading
		case data(model: [ParkingCardComponentModel])
		case empty
	}

	@Published
	var viewState: ViewState = .loading

	func fetchData() {
		Task {
			// TODO - Fetch data
			await MainActor.run {
				viewState = .data(model: [.mock])
			}
//			do {
//				// handle data
//			} catch {
//				// handle error
//			}
		}
	}
}
