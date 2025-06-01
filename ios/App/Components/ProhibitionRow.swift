import Shared
import SwiftUI

struct ProhibitionRow: View {

	let items: [ParkingProhibitions]

	private var iconsResolver: ProhibitionIconResolver = inject()

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

	init(items: [ParkingProhibitions]) {
		self.items = items
	}

	@ViewBuilder
	private func prohibitionItem(_ item: ParkingProhibitions) -> some View {
		let icon = iconsResolver.resolveIcon(prohibition: item)
		Image(uiImage: icon)
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
