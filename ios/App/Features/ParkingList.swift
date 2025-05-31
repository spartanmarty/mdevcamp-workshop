import Shared
import SwiftUI

struct ParkingList: View {

	@StateObject
	private var viewModel: SharedViewModelStoreOwner<ParkingLotsViewModel> = .init()

	@State
	private var selectedFilter: ParkingPolicyFilter = .allCases[0]

	var body: some View {
		VStack {
			TabRow(items: ParkingPolicyFilter.allCases, selectedItem: $selectedFilter)
			Observing(viewModel.instance.state) { state in
				content(state)
			}
			.frame(maxHeight: .infinity)
		}
		.onChange(of: selectedFilter, initial: true) { _, newValue in
			viewModel.instance.onFilterChange(filter: newValue)
		}
	}

	@ViewBuilder
	private func content(_ state: ParkingLotsState) -> some View {
		switch onEnum(of: state) {
		case let .data(data):
			List(data.parkingLots, id: \.id) { model in
				ParkingLotCard(model: model)
			}
			.listStyle(.plain)

		case .loading:
			Loading()

		case let .error(error):
			ErrorMessage(error: error)
		}
	}
}
