import Shared

extension [TabItemComponentModel] {

	static var mock: [TabItemComponentModel] {
		return .init(
			[
				.init(id: "OnStreet", name: "OnStreet"),
				.init(id: "Underground", name: "Underground"),
				.init(id: "Multi Storey", name: "Multi Storey"),
			]
		)
	}
}
