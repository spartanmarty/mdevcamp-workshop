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
		VStack(spacing: 0) {
			Text(model.name)
				.font(.caption)
				.fontWeight(.regular)

			VStack(alignment: .leading, spacing: 4) {
				ParkInfoRow(title: "label_capacity", value: "\(model.capacity)")

				if let address = model.address {
					ParkInfoRow(title: "label_address", value: address)
				}

				if let covered = model.covered {
					ParkInfoRow(title: "label_covered", value: covered == true ? "covered_yes" : "covered_no")
				}
			}
			.padding(16)
			.frame(maxWidth: .infinity, alignment: .leading)

//			sectionProhibition(items: model.prohibitions)

			Button(
				action: { closure(model) },
				label: {
					Text(model.isFavorite ? "favorites_remove" : "favorites_add")
						.font(.caption)
						.fontWeight(.regular)
						.padding(16)
						.background(Color.yellow)
						.cornerRadius(16)
				}
			)
		}
		.frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .top)
    }

	private func sectionProhibition(items: [ProhibitionComponentModel]) -> some View {
		Group {
			if !items.isEmpty {
				VStack(alignment: .leading, spacing: 0) {
					Text("label_prohibitions")
						.font(.body)

					HStack(spacing: 0) {
						ForEach(items, id: \.id) { item in
							ProhibitionItem()
						}
					}
				}
				.padding(.top, 32)
				.padding(.horizontal, 16)
				.frame(maxWidth: .infinity, alignment: .leading)
			}
		}
	}
}
