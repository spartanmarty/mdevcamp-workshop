import Shared

extension ParkingPolicyFilter {

	func getTranslate() -> LocalizedStringResource {
		switch self {
		case .noFilter:
			"parking_filter_no_filter"
		case .commercial:
			"parking_filter_commercial"
		case .customerOnly:
			"parking_filter_customer_only"
		case .parkAndRide:
			"parking_filter_park_and_ride"
		case .kissAndRide:
			"parking_filter_kiss_and_ride"
		case .parkSharing:
			"parking_filter_park_sharing"
		default:
			"parking_filter_zone"
		}
	}

}
