import SwiftUI
import Shared

struct TabsView: View {

	@StateObject
	private var viewModel = ParkingTabsViewModel()

	var body: some View {
		Group {
			switch viewModel.viewState {
			case .loading:
				ProgressView()

			case .data(let tabs):
				TabList(tabs: tabs, selectedTabId: viewModel.selectedTabId) { tabId in
					viewModel.selectTab(id: tabId)
				}

			case .empty:
				EmptyView()
			}
		}
		.task { viewModel.fetchData() }
	}
}

#Preview {
	TabsView()
}
