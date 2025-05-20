import SwiftUICore

extension View {

	func scrollShadowMask(type: ScrollType) -> some View {
		modifier(ScrollShadowMask(type: type))
	}

}

public enum ScrollType {
	case vertical
	case horizontal
}

private struct ScrollShadowMask: ViewModifier {

	let type: ScrollType

	func body(content: Self.Content) -> some View {
		content
			.mask {
				switch type {
				case .vertical:
					VStack(spacing: 0) {
						contentView(startPoint: .top, endPoint: .bottom, height: 16)
					}

				case .horizontal:
					HStack(spacing: 0) {
						contentView(startPoint: .leading, endPoint: .trailing, width: 16)
					}
				}
			}
	}

	@ViewBuilder
	private func contentView(
		startPoint: UnitPoint,
		endPoint: UnitPoint,
		width: CGFloat? = nil,
		height: CGFloat? = nil,
	) -> some View {
			LinearGradient(
				colors: [.clear, .black],
				startPoint: startPoint,
				endPoint: endPoint,
			)
			.frame(width: width, height: height)

			Rectangle()
				.foregroundColor(.black)

			LinearGradient(
				colors: [.black, .clear],
				startPoint: startPoint,
				endPoint: endPoint,
			)
			.frame(width: width, height: height)
	}
}
