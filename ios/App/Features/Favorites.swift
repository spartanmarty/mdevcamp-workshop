import SwiftUI
import Shared

struct Favorites: View {

	@StateObject
	private var viewModel: SharedViewModelStoreOwner<FavoritesViewModel> = .init()

	var body: some View {
		Observing(viewModel.instance.state) { state in
			content(state)
		}
		.onAppear {
			viewModel.instance.loadFavoriteParkingLots()
		}
		.navigationTitle(L10n.label_favorites)
	}

	@ViewBuilder
	private func content(_ state: ParkingLotsState) -> some View {
		switch onEnum(of: state) {
		case let .data(data):
			List(data.parkingLots, id: \.id) { model in
				ParkingLotCard(model: model)
			}
			.listStyle(.plain)

		case .loading:
			Loading()

		case let .error(error):
			ErrorMessage(error: error)
		}
	}
}
