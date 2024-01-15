## contacts-client-lib
This multiplatform library exposes a `ContactsClient` class with functions to get, add and delete `Contact`s.

Internally, it performs these operations by connecting to `contacts-backend-app` using HTTP requests.

### How to compile the library for Android/JVM apps?
1. To build this lib you'll need to compile `contacts-common-lib` first, as explained [here](../contacts-common-lib/README.md).
2. Execute `./gradlew publishToMavenLocal`. This task will publish in your `~/.m2/repository` folder.
  
### How to compile the library for iOS apps?
1. To build this lib you'll need to compile `contacts-common-lib` first, as explained [here](../contacts-common-lib/README.md).
2. Execute `./gradlew assembleContactsClientReleaseXCFramework`. This task will generate the framework in `build/XCFrameworks/release` of the library project.
3. Copy the framework to the iOS client project.