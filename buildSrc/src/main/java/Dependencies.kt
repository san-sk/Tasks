object Dependencies {

    //Base
    private const val androidXCore = "androidx.core:core-ktx:${Versions.androidXCore}"
    private const val appCompact = "androidx.appcompat:appcompat:${Versions.appCompact}"
    private const val material = "com.google.android.material:material:${Versions.material}"

    //Lifecycle
    private const val lifeCycleRuntimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycleKtx}"
    private const val lifeCycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycleKtx}"

    // Coroutines
    private const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    private const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"

    //Work manager
    private const val workRuntime = "androidx.work:work-runtime-ktx:${Versions.work}"


    //Compose
    private const val composeUi = "androidx.compose.ui:ui:${Versions.composeUi}"
    private const val composeMaterial = "androidx.compose.material:material:${Versions.composeMaterial}"
    private const val composeUiTool = "androidx.compose.ui:ui-tooling:${Versions.composeUiTool}"
    private const val composeActivity = "androidx.activity:activity-compose:${Versions.composeActivity}"

    //Retrofit
    private const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    private const val retrofitGsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    private const val retrofitMoshiConverter = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
    private const val okHttp3 = "com.squareup.okhttp3:okhttp:${Versions.okHttp3}"
    private const val okHttp3Interceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttp3}"

    //moshi
    private const val moshi = "com.squareup.moshi:moshi-kotlin:${Versions.moshi}"

    // Coil
    private const val coil = "io.coil-kt:coil:${Versions.coil}"
    private const val coilAccompanist = "com.google.accompanist:accompanist-coil:${Versions.coilAccompanist}"

    //Dagger - Hilt
    private const val hiltCore = "com.google.dagger:hilt-android:${Versions.hiltCore}"
    private const val hiltViewModel = "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.hiltViewModel}"
    private const val hiltWorker = "androidx.hilt:hilt-work:${Versions.hiltWorker}"
    private const val hiltComposeNavigation = "androidx.hilt:hilt-navigation-compose:${Versions.hiltComposeNavigation}"

    //kapt
    private const val hiltAndroidCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hiltAndroidCompiler}"
    private const val hiltCompiler = "androidx.hilt:hilt-compiler:${Versions.hiltCompiler}"


    //Room
    private const val roomRuntime = "androidx.room:room-runtime:${Versions.roomVersion}"
    private const val roomAnnotation = "androidx.room:room-compiler:${Versions.roomVersion}"

    // To use Kotlin annotation processing tool (kapt)
    private const val roomCompiler = "androidx.room:room-compiler:${Versions.roomVersion}"

    // optional - Kotlin Extensions and Coroutines support for Room
    private const val roomKtx = "androidx.room:room-ktx:${Versions.roomVersion}"

    // optional - Test helpers
    private const val roomTest = "androidx.room:room-testing:${Versions.roomVersion}"

    // Timber
    private const val timber = "com.jakewharton.timber:timber:${Versions.timber}"



    //Test
    private const val jUnit = "junit:junit:${Versions.junit}"
    private const val junitExt = "androidx.test.ext:junit:${Versions.junitExt}"
    private const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    private const val composeUiTest = "androidx.compose.ui:ui-test-junit4:${Versions.compose}"


    //Dependency List
    val baseImplementation = listOf(
        androidXCore, appCompact, material, lifeCycleRuntimeKtx, lifeCycleViewModel,
        coroutinesAndroid, coroutinesCore, workRuntime
    )

    val networkImplementation = listOf(
        retrofit,
        retrofitGsonConverter,
        retrofitMoshiConverter,
        okHttp3,
        okHttp3Interceptor,
        moshi,
        coil,
        coilAccompanist
    )

    val localStorageImplementation = arrayListOf<String>().apply {
        add(roomRuntime)
        add(roomKtx)
    }

    val localStorageKapt = arrayListOf<String>().apply {
        add(roomCompiler)
    }

    val localStorageTestImplementation = arrayListOf<String>().apply {
        add(roomTest)
    }

    val localStorageAnnotation = arrayListOf<String>().apply {
        add(roomAnnotation)
    }

    val diImplementation = arrayListOf<String>().apply {
        add(hiltCore)
        add(hiltViewModel)
        add(hiltWorker)
        add(hiltComposeNavigation)
    }

    val diKapt = arrayListOf<String>().apply {
        add(hiltCompiler)
        add(hiltAndroidCompiler)
    }

    val composeImplementation = arrayListOf<String>().apply {
        add(composeUi)
        add(composeUiTool)
        add(composeMaterial)
        add(composeActivity)
    }

    val testImplementation = arrayListOf<String>().apply {
        add(jUnit)
    }

    val androidTestImplementation = arrayListOf<String>().apply {
        add(junitExt)
        add(espresso)
        add(composeUiTest)
    }

    val otherImplementations = arrayListOf<String>().apply {
        add(coil)
        add(coilAccompanist)
        add(timber)
    }

}