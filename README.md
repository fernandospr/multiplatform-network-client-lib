Demonstrates a network client library for Android, iOS and JVM projects using Kotlin Multiplatform.

This repository contains the following folders:
* multiplatform-rest-lib
* backend-rest-app
* android-client-app
* ios-client-app
* backend-client-app

## multiplatform-rest-lib
This multiplatform library exposes a `Repository` class with functions to get, add and delete `Contact`s.

Internally, it performs these operations by connecting to the `backend-rest-app` using HTTP requests.


### Compiling the library for Android/JVM apps
1. Execute the Gradle Task: `publishToMavenLocal` using `./gradlew publishToMavenLocal`.
This task will publish in your `~/.m2/repository` folder.

2. Open the Android/JVM client project and make sure you have `mavenLocal()` in your repositories configuration.
3. Add the dependency: `implementation("com.github.fernandospr:shared-android:1.0.0")` (for Android) or `implementation("com.github.fernandospr:shared-jvm:1.0.0")` (for JVM).
4. Now in your Android/JVM app code you can import the library classes.
  
### Compiling the library for iOS apps
1. Execute the Gradle Task: `assembleSharedReleaseXCFramework`. This task will generate the framework in `build/XCFrameworks/release` of the library project.
2. Copy the framework to your iOS client project.
3. Open your iOS client project using Xcode, go to the project properties, open `General` tab and add the framework in the `Frameworks, Libraries and Embedded Content` section.
4. Now in your iOS app code you can import the library classes.

## backend-rest-app
Contains a backend app that exposes a controller to get, add and delete `Contact`s.

The `Contact`s are stored in memory.

You must start this application so that `multiplatform-rest-lib` can make HTTP requests.


## android-client-app
Contains an Android App that let's you get, add and delete `Contact`s using the `multiplatform-rest-lib`.

To build this app you'll need to compile `multiplatform-rest-lib` locally first, as explained above.

`multiplatform-rest-lib` requires you to set the base url of the `backend-rest-app`. You can configure this in `MyApplication.kt`.


## ios-client-app
Contains an iOS App that visually enables to get, add and delete `Contact`s using the `multiplatform-rest-lib`.

To build this app you'll need to compile the library locally and copy it to the iOS project folder, as explained above.

`multiplatform-rest-lib` requires you to set the base url of the `backend-rest-app`. You can configure this in `Injector.swift`.


## backend-client-app
Contains a sample Kotlin Backend App that uses the `multiplatform-rest-lib`.

To build this app you'll need to compile the library locally first, as explained above.

`multiplatform-rest-lib` requires you to set the base url of the `backend-rest-app`. You can configure this in `application.properties`.