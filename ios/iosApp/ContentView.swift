import SwiftUI
import Shared

struct ContentView: View {

    var body: some View {
		VStack(spacing: 16) {
			TabList(
				tabs: [
					.init(name: "OnStreet", isActive: true),
					.init(name: "Underground", isActive: false),
					.init(name: "Multi Storey", isActive: false),
				]
			)

			ScrollView {
				ParkingCard(
					model: .init(
						title: "Parkoviště - Areál Gayerova kasárna",
						capacity: 60,
						address: "nám. Dr. E. Beneše, 46001 Liberec Staré Město, Česko",
						covered: true,
						prohibition: "lpg, motorcycle"
					)
				)

				ParkingCard(
					model: .init(
						title: "Parkoviště - Areál Gayerova kasárna",
						capacity: 60,
						address: "nám. Dr. E. Beneše, 46001 Liberec Staré Město, Česko",
						covered: true,
						prohibition: "lpg, motorcycle"
					)
				)

				ParkingCard(
					model: .init(
						title: "Parkoviště - Areál Gayerova kasárna",
						capacity: 60,
						address: "nám. Dr. E. Beneše, 46001 Liberec Staré Město, Česko",
						covered: true,
						prohibition: "lpg, motorcycle"
					)
				)

				ParkingCard(
					model: .init(
						title: "Parkoviště - Areál Gayerova kasárna",
						capacity: 60,
						address: "nám. Dr. E. Beneše, 46001 Liberec Staré Město, Česko",
						covered: true,
						prohibition: "lpg, motorcycle"
					)
				)

				ParkingCard(
					model: .init(
						title: "Parkoviště - Areál Gayerova kasárna",
						capacity: 60,
						address: "nám. Dr. E. Beneše, 46001 Liberec Staré Město, Česko",
						covered: true,
						prohibition: "lpg, motorcycle"
					)
				)
			}
			.mask(
				VStack(spacing: 0) {
					LinearGradient(
						colors: [.clear, .black],
						startPoint: .top,
						endPoint: .bottom
					)
					.frame(height: 16)

					Rectangle()
						.foregroundColor(.black)

					LinearGradient(
						colors: [.black, .clear],
						startPoint: .top,
						endPoint: .bottom
					)
					.frame(height: 32)
				}
			)
			.ignoresSafeArea(edges: .bottom)
        }
        .frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .top)
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
