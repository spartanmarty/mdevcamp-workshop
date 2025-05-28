import Shared

final class ParkingDetailViewModel: ObservableObject {

	@Published
	var viewState: ViewStateType = .loading
	private let viewModel: ParkingLotDetailViewModel

	init() {
		self.viewModel = ParkingLotDetailViewModel(
			savedStateHandle: .init(initialState: [:]),
			repository: KoinHelper.shared.getParkingRepository(),
			favoriteParkingRepository: KoinHelper.shared.getFavoriteParkingRepository()
		)
		Task {
			try await viewModel.state.collect(
				collector: UniversalStateCollector(setViewState: { [weak self] in self?.viewState = $0 })
			)
		}
	}

	@MainActor
	func fetchData(parkId: String) async {
		viewModel.loadParkingLotDetail(id: parkId)
	}

	@MainActor
	func toggleFavorite(_ parkingLot: ParkingLot) {
		viewModel.toggleFavorite(parkingLot: parkingLot)
	}
}
