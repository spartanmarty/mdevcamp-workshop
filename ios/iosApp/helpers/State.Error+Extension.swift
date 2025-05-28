import Shared

extension State.Error {
	func getTranslate() -> String {
		switch self.type {
		case .noDataFound:
			"No data found."

		case .network:
			"Problem with network connection."

		case .unknown:
			"¯\\_(ツ)_/¯"

		default:
			""
		}
	}
}
