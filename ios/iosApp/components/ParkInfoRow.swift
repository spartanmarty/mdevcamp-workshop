import SwiftUI

struct ParkInfoRow: View {

	let title: String
	let value: String

	var body: some View {
		HStack {
			Text(LocalizedStringKey(title))
				.font(.caption)
				.fontWeight(.regular)

			Text(LocalizedStringKey(value))
				.font(.caption)
				.fontWeight(.bold)
		}
	}
}

