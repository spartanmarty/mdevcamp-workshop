import SwiftUI

struct TabList: View {

	private let tabs: [TabItemModel]

	init(tabs: [TabItemModel] = []) {
		self.tabs = tabs
	}

    var body: some View {
		ScrollView(.horizontal, showsIndicators: false) {
			HStack {
				ForEach(tabs) { tab in
					TabItem(model: tab)
				}
			}
			.padding(.horizontal, 16)
		}
		.mask(
			HStack(spacing: 0) {
				LinearGradient(
					colors: [.clear, .black],
					startPoint: .leading,
					endPoint: .trailing
				)
				.frame(width: 16)

				Rectangle()
					.foregroundColor(.black)

				LinearGradient(
					colors: [.black, .clear],
					startPoint: .leading,
					endPoint: .trailing
				)
				.frame(width: 16)
			}
		)
    }
}

#Preview {
	TabList(
		tabs: [
			.init(name: "OnStreet", isActive: true),
			.init(name: "Underground", isActive: false),
			.init(name: "Multi Storey", isActive: false),
		]
	)

}
