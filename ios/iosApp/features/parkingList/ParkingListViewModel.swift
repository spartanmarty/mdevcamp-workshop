import Shared

final class ParkingListViewModel: ObservableObject {

	@Published
	public var viewState: ViewStateType = .loading

	@Published
	public var selectTabFilter: ParkingPolicyFilter = .noFilter
	private let viewModel: ParkingLotsViewModel

	init() {
		self.viewModel = ParkingLotsViewModel(
			savedStateHandle: .init(initialState: [:]),
			repository: ParkingRepositoryCompanion.shared.INSTANCE
		)
		Task {
			try await viewModel.state.collect(
				collector: UniversalStateCollector(setViewState: { [weak self] in self?.viewState = $0 })
			)
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
