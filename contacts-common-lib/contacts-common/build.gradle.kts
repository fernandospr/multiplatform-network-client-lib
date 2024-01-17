import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    id("maven-publish")
    kotlin("plugin.serialization") version "1.9.21"
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

    val name = "ContactsCommon"
    val xcf = XCFramework(name)
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = name
            xcf.add(this)
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.5")
        }
        jvmMain.dependencies {}
        androidMain.dependencies {}
        iosMain.dependencies {}
        commonTest.dependencies {
            implementation(kotlin("test"))
        }
    }
}

android {
    namespace = "com.github.fernandospr"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
}