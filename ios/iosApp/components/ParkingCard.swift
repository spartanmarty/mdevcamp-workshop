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
			TextView(text: model.name, weight: .bold)
			row(title: "capacity", value: "\(model.capacity)")

			if let address = model.address {
				row(title: "address", value: address)
			}

			if let covered = model.covered {
				row(title: "covered", value: covered == true ? "YES" : "NO")
			}
		}
		.frame(maxWidth: .infinity, alignment: .leading)
		.padding(8)
		.background(Color.yellow)
		.cornerRadius(8)
		.padding(.horizontal, 16)
	}

	@ViewBuilder
	private func row(title: String, value: String) -> some View {
		HStack {
			TextView(text: title)
			TextView(text: value, weight: .bold)
		}
	}
}

#Preview {
//	ParkingCard(model: .mock)
}
