import Shared
import SwiftUI

extension ViewStateType {

	@ViewBuilder
	func content<DataView: View>(
		dataView: @escaping ([ParkingLot]) -> DataView
	) -> some View {
		switch self {
		case .loading:
			VStack(alignment: .center, spacing: 5) {
				ProgressView()
				Text("label_loading")
			}
			.frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .center)

		case .data(let parkingLots):
			dataView(parkingLots)

		case let .error(err):
			VStack(alignment: .center, spacing: 5) {
				Image("icon_warning")
					.resizable()
					.frame(width: 80, height: 80)

				Text(err.getTranslate())
			}
			.frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .center)
		}
	}

}
