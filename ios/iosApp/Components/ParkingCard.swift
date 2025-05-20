import SwiftUI
import Shared

struct ParkingCard: View {

	private let model: ParkingCardComponentModel
	private let closure: () -> Void

	init(model: ParkingCardComponentModel, closure: @escaping () -> Void = {}) {
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
			TextView(text: model.title, weight: .bold)
			ForEach(model.rows, id: \.id) { row in
				HStack {
					TextView(text: row.title)
					TextView(text: row.value, weight: .bold)
				}
			}
		}
		.frame(maxWidth: .infinity, alignment: .leading)
		.padding(8)
		.background(Color.yellow)
		.cornerRadius(8)
		.padding(.horizontal, 16)
	}
}

#Preview {
	ParkingCard(model: .mock)
}
