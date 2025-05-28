import SwiftUI
import Shared

struct BottomTabItem: View {

	let title: LocalizedStringKey
	let iconName: String
	let isSelected: Bool
	let tabType: ContentView.Tab
	let closure: (ContentView.Tab) -> Void

	init(tabType: ContentView.Tab, selectedTab: ContentView.Tab, closure: @escaping (ContentView.Tab) -> Void) {
		self.tabType = tabType
		switch tabType {
		case .home:
			self.title = "label_parking_lots"
			self.iconName = "icon_parking"


		case .favorites:
			self.title = "label_favorites"
			self.iconName = "icon_favorites"
		}
		
		self.isSelected = selectedTab == tabType
		self.closure = closure
	}

	var body: some View {
		Button {
			closure(tabType)
		} label: {
			VStack {
				Image(iconName)
					.resizable()
					.frame(width: 40, height: 40)
					.background(
						Group {
							if isSelected {
								RoundedRectangle(cornerRadius: 25)
									.fill(Color("primary"))
									.frame(width: 70, height: 40)
							} else {
								Color.clear
							}
						}
					)

				Text(title)
					.font(.caption)
			}
			.padding(.vertical, 8)
			.frame(maxWidth: .infinity)
		}
		.buttonStyle(.plain)
	}
}
