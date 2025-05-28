import Shared
import SwiftUI

extension ViewStateType {

	@ViewBuilder
	func content<DataView: View>(
		dataView: @escaping ([ParkingLot]) -> DataView
	) -> some View {
		switch self {
		case .loading:
			ProgressView()

		case .data(let parkingLots):
			dataView(parkingLots)

		case let .error(err):
			Text(err.getTranslate())
		}
	}

}
