object AppBuildConfig {

    private const val versionMajor = 1
    private const val versionMinor = 0
    private const val versionPatch = 0


    const val applicationId = "com.xxx.xxx"
    const val compileSdk = 32
    const val minSdk = 21
    const val targetSdk = 32
    const val versionCode = versionMajor * 100 + versionMinor * 10 + versionPatch
    const val versionName = "${versionMajor}.${versionMinor}"
    const val androidJunit = "androidx.test.runner.AndroidJUnitRunner"
}