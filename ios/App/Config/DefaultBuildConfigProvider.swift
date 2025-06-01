import Foundation
import Shared

public final class DefaultBuildConfigProvider: BuildConfigProvider {

	public var isDebug: Bool = true // set to false to use real networking
	public var isDevBuild: Bool { isDebug }

	/* example how it can be in real application

	 public var isDebug: Bool {
	 	#if DEBUG
	 	true
	 	#else
	 	false
	 	#endif
	 }

	 public var isDevBuild: Bool = Bundle.main.bundleIdentifier?.contains(".dev") == true

	 */
}
