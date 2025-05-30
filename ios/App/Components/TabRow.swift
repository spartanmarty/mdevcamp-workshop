import Shared
import SwiftUI

protocol TabItem: Equatable, Identifiable {
	func localizedTitle() -> LocalizedStringKey
}

struct TabRow<Item: TabItem>: View {

	private var selectedItem: Binding<Item>

	private let items: [Item]

	init(items: [Item], selectedItem: Binding<Item>) {
		self.items = items
		self.selectedItem = selectedItem
	}

	var body: some View {
		ScrollView(.horizontal, showsIndicators: false) {
			HStack {
				ForEach(items) { item in
					tabItem(item, isActive: item == selectedItem.wrappedValue)
				}
			}
			.padding(.horizontal, 20)
		}
	}

	private func tabItem(_ item: Item, isActive: Bool) -> some View {
		Button(
			action: { selectedItem.wrappedValue = item },
			label: {
				Text(item.localizedTitle())
					.textCase(.uppercase)
					.font(.headline)
					.padding(12)
					.background(isActive ? Color(.accent) : Color(.backgroundPrimary))
					.foregroundStyle(isActive ? .black : .primary)
					.cornerRadius(8)
					.fixedSize()
			}
		)
		.buttonStyle(.plain)
	}
}

#Preview {
	@Previewable @State var selectedFilter: ParkingPolicyFilter = .noFilter

	TabRow(
		items: ParkingPolicyFilter.allCases,
		selectedItem: $selectedFilter
	)
}
