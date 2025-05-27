import SwiftUI
import Shared

struct ParkingListView: View {

	@StateObject
	private var viewModel = ParkingListViewModel()

	private let closure: (String) -> Void

	init(closure: @escaping (String) -> Void) {
		self.closure = closure
	}

    var body: some View {
		VStack(spacing: 0) {
//			TabsView()
			ScrollView {
				Text("\(viewModel.viewState)")
//				switch viewModel.viewState {
//				case .loading:
//					ProgressView()
//
//				case .data(let parkings):
//					ForEach(parkings, id: \.id) { parkCard in
//						ParkingCard(model: parkCard) {
//							closure(parkCard.id)
//						}
//					}
//					.padding(.vertical, 16)
//
//				case .empty:
//					EmptyView()
//				}
			}
			.scrollShadowMask(type: .vertical)
			.refreshable { await viewModel.fetchData() }
		}
		.frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .top)
		.task { await viewModel.fetchData() }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ParkingListView(closure: { _ in })
    }
}
