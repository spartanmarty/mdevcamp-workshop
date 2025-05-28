import Shared

extension State.Error {
	func getTranslate() -> LocalizedStringResource {
		switch self.type {
		case .noDataFound:
			"error_message_no_data"

		case .network:
			"error_message_network"

		case .unknown:
			"value_unknown"

		default:
			""
		}
	}
}
