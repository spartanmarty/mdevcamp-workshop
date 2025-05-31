import Shared
import SwiftUI

struct ProhibitionRow: View {

	let items: [ParkingProhibitions]

	var body: some View {
		VStack(alignment: .leading, spacing: 16) {
			Text(L10n.label_prohibitions)
				.font(.title2)

			HStack(spacing: 16) {
				ForEach(items, id: \.name) { item in
					prohibitionItem(item)
				}
			}
		}
		.frame(maxWidth: .infinity, alignment: .leading)
	}

	@ViewBuilder
	private func prohibitionItem(_ item: ParkingProhibitions) -> some View {
		#warning("TODO: use ProhibitionIconResolver for correct icon")
		let resource = ImageResource.iconWarning
		Image(resource)
			.resizable()
			.frame(width: 32, height: 32)
			.padding(8)
			.background(Color(.backgroundPrimary))
			.cornerRadius(8)
	}
}

#Preview {
	ProhibitionRow(items: ParkingProhibitions.allCases)
}
