import SwiftUI
import Shared

@main
struct iOSApp: App {

	init() {
		KoinKt.doInitKoin(
			platformModules: [
				ApplicationModule.shared.createKoinModule(buildConfigProvider: { DefaultBuildConfigProvider() }),
			],
			appDeclaration: { _ in }
		)
	}

    var body: some Scene {
        WindowGroup {
			ContentView()
        }
    }
}
