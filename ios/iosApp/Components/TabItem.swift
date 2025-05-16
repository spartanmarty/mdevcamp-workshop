import SwiftUI
import Shared

struct TabItem: View {

	private let model: TabItemComponentModel
	private let isActive: Bool
	private let closure: () -> Void

	init(model: TabItemComponentModel, isActive: Bool, closure: @escaping () -> Void = {}) {
		self.model = model
		self.isActive = isActive
		self.closure = closure
	}

    var body: some View {
		Button(
			action: closure,
			label: {
				TextView(text: model.name, font: .headline)
					.padding(16)
					.background(Color.green.opacity(isActive ? 1.0 : 0.4))
					.cornerRadius(16)
					.fixedSize()
			}
		)
		.buttonStyle(.plain)
    }
}

#Preview {
	TabItem(model: .init(id: "", name: "In-Active"), isActive: true)
	TabItem(model: .init(id: "", name: "Active"), isActive: false)
}
