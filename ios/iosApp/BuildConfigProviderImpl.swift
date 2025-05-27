import Shared

public final class BuildConfigProviderImpl: BuildConfigProvider {
#if DEBUG
	public var isDebug: Bool = true
#else
	public var isDebug: Bool = false
#endif

	public var isDevBuild: Bool = Bundle.main.bundleIdentifier?.contains(".dev") == true
}
