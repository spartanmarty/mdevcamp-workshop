import Shared

final class ParkingTabsViewModel: ObservableObject {

	enum ViewState {
		case loading
		case data(model: [TabItemComponentModel])
		case empty
	}

	@Published
	var viewState: ViewState = .loading

	@Published
	var selectedTabId: String?

	func selectTab(id: String) {
		selectedTabId = id
	}

	func fetchData() {
		Task {
			// TODO - Fetch data
			await MainActor.run {
				fetchTabs()
			}
//			do {
//				// handle data
//			} catch {
//				// handle error
//			}
		}
	}

	func fetchTabs() {
		let tabs: [TabItemComponentModel] = [
			.init(id: "OnStreet", name: "OnStreet"),
			.init(id: "Underground", name: "Underground"),
			.init(id: "Multi Storey", name: "Multi Storey"),
		]
		self.viewState = .data(model: tabs)
		self.selectedTabId = tabs.first?.id
	}
}
