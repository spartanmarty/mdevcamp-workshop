import SwiftUI

struct ParkingDetail: View {

	private let model: ParkingCardModel

	init(model: ParkingCardModel) {
		self.model = model
	}

    var body: some View {
		VStack(spacing: 0) {
			title
			detail
			sectionProhibition
		}
		.frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .top)
    }

	private var title: some View {
		Text(model.title)
			.font(.title)
	}

	private var detail: some View {
		VStack(alignment: .leading, spacing: 4) {
			row(keyText: "Capacity", valueText: "\(model.capacity)")
			row(keyText: "address", valueText: model.address)
			row(keyText: "covered", valueText: "\(model.covered)")
			row(keyText: "prohibition", valueText: model.prohibition ?? "")
		}
		.padding(16)
		.frame(maxWidth: .infinity, alignment: .leading)
	}

	private var sectionProhibition: some View {
		VStack(alignment: .leading, spacing: 0) {
			Text("Prohibition")
				.font(.body)

			HStack(spacing: 0) {
				ProhibitionItem()
				ProhibitionItem()
				ProhibitionItem()
				ProhibitionItem()
				ProhibitionItem()
				ProhibitionItem()
			}


		}
		.padding(.top, 32)
		.padding(.horizontal, 16)
		.frame(maxWidth: .infinity, alignment: .leading)
	}

	private func row(keyText: String?, valueText: String, isBold: Bool = true) -> some View {
		HStack(alignment: .top) {
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
    ParkingDetail(
		model: .init(
			title: "Parkoviště - Areál Gayerova kasárna",
			capacity: 60,
			address: "nám. Dr. E. Beneše, 46001 Liberec Staré Město, Česko",
			covered: true,
			prohibition: "lpg, motorcycle"
		)
	)
}
