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
			bottomBar
		}
	}

	@ViewBuilder
	private var bottomBar: some View {
		HStack(spacing: 16) {
			BottomTabItem(tabType: .home, selectedTab: selectedTab) {
				selectedTab = $0
			}

			BottomTabItem(tabType: .favorites, selectedTab: selectedTab) {
				selectedTab = $0
			}
		}
	}

	@ViewBuilder
	private var navigationView: some View {
		NavigationStack(path: $path) {
			content
				.navigationDestination(for: Route.self) { destination(route: $0) }
		}
	}

	@ViewBuilder
	private var content: some View {
		switch selectedTab {
		case .home:
			ParkingListView { parkId in
				path.append(Route.parkingDetail(id: parkId))
			}

		case .favorites:
			FavoritesView { parkId in
				path.append(Route.parkingDetail(id: parkId))
			}
		}
	}

	@ViewBuilder
	private func destination(route: Route) -> some View {
		switch route {
		case .parkingDetail(let id):
			ParkingDetailView(parkId: id)
				.toolbar(.hidden, for: .tabBar)
		}
	}
}
