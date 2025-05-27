import Shared

final class ParkingListViewModel: ObservableObject {

	enum ViewState {
		case loading
		case data(model: [ParkingLot])
		case error(String)
	}

	private let viewModel: ParkingLotsViewModel

	@Published
	public var viewState: ViewState = .loading

	@Published
	public var selectTabFilter: ParkingPolicyFilter = .noFilter

	init() {
		self.viewModel = ParkingLotsViewModel(
			savedStateHandle: .init(initialState: [:]),
			repository: KoinHelper.shared.getParkingRepository()
		)
		Task {
			try await viewModel.state.collect(collector: ViewStateCollector(viewModel: self))
		}
	}

	@MainActor
	public func fetchData() async {
		fetchParkingLots()
	}

	@MainActor
	public func setSelectTabFilter(filter: ParkingPolicyFilter) {
		selectTabFilter = filter
		fetchParkingLots()
	}

	private func fetchParkingLots() {
		viewModel.loadParkingLots(filters: selectTabFilter)
	}
}

class ViewStateCollector: Kotlinx_coroutines_coreFlowCollector {

	private weak var viewModel: ParkingListViewModel?

	init(viewModel: ParkingListViewModel) {
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
