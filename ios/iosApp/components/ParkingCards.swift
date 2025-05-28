import SwiftUI
import Shared

struct ParkingCards: View {

	private let parkingLots: [ParkingLot]
	private let closure: (String) -> Void

	init(parkingLots: [ParkingLot], closure: @escaping (String) -> Void) {
		self.parkingLots = parkingLots
		self.closure = closure
	}

	var body: some View {
		ScrollView {
			ForEach(parkingLots, id: \.id) { parkingLot in
				Button(
					action: { closure(parkingLot.id) },
					label: { contentView(model: parkingLot) },
				)
				.buttonStyle(.plain)
			}
			.padding(.horizontal, 16)
			.padding(.vertical, 8)
		}
		.scrollShadowMask(type: .vertical)
	}

	@ViewBuilder
	private func contentView(model: ParkingLot) -> some View {
		VStack(alignment: .leading, spacing: 0) {
			Text(model.name)
				.font(.headline)
				.fontWeight(.bold)
				.padding(.bottom, 8)

			InfoSection(model: model, type: .card)
		}
		.frame(maxWidth: .infinity, alignment: .leading)
		.padding(8)
		.background(Color("secondary"))
		.cornerRadius(8)
	}
}
