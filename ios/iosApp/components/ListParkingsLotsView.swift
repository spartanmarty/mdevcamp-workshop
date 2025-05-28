import SwiftUI
import Shared

struct ListParkingsLotsView: View {

	private let parkingLots: [ParkingLot]
	private let closure: (String) -> Void

	init(parkingLots: [ParkingLot], closure: @escaping (String) -> Void) {
		self.parkingLots = parkingLots
		self.closure = closure
	}

	var body: some View {
		ScrollView {
			ForEach(parkingLots, id: \.id) { parkingLot in
				ParkingCard(model: parkingLot) {
					closure(parkingLot.id)
				}
			}
			.padding(.vertical, 8)
		}
		.scrollShadowMask(type: .vertical)
	}
}
