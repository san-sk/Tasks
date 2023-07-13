plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
    id("kotlinx-serialization")
    kotlin("kapt")
}

android {
    namespace = "com.san.core"
    compileSdk = 33

    defaultConfig {
        minSdk = 21
        targetSdk = 33

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {

    api("androidx.appcompat:appcompat:1.6.1")
    api("com.google.android.material:material:1.9.0")
    testApi("junit:junit:4.13.2")
    androidTestApi("androidx.test.ext:junit:1.1.5")
    androidTestApi("androidx.test.espresso:espresso-core:3.5.1")


    //main
    api("androidx.core:core-ktx:1.10.1")
    api("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    api("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")
    api("androidx.lifecycle:lifecycle-livedata-ktx:2.6.1")

    //coroutines
    api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    api("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")

    //hilt
    api("com.google.dagger:hilt-android:2.44.2")
    kapt("com.google.dagger:hilt-compiler:2.44.2")
    api("androidx.hilt:hilt-navigation-compose:1.0.0")

    //Network
    api("com.squareup.retrofit2:retrofit:2.9.0")
    api("com.squareup.okhttp3:okhttp:4.10.0")
    api("com.squareup.okhttp3:logging-interceptor:4.10.0")
    api("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1")
    api("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:0.8.0")

    //Room
    implementation("androidx.room:room-runtime:2.5.2")
    kapt("androidx.room:room-compiler:2.5.2")
    implementation("androidx.room:room-ktx:2.5.2")
    implementation("androidx.room:room-testing:2.5.2")

    // Timber
    api("com.jakewharton.timber:timber:5.0.1")

    //coil
    api("io.coil-kt:coil-compose:2.2.2")


    //ktor
    val ktor_version = "2.2.3"
    implementation("io.ktor:ktor-client-core:$ktor_version")
    implementation("io.ktor:ktor-client-android:$ktor_version")
    implementation("io.ktor:ktor-client-serialization:$ktor_version")
    implementation("io.ktor:ktor-client-logging:$ktor_version")
    implementation("io.ktor:ktor-client-content-negotiation:$ktor_version")

}