import Shared

final class ParkingListViewModel: ObservableObject {

	private let viewModel: ParkingLotsViewModel

	@Published
	public var viewState: State = .Loading()

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
		fetchTabs()
	}

	private func fetchTabs(_ filter: ParkingPolicyFilter = .noFilter) {
		viewModel.loadParkingLots(filters: filter)
	}
}

class ViewStateCollector: Kotlinx_coroutines_coreFlowCollector {

	weak var viewModel: ParkingListViewModel?

	init(viewModel: ParkingListViewModel) {
		self.viewModel = viewModel
	}

	@MainActor
	func emit(value: Any?) async throws {
		if let state = value as? State {
			viewModel?.viewState = state
		}
	}
}
