import SwiftUI

struct RootView: View {

	enum Route: Hashable {
		case parkingDetail(id: String)
	}

	enum Tab {
		case home
		case favorites
		case settings
	}

	@State
	private var path = NavigationPath()

	@State
	private var selectedTab: Tab = .home

	var body: some View {
		TabView(selection: $selectedTab) {
			homeView
				.tabItem {
					Label(
						title: { Text("Parking Lots") },
						icon: { Image("icon_list") }
					)
				}
				.tag(Tab.home)

			favoritesView
				.tabItem {
					Label(
						title: { Text("Favorites Lots") },
						icon: { Image("icon_favorites") }
					)
				}
				.tag(Tab.favorites)
		}
	}

	@ViewBuilder
	private var homeView: some View {
		NavigationStack(path: $path) {
			ParkingListView { parkId in
				path.append(Route.parkingDetail(id: parkId))
			}
			.navigationDestination(for: Route.self) { route in
				switch route {
				case .parkingDetail(let id):
					ParkingDetailView(parkId: id)
						.toolbar(.hidden, for: .tabBar)
				}
			}
		}
	}

	@ViewBuilder
	private var favoritesView: some View {
		NavigationStack(path: $path) {
			FavoritesView { parkId in
				path.append(Route.parkingDetail(id: parkId))
			}
			.navigationDestination(for: Route.self) { route in
				switch route {
				case .parkingDetail(let id):
					ParkingDetailView(parkId: id)
						.toolbar(.hidden, for: .tabBar)
				}
			}
		}
	}
}

#Preview {
    RootView()
}
