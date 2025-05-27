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
			TabList(
				tabs: ParkingPolicyFilter.entries,
				selectedTabFilter: viewModel.selectTabFilter
			) {
				viewModel.setSelectTabFilter(filter: $0)
			}

			ScrollView {
				content()
					.padding(.vertical, 8)
			}
			.scrollShadowMask(type: .vertical)
			.refreshable { await viewModel.fetchData() }
		}
		.frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .top)
		.task { await viewModel.fetchData() }
    }

	@ViewBuilder
	private func content() -> some View {
		switch viewModel.viewState {
		case .loading:
			ProgressView()

		case .data(let parkingLots):
			ForEach(parkingLots, id: \.id) { parkingLot in
				ParkingCard(model: parkingLot) {
					closure(parkingLot.id)
				}
			}

		case .error(let error):
			Text(error)
		}
	}
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ParkingListView(closure: { _ in })
    }
}
