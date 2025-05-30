import Shared
import SwiftUI

struct ErrorMessage: View {

	let error: ParkingLotsState.Error

	var body: some View {
		Text(error.localizedMessage())
	}
}
