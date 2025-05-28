import Shared
import UIKit

public final class MultiplatformApplicationService: ApplicationServiceType {

	public func application(
		_ application: UIApplication,
		didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?
	) {
		KoinKt.doInitKoin(
			platformModules: [
				ApplicationModule().createKoinModule(buildConfigProvider: { BuildConfigProviderImpl() }),
			],
			appDeclaration: { _ in }
		)
	}

	public init() {}
}
