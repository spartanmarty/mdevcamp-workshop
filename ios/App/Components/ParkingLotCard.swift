import Shared
import SwiftUI

struct ParkingLotCard: View {

	let model: ParkingLot

	var body: some View {
		VStack(alignment: .leading, spacing: 0) {
			Text(model.name)
				.font(.headline)
				.fontWeight(.bold)
				.padding(.bottom, 8)

			InfoSection(model: model, type: .card)
		}
		.padding()
		.frame(height: 110, alignment: .top)
		.frame(maxWidth: .infinity, alignment: .leading)
		.background(Color(.backgroundSecondary))
		.cornerRadius(16)
		.background { // hack to remove unwanted arrows in the list
			NavigationLink(
				destination: { ParkingDetail(parkingLotId: model.id) },
				label: {}
			)
		}
		.listRowSeparator(.hidden)
		.listRowBackground(Color.clear)
	}
}

#Preview {
	ParkingLotCard(
		model: .init(
			id: "id",
			name: "ParkingLotName",
			address: "address",
			capacity: 42,
			covered: true,
			isFavorite: false,
			prohibitions: [
				.bicycle,
				.bus,
				.motorcycle
			]
		)
	)
}
