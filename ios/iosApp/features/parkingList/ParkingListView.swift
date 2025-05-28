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
			TabRow(
				tabs: ParkingPolicyFilter.entries,
				selectedTabFilter: viewModel.selectTabFilter,
				closure: { viewModel.setSelectTabFilter(filter: $0) }
			)

			viewModel.viewState.content {
				ParkingCards(parkingLots: $0, closure: closure)
			}
			.refreshable { await viewModel.fetchData() }
		}
		.task { await viewModel.fetchData() }
    }
}
