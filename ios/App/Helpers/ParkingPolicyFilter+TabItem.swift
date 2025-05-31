import Shared
import SwiftUI

extension ParkingPolicyFilter: @retroactive Identifiable {}
extension ParkingPolicyFilter: TabItem {

	public var id: String {
		value
	}

	func localizedTitle() -> LocalizedStringKey {
		switch self {
		case .noFilter:
			L10n.parking_filter_no_filter

		case .commercial:
			L10n.parking_filter_commercial

		case .customerOnly:
			L10n.parking_filter_customer_only

		case .parkAndRide:
			L10n.parking_filter_park_and_ride

		case .kissAndRide:
			L10n.parking_filter_kiss_and_ride

		case .parkSharing:
			L10n.parking_filter_park_sharing

		case .theZone:
			L10n.parking_filter_zone
		}
	}
}
