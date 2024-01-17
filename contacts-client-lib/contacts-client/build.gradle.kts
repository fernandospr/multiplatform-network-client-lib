import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    id("maven-publish")
    kotlin("plugin.serialization") version "1.9.21"

    id("com.google.devtools.ksp") version "1.9.21-1.0.16"
    id("com.rickclephas.kmp.nativecoroutines") version "1.0.0-ALPHA-12"
}

group = "com.github.fernandospr"
version = "1.0.0"

kotlin {
    androidTarget {
        publishLibraryVariants("release", "debug")
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }

    jvm()

    val name = "ContactsClient"
    val xcf = XCFramework(name)
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            // Expose code from contacts-common external library
            export("com.github.fernandospr:contacts-common:1.0.0")

            baseName = name
            xcf.add(this)
            isStatic = true
        }
    }

    val ktorVersion = "2.3.5"
    sourceSets {
        commonMain.dependencies {
            // Using "api" to expose code from contacts-common external library
            api("com.github.fernandospr:contacts-common:1.0.0")

            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
            implementation("io.ktor:ktor-client-core:$ktorVersion")
            implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
            implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
        }
        jvmMain.dependencies {
            implementation("io.ktor:ktor-client-apache5:$ktorVersion")
        }
        androidMain.dependencies {
            implementation("io.ktor:ktor-client-android:$ktorVersion")
        }
        iosMain.dependencies {
            implementation("io.ktor:ktor-client-darwin:$ktorVersion")
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
        all {
            languageSettings.optIn("kotlin.experimental.ExperimentalObjCName")
        }
    }

}

android {
    namespace = "com.github.fernandospr.contacts"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
}