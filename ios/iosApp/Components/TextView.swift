import SwiftUI

struct TextView: View {

	private let text: String
	private let font: Font
	private let weight: Font.Weight

	init(text: String, font: Font = .caption, weight: Font.Weight = .regular) {
		self.text = text
		self.font = font
		self.weight = weight
	}

	var body: some View {
		Text(text)
			.font(font)
			.fontWeight(weight)
	}
}

#Preview {
	TextView(text: "Some text")
}
