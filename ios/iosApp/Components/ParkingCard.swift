import SwiftUI

struct ParkingCard: View {

	private let model: ParkingCardModel
	private let closure: () -> Void

	init(model: ParkingCardModel, closure: @escaping () -> Void = {}) {
		self.model = model
		self.closure = closure
	}

	var body: some View {
		Button(
			action: closure,
			label: {
				VStack(alignment: .leading, spacing: 2) {
					row(keyText: nil, valueText: model.title)
					row(keyText: "Capacity", valueText: "\(model.capacity)")
					row(keyText: "address", valueText: model.address)
					row(keyText: "covered", valueText: "\(model.covered)")
					row(keyText: "prohibition", valueText: model.prohibition ?? "")
				}
				.frame(maxWidth: .infinity, alignment: .leading)
				.padding(8)
				.background(Color.green.opacity(0.4))
				.cornerRadius(8)
				.padding(.horizontal, 16)
			}
		)
		.buttonStyle(.plain)

	}

	private func row(keyText: String?, valueText: String, isBold: Bool = true) -> some View {
		HStack {
			if let keyText {
				Text("\(keyText): ")
					.font(.caption)
			}
			Text(valueText)
				.font(.caption)
				.fontWeight(.bold)
		}
	}
}

#Preview {
	ParkingCard(
		model: .init(
			title: "Parkoviště - Areál Gayerova kasárna",
			capacity: 60,
			address: "nám. Dr. E. Beneše, 46001 Liberec Staré Město, Česko",
			covered: true,
			prohibition: "lpg, motorcycle"
		)
	)
}
