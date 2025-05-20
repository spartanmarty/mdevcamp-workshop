import SwiftUI

struct RootView: View {

	enum Route: Hashable {
		case parkingDetail(id: String)
		case someOtherView
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
				.tabItem { Label("Parking", systemImage: "car.fill") }
				.tag(Tab.home)

			FavoritesView()
				.tabItem { Label("Favorites", systemImage: "star.fill") }
				.tag(Tab.favorites)

			SettingsView()
				.tabItem { Label("Settings", systemImage: "gear") }
				.tag(Tab.settings)
		}
	}

	@ViewBuilder
	private var homeView: some View {
		NavigationStack(path: $path) {
			VStack(spacing: 0) {
				ParkingListView { parkId in
					path.append(Route.parkingDetail(id: parkId))
				}

				Button(
					action: {
						path.append(Route.someOtherView)
					},
					label: {
						Text("Open some other View")
							.padding(16)
					}
				)
			}
			.navigationDestination(for: Route.self) { route in
				switch route {
				case .parkingDetail(let id):
					ParkingDetailView(parkId: id)
						.toolbar(.hidden, for: .tabBar)

				case .someOtherView:
					SomeOtherView()
						.toolbar(.hidden, for: .tabBar)
				}
			}
		}
	}
}

#Preview {
    RootView()
}
