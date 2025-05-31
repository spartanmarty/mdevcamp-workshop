import Shared
import SwiftUI

extension ParkingLotsState.Error {

	func localizedMessage() -> LocalizedStringKey {
		switch type {
		case .noDataFound:
			L10n.error_message_no_data

		case .network:
			L10n.error_message_network

		case .unknown:
			L10n.value_unknown
		}
	}
}
