import SwiftUI
import Shared

public struct ParkingDetailView: View {

	@StateObject
	private var viewModel = ParkingDetailViewModel()
	private let parkId: String

	init(parkId: String) {
		self.parkId = parkId
	}

	public var body: some View {
		content()
			.task { await viewModel.fetchData(parkId: parkId) }
    }

	@ViewBuilder
	private func content() -> some View {
		switch viewModel.viewState {
		case .loading:
			ProgressView()

		case let .data(model):
			ParkingDetail(model: model) { parkingLot in
				viewModel.toggleFavorite(parkingLot)
			}

		case let .error(error):
			Text(error)
		}
	}
}
