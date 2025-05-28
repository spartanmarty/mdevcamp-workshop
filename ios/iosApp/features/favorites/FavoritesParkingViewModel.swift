import Shared

final class FavoritesParkingViewModel: ObservableObject {

	@Published
	public var viewState: ViewStateType = .loading
	private let viewModel: FavoritesViewModel

	init() {
		self.viewModel = FavoritesViewModel(favoriteParkingRepository: KoinHelper.shared.getFavoriteParkingRepository())
		Task {
			try await viewModel.state.collect(
				collector: UniversalStateCollector(setViewState: { [weak self] in self?.viewState = $0 })
			)
		}
	}

	@MainActor
	public func fetchData() async {
		viewModel.loadFavoriteParkingLots()
	}
}
