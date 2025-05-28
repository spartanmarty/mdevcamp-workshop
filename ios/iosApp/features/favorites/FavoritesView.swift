import SwiftUI
import Shared

struct FavoritesView: View {

	@StateObject
	private var viewModel = FavoritesParkingViewModel()
	private let closure: (String) -> Void

	init(closure: @escaping (String) -> Void) {
		self.closure = closure
	}

	var body: some View {
		VStack(spacing: 0) {
			Text("label_favorites")
				.font(.title)
				.fontWeight(.bold)
				.padding(8)

			viewModel.viewState.content {
				ParkingCards(parkingLots: $0, closure: closure)
			}
		}
		.task { await viewModel.fetchData() }
	}
}
