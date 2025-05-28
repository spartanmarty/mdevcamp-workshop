import SwiftUI
import Shared

struct ParkingDetail: View {

	private let model: ParkingLot
	private let closure: (ParkingLot) -> Void

	init(model: ParkingLot, closure: @escaping (ParkingLot) -> Void) {
		self.model = model
		self.closure = closure
	}

    var body: some View {
		VStack(spacing: 16) {
			Text(model.name)
				.font(.title)
				.fontWeight(.bold)
				.padding(8)

			InfoSection(model: model, type: .detail)
				.frame(maxWidth: .infinity, alignment: .leading)

			ProhibitionRow(items: model.prohibitions)

			// TODO - Button - Add to favorites / Remove from favorites
		}
		.padding(.horizontal, 16)
		.frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .top)
	}
}
