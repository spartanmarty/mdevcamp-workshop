import SwiftUI
import Shared

struct TabItem: View {

	private let model: ParkingPolicyFilter
	private let isActive: Bool
	private let closure: () -> Void

	init(model: ParkingPolicyFilter, isActive: Bool, closure: @escaping () -> Void = {}) {
		self.model = model
		self.isActive = isActive
		self.closure = closure
	}

    var body: some View {
		Button(
			action: closure,
			label: {
				TextView(text: getTranslate(key: model), font: .headline)
					.padding(16)
					.background(isActive ? Color.orange : Color.yellow)
					.cornerRadius(16)
					.fixedSize()
			}
		)
		.buttonStyle(.plain)
    }

	private func getTranslate(key: ParkingPolicyFilter) -> String {
		switch key {
		case .noFilter:
			"All Parking Lots"
		case .commercial:
			"Commercial"
		case .customerOnly:
			"Customer Only"
		case .parkAndRide:
			"Park & Ride"
		case .kissAndRide:
			"Kiss & Ride"
		case .parkSharing:
			"Park Sharing"
		default:
			"Zone"
		}
	}
}

#Preview {
	ForEach(ParkingPolicyFilter.entries, id: \.value) { model in
		TabItem(model: model, isActive: model == ParkingPolicyFilter.noFilter, closure: {})
	}
}
