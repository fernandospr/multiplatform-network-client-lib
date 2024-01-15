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

You can now perform GET, POST and DELETE requests to `/api/contacts`. E.g:

Request | cURL | Screenshot
:-: | :-: | :-:
Get | `curl localhost:8080/api/contacts/1` | <img width="624" alt="Contacts - Server - Get" src="https://github.com/fernandospr/multiplatform-network-client-lib/assets/4404680/f41ed075-cf4a-4d04-bf03-d838e66329ce">
Get all | `curl localhost:8080/api/contacts` | <img width="828" alt="Contacts - Server - Get all" src="https://github.com/fernandospr/multiplatform-network-client-lib/assets/4404680/f3883587-61dc-4d76-b749-23ac745f9145">
Post | `curl -X POST -H "Content-Type: application/json" -d '{"id": "3", "name": "Mario", "number":"1234-5678"}' localhost:8080/api/contacts` | <img width="750" alt="Contacts - Server - Post" src="https://github.com/fernandospr/multiplatform-network-client-lib/assets/4404680/090a4325-8a1e-4062-babe-55a86d8423b0">
Delete | `curl -X DELETE localhost:8080/api/contacts/3 -v` | <img width="721" alt="Contacts - Server - Delete" src="https://github.com/fernandospr/multiplatform-network-client-lib/assets/4404680/cea976bc-5732-421c-871f-07f7176187f2">




> Note: You must start this application so that the Android, iOS and backend clients using `contacts-client-lib` will be able to make HTTP requests.

## contacts-android-client-app
Contains an Android App that lets you get, add and delete `Contact`s using the `contacts-client-lib`.

### How to compile & run?
1. To build this app you'll need to compile `contacts-client-lib` first, as explained above.
2. `contacts-client-lib` requires you to set the base url of the `contacts-backend-app`. You can configure this in `MyApplication.kt`.
3. Use Android Studio to launch the app.

List | Add | Delete
:-: | :-: | :-:
<img width="200" alt="Contacts - Android - List" src="https://github.com/fernandospr/multiplatform-network-client-lib/assets/4404680/e6fca80b-0e3e-433a-94d1-13a4cdac4d04"> | <img width="200" alt="Contacts - Android - Add" src="https://github.com/fernandospr/multiplatform-network-client-lib/assets/4404680/8be4d59d-23bd-4c6f-b587-28b2adf731d0"> | <img width="200" alt="Contacts - Android - Delete" src="https://github.com/fernandospr/multiplatform-network-client-lib/assets/4404680/f47f044e-80d4-4cbf-8a81-9f0cd97af84c">

## ios-client-app
Contains an iOS App that lets you get, add and delete `Contact`s using the `multiplatform-rest-lib`.

### How to compile & run?
1. To build this app you'll need to compile `contacts-client-lib` first and copy it to the iOS project folder, as explained above.
2. `contacts-client-lib` requires you to set the base url of the `contacts-backend-app`. You can configure this in `Injector.swift`.
3. Use Xcode to launch the app.

List | Add | Delete
:-: | :-: | :-:
<img width="200" alt="Contacts - iOS - List" src="https://github.com/fernandospr/multiplatform-network-client-lib/assets/4404680/f98ffd70-89a8-4507-82a3-8e45e8ae5969"> | <img width="200" alt="Contacts - iOS - Add" src="https://github.com/fernandospr/multiplatform-network-client-lib/assets/4404680/ca2e186d-8889-435a-b7d2-311f55653a19"> | <img width="200" alt="Contacts - iOS - Delete" src="https://github.com/fernandospr/multiplatform-network-client-lib/assets/4404680/2bb8df1a-3b7e-4c14-891a-cd674ea78740">


## backend-client-app
Contains a Backend App that lets you get, add and delete `Contact`s using the `multiplatform-rest-lib`.

### How to compile & run?
1. To build this app you'll need to compile `contacts-client-lib` first, as explained above.
2. `contacts-client-lib` requires you to set the base url of the `contacts-backend-app`. You can configure this in `application.properties`.
3. Open the project in your favorite IDE and run the main function from `Application.kt`.

You can now perform GET, POST and DELETE requests to `/api/contact-descriptions`. E.g:

Request | cURL | Screenshot
:-: | :-: | :-:
Get | `curl localhost:8181/api/contact-descriptions/1` | <img width="772" alt="Contacts - Backend Client - Get" src="https://github.com/fernandospr/multiplatform-network-client-lib/assets/4404680/f10fec55-202f-495f-9947-6e77fed746cc">
Get all | `curl localhost:8181/api/contact-descriptions` | <img width="939" alt="Contacts - Backend Client - Get all" src="https://github.com/fernandospr/multiplatform-network-client-lib/assets/4404680/3b6e1c01-254e-42e0-9e3b-10579265f4f6">
Post | `curl -X POST -H "Content-Type: application/json" -d '{"description": "Id[5] Name[Luigi] Number[8976-3245]"}' localhost:8181/api/contact-descriptions` | <img width="801" alt="Contacts - Backend Client - Post" src="https://github.com/fernandospr/multiplatform-network-client-lib/assets/4404680/f2a94729-dc0f-453f-8065-bc75530f0513">
Delete | `curl -X DELETE localhost:8181/api/contact-descriptions/5 -v` | <img width="878" alt="Contacts - Backend Client - Delete" src="https://github.com/fernandospr/multiplatform-network-client-lib/assets/4404680/fb345b34-9230-4106-a9ee-0f63f34e9ac7">
