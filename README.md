Demonstrates a network client library for Android, iOS and JVM projects using Kotlin Multiplatform.

This repository contains the following folders:
* contacts-common-lib
* contacts-client-lib
* contacts-backend-app
* contacts-android-client-app
* contacts-ios-client-app
* contacts-backend-client-app

## contacts-common-lib
This multiplatform library contains a `Contact` class. `contacts-client-lib` and `contacts-backend-app` use this common library.

### How to compile the library?
Execute `./gradlew publishToMavenLocal`. This task will publish in your `~/.m2/repository` folder.

## contacts-client-lib
This multiplatform library exposes a `ContactsClient` class with functions to get, add and delete `Contact`s.

Internally, it performs these operations by connecting to `contacts-backend-app` using HTTP requests.

### How to compile the library for Android/JVM apps?
1. To build this lib you'll need to compile `contacts-common-lib` first, as explained above.
2. Execute `./gradlew publishToMavenLocal`. This task will publish in your `~/.m2/repository` folder.
  
### How to compile the library for iOS apps?
1. Execute `./gradlew assembleContactsClientReleaseXCFramework`. This task will generate the framework in `build/XCFrameworks/release` of the library project.
2. Copy the framework to the iOS client project.

## contacts-backend-app
Contains a backend app that exposes a controller to get, add and delete `Contact`s.

The `Contact`s are stored in memory.

### How to compile & run?
1. You'll need to compile `contacts-common-lib` first, as explained above.
2. Open the project in your favorite IDE and run the main function from `Application.kt`.

> Note: You must start this application so that the Android, iOS and backend clients using `contacts-client-lib` will be able to make HTTP requests.

## contacts-android-client-app
Contains an Android App that lets you get, add and delete `Contact`s using the `contacts-client-lib`.

### How to compile & run?
1. To build this app you'll need to compile `contacts-client-lib` first, as explained above.
2. `contacts-client-lib` requires you to set the base url of the `contacts-backend-app`. You can configure this in `MyApplication.kt`.
3. Use Android Studio to launch the app.

## ios-client-app
Contains an iOS App that lets you get, add and delete `Contact`s using the `multiplatform-rest-lib`.

### How to compile & run?
1. To build this app you'll need to compile `contacts-client-lib` first and copy it to the iOS project folder, as explained above.
2. `contacts-client-lib` requires you to set the base url of the `contacts-backend-app`. You can configure this in `Injector.swift`.
3. Use Xcode to launch the app.

## backend-client-app
Contains a Backend App that lets you get, add and delete `Contact`s using the `multiplatform-rest-lib`.

### How to compile & run?
1. To build this app you'll need to compile `contacts-client-lib` first, as explained above.
2. `contacts-client-lib` requires you to set the base url of the `contacts-backend-app`. You can configure this in `application.properties`.
3. Open the project in your favorite IDE and run the main function from `Application.kt`.