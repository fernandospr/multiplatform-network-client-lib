## contacts-backend-app
Contains a backend app that exposes a controller to get, add and delete `Contact`s.

The `Contact`s are stored in memory.

### How to compile & run?
1. You'll need to compile `contacts-common-lib` first, as explained [here](../contacts-common-lib/README.md).
2. Open the project in your favorite IDE and run the main function from `Application.kt`.

You can now perform GET, POST and DELETE requests to `/api/contacts`. E.g:

Request | cURL | Screenshot
:---: | :--- | :---:
Get | `curl localhost:8080/api/contacts/1` | <img width="624" alt="Contacts - Server - Get" src="https://github.com/fernandospr/multiplatform-network-client-lib/assets/4404680/f41ed075-cf4a-4d04-bf03-d838e66329ce">
Get all | `curl localhost:8080/api/contacts` | <img width="828" alt="Contacts - Server - Get all" src="https://github.com/fernandospr/multiplatform-network-client-lib/assets/4404680/f3883587-61dc-4d76-b749-23ac745f9145">
Post | `curl -X POST -H "Content-Type: application/json" -d '{"id": "3", "name": "Mario", "number":"1234-5678"}' localhost:8080/api/contacts` | <img width="750" alt="Contacts - Server - Post" src="https://github.com/fernandospr/multiplatform-network-client-lib/assets/4404680/090a4325-8a1e-4062-babe-55a86d8423b0">
Delete | `curl -X DELETE localhost:8080/api/contacts/3 -v` | <img width="721" alt="Contacts - Server - Delete" src="https://github.com/fernandospr/multiplatform-network-client-lib/assets/4404680/cea976bc-5732-421c-871f-07f7176187f2">

> Note: You must start this application so that the Android, iOS and backend clients using `contacts-client-lib` will be able to make HTTP requests.