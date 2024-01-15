## backend-client-app
Contains a Backend App that lets you get, add and delete `Contact`s using the `multiplatform-rest-lib`.

### How to compile & run?
1. To build this app you'll need to compile `contacts-client-lib` first, as explained above.
2. `contacts-client-lib` requires you to set the base url of the `contacts-backend-app`. You can configure this in `application.properties`.
3. Open the project in your favorite IDE and run the main function from `Application.kt`.

You can now perform GET, POST and DELETE requests to `/api/contact-descriptions`. E.g:

Request | cURL | Screenshot
:---: | :--- | :---:
Get | `curl localhost:8181/api/contact-descriptions/1` | <img width="772" alt="Contacts - Backend Client - Get" src="https://github.com/fernandospr/multiplatform-network-client-lib/assets/4404680/f10fec55-202f-495f-9947-6e77fed746cc">
Get all | `curl localhost:8181/api/contact-descriptions` | <img width="939" alt="Contacts - Backend Client - Get all" src="https://github.com/fernandospr/multiplatform-network-client-lib/assets/4404680/3b6e1c01-254e-42e0-9e3b-10579265f4f6">
Post | `curl -X POST -H "Content-Type: application/json" -d '{"description": "Id[5] Name[Luigi] Number[8976-3245]"}' localhost:8181/api/contact-descriptions` | <img width="801" alt="Contacts - Backend Client - Post" src="https://github.com/fernandospr/multiplatform-network-client-lib/assets/4404680/f2a94729-dc0f-453f-8065-bc75530f0513">
Delete | `curl -X DELETE localhost:8181/api/contact-descriptions/5 -v` | <img width="878" alt="Contacts - Backend Client - Delete" src="https://github.com/fernandospr/multiplatform-network-client-lib/assets/4404680/fb345b34-9230-4106-a9ee-0f63f34e9ac7">