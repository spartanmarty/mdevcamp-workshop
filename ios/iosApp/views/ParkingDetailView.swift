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
		Group {
			switch viewModel.viewState {
			case .loading:
				ProgressView()

			case let .data(model):
				ParkingDetail(model: model)

			case .empty:
				Text("No data available")
			}
		}
		.task {
			viewModel.fetchData(parkId: parkId)
		}
    }
}
