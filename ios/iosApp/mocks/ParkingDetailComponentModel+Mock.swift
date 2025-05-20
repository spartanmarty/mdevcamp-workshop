import Shared

extension ParkingDetailComponentModel {

	static var mock: ParkingDetailComponentModel {
		return .init(
			id: "Parkoviště - Areál Gayerova kasárna",
			title: "Parkoviště - Areál Gayerova kasárna",
			rows: [
				.init(
					id: "Row-Capacity",
					title: "Capacity",
					value: "60",
				),
				.init(
					id: "Row-Address",
					title: "Address",
					value: "nám. Dr. E. Beneše, 46001 Liberec Staré Město, Česko",
				),
				.init(
					id: "Row-Covered",
					title: "Covered",
					value: "Yes",
				),
				.init(
					id: "Row-Prohibitions",
					title: "Prohibitions",
					value: "lpg, motorcycle",
				),
			],
			prohibitions: [],
		)
	}
}

