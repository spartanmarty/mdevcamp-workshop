import SwiftUI

struct TabItem: View {

	private let model: TabItemModel
	private let closure: () -> Void

	init(model: TabItemModel, closure: @escaping () -> Void = {}) {
		self.model = model
		self.closure = closure
	}

    var body: some View {
		Button(
			action: closure,
			label: {
				Text(model.name)
					.padding(16)
					.background(Color.green.opacity(model.isActive ? 1.0 : 0.4))
					.cornerRadius(16)
					.fixedSize()
			}
		)
		.buttonStyle(.plain)
    }
}

#Preview {
	TabItem(model: .init(name: "In-Active", isActive: false))
	TabItem(model: .init(name: "Active", isActive: true))
}
