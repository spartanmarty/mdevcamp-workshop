import Shared
import UIKit

public final class MultiplatformApplicationService: ApplicationServiceType {

	public func application(
		_ application: UIApplication,
		didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?
	) {
		KoinKt.doInitKoin()
	}

	public init() {}
}
