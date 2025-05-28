import SwiftUI
import Shared

struct ProhibitionRow: View {

	let items: [ParkingProhibitions]

	var body: some View {
		Group {
			if !items.isEmpty {
				VStack(alignment: .leading, spacing: 16) {
					Text("label_prohibitions")
						.font(.title2)

					HStack(spacing: 16) {
						ForEach(items, id: \.name) { item in
							switch item {
							case .bicycle:
								prohibitionItem(iconName: "icon_bike")

							case .bus:
								prohibitionItem(iconName: "icon_bus")

							case .lpgCng:
								prohibitionItem(iconName: "icon_lpg")

							case .motorcycle:
								prohibitionItem(iconName: "icon_motorcycle")

							case .trailer:
								prohibitionItem(iconName: "icon_trailer")

							case .truck:
								prohibitionItem(iconName: "icon_truck")

							default:
								EmptyView()
							}
						}
					}
				}
				.frame(maxWidth: .infinity, alignment: .leading)
			}
		}
	}

	private func prohibitionItem(iconName: String) -> some View {
		Image(iconName)
			.resizable()
			.frame(width: 40, height: 40)
			.padding(8)
			.background(Color("secondary"))
			.cornerRadius(8)
	}
}
