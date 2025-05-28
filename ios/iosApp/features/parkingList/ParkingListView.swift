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

			viewModel.viewState.content { parkingLots in
				ListParkingsLotsView(parkingLots: parkingLots, closure: closure)
					.refreshable { await viewModel.fetchData() }
			}
		}
		.frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .top)
		.task { await viewModel.fetchData() }
    }
}
