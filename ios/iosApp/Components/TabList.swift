import SwiftUI
import Shared

struct TabList: View {

	private let tabs: [TabItemComponentModel]
	private var selectedTabId: String?
	private let closure: (String) -> Void

	init(tabs: [TabItemComponentModel], selectedTabId: String?, closure: @escaping (String) -> Void) {
		self.tabs = tabs
		self.selectedTabId = selectedTabId
		self.closure = closure
	}

    var body: some View {
		ScrollView(.horizontal, showsIndicators: false) {
			HStack {
				ForEach(tabs, id: \.id) { tab in
					TabItem(model: tab, isActive: tab.id == selectedTabId) {
						closure(tab.id)
					}
				}
			}
			.padding(16)
		}
		.scrollShadowMask(type: .horizontal)
    }
}

#Preview {
	TabList(
		tabs: .mock,
		selectedTabId: "OnStreet",
		closure: { _ in }
	)

}
