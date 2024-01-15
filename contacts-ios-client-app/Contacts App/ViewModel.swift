//
//  ViewModel.swift
//  iOS App
//
//  Created by Fernando Luis Sproviero on 10/01/2024.
//

import Foundation
import KMPNativeCoroutinesAsync
import KMPNativeCoroutinesCore
import ContactsClient

extension ContentView {
    @MainActor
    class ViewModel: ObservableObject {
        enum State {
            case idle; case success(contacts: [Contact]); case error
        }
        
        private let contactsClient: ContactsClient
        
        @Published private(set) var state = State.idle
        
        init(contactsClient: ContactsClient) {
            self.contactsClient = contactsClient
        }
        
        func refresh() async {
            return getAllContacts()
        }
        
        func getAllContacts() {
            Task {
                do {
                    let allContacts = try await asyncFunction(for: contactsClient.getAll())
                    state = State.success(contacts: allContacts)
                } catch {
                    print("Failed with error: \(error)")
                    state = State.error
                }
            }
        }
    }
}

extension ContactDetailView {
    @MainActor
    class ViewModel: ObservableObject {
        enum ContactState {
            case idle; case loading; case success(contact: Contact); case error
        }
        enum DeleteState {
            case idle; case deleting; case deleted; case error
        }
        
        private let contactsClient: ContactsClient
        
        @Published private(set) var contactState = ContactState.idle
        @Published private(set) var deleteState = DeleteState.idle
        
        init(contactsClient: ContactsClient) {
            self.contactsClient = contactsClient
        }
        
        func getContact(id: String) {
            Task {
                do {
                    contactState = ContactState.loading
                    let result = try await asyncFunction(for: contactsClient.get(contactId: id))
                    contactState = ContactState.success(contact: result)
                } catch {
                    print("Failed with error: \(error)")
                    contactState = ContactState.error
                }
            }
        }
        
        func deleteContact(id: String) {
            Task {
                do {
                    deleteState = DeleteState.deleting
                    _ = try await asyncFunction(for: contactsClient.delete(contactId: id))
                    deleteState = DeleteState.deleted
                } catch {
                    print("Failed with error: \(error)")
                    deleteState = DeleteState.error
                }
            }
        }
    }
}

extension ContactEditView {
    @MainActor
    class ViewModel: ObservableObject {
        enum AddState {
            case idle; case adding; case added; case error
        }
        
        private let contactsClient: ContactsClient
        
        @Published private(set) var addState = AddState.idle
        
        init(contactsClient: ContactsClient) {
            self.contactsClient = contactsClient
        }
        
        func addContact(id: String, name: String, number: String) {
            Task {
                do {
                    addState = AddState.adding
                    let contact = Contact(id: id, name: name, number: number)
                    _ = try await asyncFunction(for: contactsClient.add(contact: contact))
                    addState = AddState.added
                } catch {
                    print("Failed with error: \(error)")
                    addState = AddState.error
                }
            }
        }
    }
}
