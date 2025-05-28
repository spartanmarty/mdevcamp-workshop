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
		TabView(selection: $selectedTab) {
			buildStack {
				ParkingListView { parkId in
					path.append(Route.parkingDetail(id: parkId))
				}
			}
			.tabItem {
				Label(
					title: { Text("label_parking_lots") },
					icon: { Image("icon_parking") }
				)
			}
			.tag(Tab.home)

			buildStack {
				FavoritesView { parkId in
					path.append(Route.parkingDetail(id: parkId))
				}
			}
			.tabItem {
				Label(
					title: { Text("Favorites Lots") },
					icon: { Image("icon_favorites") }
				)
			}
			.tag(Tab.favorites)
		}
	}

	private func buildStack<Content: View>(@ViewBuilder content: @escaping () -> Content) -> some View {
		NavigationStack(path: $path) {
			content()
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
