import org.gradle.kotlin.dsl.`kotlin-dsl`

plugins {
    `kotlin-dsl`
    id("com.github.ben-manes.versions") version "0.42.0"
}

repositories {
    google()
    mavenCentral()
    maven("https://jitpack.io")
}
