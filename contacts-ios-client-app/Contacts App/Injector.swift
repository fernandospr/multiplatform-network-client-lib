//
//  Injector.swift
//  iOS App
//
//  Created by Fernando Luis Sproviero on 11/01/2024.
//

import Foundation
import ContactsClient

class Injector {
    
    static let shared = Injector()

    private let repository = ContactsClient(baseUrl: "http://127.0.0.1:8080")

    @MainActor
    func makeContentViewViewModel() -> ContentView.ViewModel {
        return ContentView.ViewModel(contactsClient: repository)
    }

    @MainActor
    func makeContactDetailViewViewModel() -> ContactDetailView.ViewModel {
        return ContactDetailView.ViewModel(contactsClient: repository)
    }
    
    @MainActor
    func makeContactEditViewViewModel() -> ContactEditView.ViewModel {
        return ContactEditView.ViewModel(contactsClient: repository)
    }
}
