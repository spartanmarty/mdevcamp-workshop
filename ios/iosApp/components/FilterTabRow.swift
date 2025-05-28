import SwiftUI
import Shared

struct TabRow: View {

	private let tabs: [ParkingPolicyFilter]
	private var selectedTabFilter: ParkingPolicyFilter
	private let closure: (ParkingPolicyFilter) -> Void

	init(
		tabs: [ParkingPolicyFilter],
		selectedTabFilter: ParkingPolicyFilter,
		closure: @escaping (ParkingPolicyFilter) -> Void
	) {
		self.tabs = tabs
		self.selectedTabFilter = selectedTabFilter
		self.closure = closure
	}

    var body: some View {
		ScrollView(.horizontal, showsIndicators: false) {
			HStack {
				ForEach(tabs, id: \.value) { tab in
					tabItem(model: tab, isActive: tab == selectedTabFilter) {
						closure(tab)
					}
				}
			}
			.padding(16)
		}
		.scrollShadowMask(type: .horizontal)
    }

	private func tabItem(model: ParkingPolicyFilter, isActive: Bool, closure: @escaping () -> Void) -> some View {
		Button(
			action: closure,
			label: {
				Text(model.getTranslate())
					.font(.headline)
					.fontWeight(.regular)
					.padding(16)
					.background(isActive ? Color("primary") : Color("primary_highlighted"))
					.cornerRadius(16)
					.fixedSize()
			}
		)
		.buttonStyle(.plain)
	}
}

#Preview {
	TabRow(
		tabs: ParkingPolicyFilter.entries,
		selectedTabFilter: ParkingPolicyFilter.noFilter,
		closure: { _ in }
	)
}
