import Shared

final class FavoritesParkingViewModel: ObservableObject {

	enum ViewState {
		case loading
		case data(model: [ParkingLot])
		case error(String)
	}

	private let viewModel: FavoritesViewModel

	@Published
	public var viewState: ViewState = .loading

	init() {
		self.viewModel = FavoritesViewModel(favoriteParkingRepository: KoinHelper.shared.getFavoriteParkingRepository())
		Task {
			try await viewModel.state.collect(collector: FavoritesViewStateCollector(viewModel: self))
		}
	}

	@MainActor
	public func fetchData() async {
		viewModel.loadFavoriteParkingLots()
	}
}

class FavoritesViewStateCollector: Kotlinx_coroutines_coreFlowCollector {

	private weak var viewModel: FavoritesParkingViewModel?

	init(viewModel: FavoritesParkingViewModel) {
		self.viewModel = viewModel
	}

	@MainActor
	func emit(value: Any?) async throws {
		if let state = value as? State {
			switch state {
			case is State.Loading:
				viewModel?.viewState = .loading

			case let model as State.Data:
				viewModel?.viewState = .data(model: model.parkingLots)

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
