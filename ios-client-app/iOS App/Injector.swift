//
//  Injector.swift
//  iOS App
//
//  Created by Fernando Luis Sproviero on 11/01/2024.
//

import Foundation
import shared

class Injector {
    
    static let shared = Injector()

    private let repository = ContactsRepository(baseUrl: "http://127.0.0.1:8080")

    @MainActor
    func makeContentViewViewModel() -> ContentView.ViewModel {
        return ContentView.ViewModel(repository: repository)
    }

    @MainActor
    func makeContactDetailViewViewModel() -> ContactDetailView.ViewModel {
        return ContactDetailView.ViewModel(repository: repository)
    }
    
    @MainActor
    func makeContactEditViewViewModel() -> ContactEditView.ViewModel {
        return ContactEditView.ViewModel(repository: repository)
    }
}
