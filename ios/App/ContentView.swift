import SwiftUI

struct ContentView: View {

	var body: some View {
		TabView {
			Tab(L10n.label_parking_lots, image: "icon_parking") {
				NavigationStack {
					ParkingList()
				}
			}

			Tab(L10n.label_favorites, image: "icon_favorites") {
				NavigationStack {
					Favorites()
				}
			}
		}
	}
}
