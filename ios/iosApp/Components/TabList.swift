import SwiftUI
import Shared

struct TabList: View {

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
					TabItem(model: tab, isActive: tab == selectedTabFilter) {
						closure(tab)
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
		tabs: ParkingPolicyFilter.entries,
		selectedTabFilter: ParkingPolicyFilter.noFilter,
		closure: { _ in }
	)
}
