import Shared
import SwiftUI

struct ParkingDetail: View {

	@StateObject
	private var viewModel: SharedViewModelStoreOwner<ParkingLotDetailViewModel> = .init()

	private let parkingLotId: String

	init(parkingLotId: String) {
		self.parkingLotId = parkingLotId
	}

	var body: some View {
		Observing(viewModel.instance.state) { state in
			switch onEnum(of: state) {
				case let .data(model):
				if let parkingLot = model.parkingLots.first {
					content(parkingLot)
				}

			case .loading:
				Loading()

			case let .error(error):
				ErrorMessage(error: error)
			}
		}
		.onAppear {
			viewModel.instance.loadParkingLotDetail(id: parkingLotId)
		}
	}

	@ViewBuilder
	private func content(_ model: ParkingLot) -> some View {
		VStack(spacing: 16) {
			Text(model.name)
				.font(.title)
				.fontWeight(.bold)
				.padding(8)

			InfoSection(model: model, type: .detail)
				.frame(maxWidth: .infinity, alignment: .leading)

			if !model.prohibitions.isEmpty {
				ProhibitionRow(items: model.prohibitions)
			}

			Button(
				action: { viewModel.instance.toggleFavorite(parkingLot: model) },
				label: {
					Text(model.isFavorite ? L10n.favorites_remove : L10n.favorites_add)
						.font(.callout)
						.textCase(.uppercase)
						.fontWeight(.bold)
						.padding()
						.frame(maxWidth: .infinity)
						.background(Color(.accent))
						.cornerRadius(24)
				}
			)
			.buttonStyle(.plain)
			.padding(.vertical, 16)
			.padding(.horizontal, 24)
		}
		.padding(.horizontal, 16)
		.frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .top)
	}
}
