import SwiftUI
import Shared

struct ParkingCard: View {

	private let model: ParkingLot
	private let closure: () -> Void

	init(model: ParkingLot, closure: @escaping () -> Void = {}) {
		self.model = model
		self.closure = closure
	}

	var body: some View {
		Button(
			action: closure,
			label: contentView,
		)
		.buttonStyle(.plain)
	}

	@ViewBuilder
	private func contentView() -> some View {
		VStack(alignment: .leading, spacing: 2) {
			Text(model.name)
				.font(.caption)
				.fontWeight(.bold)

			ParkInfoRow(title: "label_capacity", value: "\(model.capacity)")

			if let address = model.address {
				ParkInfoRow(title: "label_address", value: address)
			}

			if let covered = model.covered {
				ParkInfoRow(title: "label_covered", value: covered == true ? "covered_yes" : "covered_no")
			}
		}
		.frame(maxWidth: .infinity, alignment: .leading)
		.padding(8)
		.background(Color.yellow)
		.cornerRadius(8)
		.padding(.horizontal, 16)
	}
}
