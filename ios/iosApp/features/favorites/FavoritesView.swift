import SwiftUI

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

			ScrollView {
				content()
					.padding(.vertical, 8)
			}
			.scrollShadowMask(type: .vertical)
			.refreshable { await viewModel.fetchData() }
		}
		.frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .top)
		.task { await viewModel.fetchData() }
	}

	@ViewBuilder
	private func content() -> some View {
		switch viewModel.viewState {
		case .loading:
			ProgressView()

		case .data(let parkingLots):
			ForEach(parkingLots, id: \.id) { parkingLot in
				ParkingCard(model: parkingLot) {
					closure(parkingLot.id)
				}
			}

		case .error(let error):
			Text(error)
		}
	}
}

#Preview {
//	FavoritesView()
}
