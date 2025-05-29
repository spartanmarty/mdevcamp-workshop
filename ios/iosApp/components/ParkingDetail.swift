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

			Button(
				action: { closure(model) },
				label: {
					Text(model.isFavorite ? "favorites_remove" : "favorites_add")
						.font(.callout)
						.fontWeight(.regular)
						.padding(.vertical, 16)
						.frame(maxWidth: .infinity)
						.background(Color("primary"))
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
