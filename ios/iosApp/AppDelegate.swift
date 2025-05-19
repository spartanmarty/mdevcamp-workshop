import UIKit

final class AppDelegate: NSObject, UIApplicationDelegate {
	private var applicationServices: [ApplicationServiceType]  = [
		MultiplatformApplicationService(),
	]

	func application(
		_ application: UIApplication,
		didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]? = nil
	) -> Bool {
		applicationServices.forEach {
			$0.application(application, didFinishLaunchingWithOptions: launchOptions)
		}
		return true
	}
}
