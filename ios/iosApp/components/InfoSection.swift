import SwiftUI
import Shared

struct InfoSection: View {

	enum RowType {
		case detail
		case card
	}

	let model: ParkingLot

	let spacing: CGFloat
	let font: Font
	let lineLimit: Int?

	init(model: ParkingLot, type: RowType) {
		self.model = model
		switch type {
		case .detail:
			self.spacing = 8
			self.font = .headline
			self.lineLimit = nil

		case .card:
			self.spacing = 6
			self.font = .footnote
			self.lineLimit = 1
		}
	}

	var body: some View {
		VStack(alignment: .leading, spacing: spacing) {
			infoRow(title: "label_capacity", value: "\(model.capacity)")

			if let address = model.address {
				infoRow(title: "label_address", value: address)
			}

			if let covered = model.covered {
				infoRow(title: "label_covered", value: covered == true ? "covered_yes" : "covered_no")
			}
		}
	}

	func infoRow(title: String, value: String) -> some View {
		HStack(alignment: .top) {
			Text(LocalizedStringKey(title))
				.font(font)
				.fontWeight(.regular)

			Text(LocalizedStringKey(value))
				.font(font)
				.fontWeight(.bold)
		}
		.lineLimit(lineLimit)
	}

	
}

