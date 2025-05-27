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
			TextView(text: model.name)

			VStack(alignment: .leading, spacing: 4) {
				row(title: "capacity", value: "\(model.capacity)")

				if let address = model.address {
					row(title: "address", value: address)
				}

				if let covered = model.covered {
					row(title: "covered", value: covered == true ? "YES" : "NO")
				}
			}
			.padding(16)
			.frame(maxWidth: .infinity, alignment: .leading)

//			sectionProhibition(items: model.prohibitions)

			Button(
				action: { closure(model) },
				label: {
					TextView(text: model.isFavorite ? "Remove from favorites" : "Add to favorites")
						.padding(16)
						.background(Color.yellow)
						.cornerRadius(16)
				}
			)
		}
		.frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .top)
    }

	@ViewBuilder
	private func row(title: String, value: String) -> some View {
		HStack {
			TextView(text: title)
			TextView(text: value, weight: .bold)
		}
	}

	private func sectionProhibition(items: [ProhibitionComponentModel]) -> some View {
		Group {
			if !items.isEmpty {
				VStack(alignment: .leading, spacing: 0) {
					Text("Prohibition")
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

#Preview {
//	ParkingDetail(model: .mock)
}
