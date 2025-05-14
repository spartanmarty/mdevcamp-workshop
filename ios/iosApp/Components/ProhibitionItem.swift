import SwiftUI

struct ProhibitionItem: View {

	private let model: String

	init(model: String = "circle") {
		self.model = model
	}

	var body: some View {
		Image(systemName: model)
			.resizable()
			.frame(width: 20, height: 20)
			.padding(8)
			.background(Color.green.opacity(0.4))
			.cornerRadius(8)
			.padding(8)
	}
}

#Preview {
	ProhibitionItem()
}
