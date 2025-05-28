import Shared

final class UniversalStateCollector: Kotlinx_coroutines_coreFlowCollector {
	typealias StateType = State

	private let setViewState: (ViewStateType) -> Void

	init(setViewState: @escaping (ViewStateType) -> Void) {
		self.setViewState = setViewState
	}

	@MainActor
	func emit(value: Any?) async throws {
		guard let state = value as? StateType else { return }
		setViewState(Self.map(state: state))
	}

	private static func map(state: StateType) -> ViewStateType {
		switch state {
		case is State.Loading:
			return .loading

		case let data as State.Data:
			return .data(model: data.parkingLots)

		case let err as State.Error:
			return .error(err)

		default:
			return .error(.init(type: .unknown))
		}
	}
}
