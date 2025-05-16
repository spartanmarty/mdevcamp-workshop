import SwiftUI
import Shared

struct ParkingDetail: View {

	let model: ParkingDetailComponentModel

    var body: some View {
		VStack(spacing: 0) {
			TextView(text: model.title)
			detail(rows: model.rows)
			sectionProhibition(items: model.prohibitions)
		}
		.frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .top)
    }

	private func detail(rows: [RowComponentModel]) -> some View {
			VStack(alignment: .leading, spacing: 4) {
				ForEach(rows, id: \.id) { row in
					HStack {
						TextView(text: row.title)
						TextView(text: row.value, weight: .bold)
					}
				}
			}
			.padding(16)
			.frame(maxWidth: .infinity, alignment: .leading)
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
			else {
				ProgressView()
					.frame(maxWidth: .infinity, alignment: .center)
			}
		}
	}
}

#Preview {
	ParkingDetail(model: .mock)
}
