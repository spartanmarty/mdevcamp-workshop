import SwiftUI

struct ContentView: View {

	enum Route: Hashable {
		case parkingDetail(id: String)
	}

	enum Tab {
		case home
		case favorites
	}

	@State
	private var path = NavigationPath()

	@State
	private var selectedTab: Tab = .home

	var body: some View {
		VStack(spacing: 0) {
			navigationView
			if path.isEmpty {
				bottomBar
			}
		}
	}

	@ViewBuilder
	private var bottomBar: some View {
		HStack(spacing: 16) {
			BottomTabItem(tabType: .home, selectedTab: selectedTab) { selectedTab = $0 }
			BottomTabItem(tabType: .favorites, selectedTab: selectedTab) { selectedTab = $0 }
		}
	}

	@ViewBuilder
	private var navigationView: some View {
		NavigationStack(path: $path) {
			content
				.frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .top)
				.navigationDestination(for: Route.self) {
					destination(route: $0)
				}
		}
	}

	@ViewBuilder
	private var content: some View {
		switch selectedTab {
		case .home:
			ParkingListView { path.append(Route.parkingDetail(id: $0)) }

		case .favorites:
			FavoritesView { path.append(Route.parkingDetail(id: $0)) }
		}
	}

	@ViewBuilder
	private func destination(route: Route) -> some View {
		switch route {
		case .parkingDetail(let id):
			ParkingDetailView(parkId: id)
		}
	}
}
