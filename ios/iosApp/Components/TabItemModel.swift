import Foundation

struct TabItemModel: Equatable, Identifiable {
	let id: String = UUID().uuidString
	let name: String
	let isActive: Bool
}
