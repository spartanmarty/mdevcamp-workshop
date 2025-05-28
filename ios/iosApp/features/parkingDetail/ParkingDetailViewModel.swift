import Shared

final class ParkingDetailViewModel: ObservableObject {

	enum ViewState {
		case loading
		case data(model: ParkingLot)
		case error(String)
	}

	private let viewModel: ParkingLotDetailViewModel

	@Published
	var viewState: ViewState = .loading

	init() {
		self.viewModel = ParkingLotDetailViewModel(
			savedStateHandle: .init(initialState: [:]),
			repository: KoinHelper.shared.getParkingRepository(),
			favoriteParkingRepository: KoinHelper.shared.getFavoriteParkingRepository()
		)
		Task {
			try await viewModel.state.collect(collector: ParkingDetailViewStateCollector(viewModel: self))
		}
	}

	@MainActor
	public func fetchData(parkId: String) async {
		viewModel.loadParkingLotDetail(id: parkId)
	}

	@MainActor
	public func toggleFavorite(_ parkingLot: ParkingLot) {
		viewModel.toggleFavorite(parkingLot: parkingLot)
	}


}

class ParkingDetailViewStateCollector: Kotlinx_coroutines_coreFlowCollector {

	private weak var viewModel: ParkingDetailViewModel?

	init(viewModel: ParkingDetailViewModel) {
		self.viewModel = viewModel
	}

	@MainActor
	func emit(value: Any?) async throws {
		if let state = value as? State {
			switch state {
			case is State.Loading:
				viewModel?.viewState = .loading

			case let model as State.Data:
				if let parkingLot = model.parkingLots.first {
					viewModel?.viewState = .data(model: parkingLot)
				}

			case let model as State.Error:
				viewModel?.viewState = .error(getTranslate(type: model.type))

			default:
				#if DEBUG
				print("Unknow view state: \(state)")
				#endif
			}
		}
	}

	private func getTranslate(type: State.ErrorType) -> String {
		switch type {
		case .noDataFound:
			"No data found."

		case .network:
			"Problem with network connection."

		case .unknown:
			"¯\\_(ツ)_/¯"

		default:
			""
		}
	}
}
