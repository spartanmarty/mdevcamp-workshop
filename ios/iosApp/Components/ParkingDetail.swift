import SwiftUI
import Shared

struct ParkingDetail: View {

	let model: ParkingDetailComponentModel

    var body: some View {
		VStack(spacing: 0) {
			textView(text: model.title)
			detail(rows: model.rows)
			sectionProhibition
		}
		.frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .top)
    }

	private func detail(rows: [RowComponentModel]) -> some View {
			VStack(alignment: .leading, spacing: 4) {
				ForEach(rows, id: \.id) { row in
					HStack {
						textView(text: row.title)
						textView(text: row.value, weight: .bold)
					}
				}
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

	private func textView(
		text: String, font: Font = .caption, weight: Font.Weight = .regular
	) -> some View {
		Text(text)
			.font(font)
			.fontWeight(weight)
	}
	
}

#Preview {
	ParkingDetail(model: .mock)
}
