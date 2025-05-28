import SwiftUI
import Shared

struct ParkingDetailView: View {

	@StateObject
	private var viewModel = ParkingDetailViewModel()
	private let parkId: String

	init(parkId: String) {
		self.parkId = parkId
	}

	var body: some View {
		viewModel.viewState.content { parkingLots in
			Group {
				if let model = parkingLots.first {
					ParkingDetail(model: model) { parkingLot in
						viewModel.toggleFavorite(parkingLot)
					}
				} else {
					EmptyView()
				}
			}
		}
		.task { await viewModel.fetchData(parkId: parkId) }
    }
}
