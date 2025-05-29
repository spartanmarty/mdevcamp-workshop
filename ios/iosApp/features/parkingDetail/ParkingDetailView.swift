import Shared
import SwiftUI

struct ParkingDetailView: View {

	private let parkId: String

	init(parkId: String) {
		self.parkId = parkId
	}

	var body: some View {
		ParkingDetail(
			model: .init(
				id: "id",
				name: "parking_lot",
				address: "address",
				capacity: 42,
				covered: false,
				isFavorite: false,
				prohibitions: [.bicycle, .bus]
			)
		) { _ in
		}
	}
}
