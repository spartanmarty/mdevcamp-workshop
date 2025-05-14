import Foundation

struct ParkingCardModel: Equatable, Identifiable {
	let id: String = UUID().uuidString
	let title: String
	let capacity: Int
	let address: String
	let covered: Bool
	let prohibition: String?
}
