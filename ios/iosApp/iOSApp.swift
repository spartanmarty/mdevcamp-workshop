import SwiftUI
import Shared

@main
struct iOSApp: App {

	init() {
		KoinKt.doInitKoin(
			platformModules: [
				ApplicationModule().createKoinModule(buildConfigProvider: { BuildConfigProviderImpl() }),
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
