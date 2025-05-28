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
			TextView(text: "Favorites", font: .headline, weight: .bold)
				.frame(maxWidth: .infinity, alignment: .leading)

			viewModel.viewState.content { parkingLots in
				ListParkingsLotsView(parkingLots: parkingLots, closure: closure)
					.refreshable { await viewModel.fetchData() }
			}
		}
		.frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .top)
		.task { await viewModel.fetchData() }
	}
}
