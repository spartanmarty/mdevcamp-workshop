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
				Text(model.getTranslate())
					.font(.headline)
					.fontWeight(.regular)
					.padding(16)
					.background(isActive ? Color.orange : Color.yellow)
					.cornerRadius(16)
					.fixedSize()
			}
		)
		.buttonStyle(.plain)
    }
}

#Preview {
	ForEach(ParkingPolicyFilter.entries, id: \.value) { model in
		TabItem(model: model, isActive: model == ParkingPolicyFilter.noFilter, closure: {})
	}
}
