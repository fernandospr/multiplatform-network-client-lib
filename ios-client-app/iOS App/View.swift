//
//  ContentView.swift
//  iOS App
//
//  Created by Fernando Luis Sproviero on 08/01/2024.
//

import SwiftUI
import shared

struct ContentView: View {
    @StateObject var viewModel = Injector.shared.makeContentViewViewModel()
    
    var body: some View {
        NavigationView {
            switch viewModel.state {
            case .idle:
                ProgressView().onAppear { viewModel.getAllContacts() }
            case .success(let contacts):
                ContactListSuccessView(contacts: contacts, contactDeleted: { viewModel.getAllContacts() })
                    .refreshable { await viewModel.refresh() }
                    .toolbar {
                        ToolbarItem(placement: .navigationBarTrailing) {
                            NavigationLink {
                                ContactEditView(contactAdded: { viewModel.getAllContacts() })
                            } label: {
                                Image(systemName: "plus")
                            }
                        }
                    }
            case .error:
                Color.red
            }
        }
    }
}

struct ContactListSuccessView: View {
    let contacts: [Contact]
    let contactDeleted: () -> Void

    var body: some View {
        List(contacts, id: \.self) { contact in
            NavigationLink {
                ContactDetailView(contactId: contact.id, contactDeleted: contactDeleted)
            } label: {
                ContactView(item: contact)
            }
        }
    }
}

struct ContactListView_Previews: PreviewProvider {
    static var previews: some View {
        ContactListSuccessView(contacts: [
            Contact(id: "1", name: "Name1", number: "1111-1111"),
            Contact(id: "2", name: "Name2", number: "2222-2222"),
            Contact(id: "3", name: "Name3", number: "3333-3333")], contactDeleted: {}
        )
    }
}

struct ContactView : View {
    let item: Contact
    
    var body: some View {
        VStack(alignment: .leading) {
            Text("Id: " + item.id)
            Text("Name: " + item.name)
            Text("Number: " + item.number)
        }
    }
}

struct ContactView_Previews: PreviewProvider {
    static var previews: some View {
        ContactView(item: Contact(id: "1", name: "Name1", number: "1111-1111"))
    }
}

struct ContactDetailView: View {
    let contactId: String
    let contactDeleted: () -> Void

    @Environment(\.dismiss) private var dismiss
    
    @StateObject var viewModel = Injector.shared.makeContactDetailViewViewModel()
    
    var body: some View {
        switch viewModel.contactState {
        case .idle:
            Color.clear.onAppear { viewModel.getContact(id: contactId) }
        case .loading:
            ProgressView()
        case .success(let contact):
            ContactDetailSuccessView(
                contact: contact, deleteAction: { viewModel.deleteContact(id: contact.id) }
            ).onChange(of: viewModel.deleteState) { state in
                if (state == .deleted) {
                    dismiss()
                    contactDeleted()
                }
            }
        case .error:
            Color.red
        }
    }
}

struct ContactDetailSuccessView: View {
    let contact: Contact
    let deleteAction: () -> Void
    
    var body: some View {
        VStack(alignment: .center) {
            Text(contact.id).font(.system(size: 30))
            Text(contact.name).font(.system(size: 30))
            Text(contact.number).font(.system(size: 30))
            Button(action: deleteAction) {
                Text("Delete")
                    .frame(maxWidth: .infinity)
                    .font(.headline)
                    .foregroundColor(.white)
                    .padding()
                    .background(Color.red)
                    .cornerRadius(10)
            }.padding(EdgeInsets(top: 32, leading: 16, bottom: 0, trailing: 16))
        }
    }
}

struct ContactDetailSuccessView_Previews: PreviewProvider {
    static var previews: some View {
        ContactDetailSuccessView(
            contact: Contact(id: "1", name: "Name1", number: "1111"),
            deleteAction: {}
        )
    }
}

struct ContactEditView: View {
    @State private var id = ""
    @State private var name = ""
    @State private var number = ""
    let contactAdded: () -> Void
    
    @StateObject var viewModel = Injector.shared.makeContactEditViewViewModel()
    
    @Environment(\.dismiss) private var dismiss
    
    var body: some View {
        VStack {
            TextField("Id", text: $id)
                .multilineTextAlignment(.center)
                .font(.system(size: 30))
                .padding(EdgeInsets(top: 0, leading: 16, bottom: 0, trailing: 16))
            TextField("Name", text: $name)
                .multilineTextAlignment(.center)
                .font(.system(size: 30))
                .padding(EdgeInsets(top: 0, leading: 16, bottom: 0, trailing: 16))
            TextField("Number", text: $number)
                .multilineTextAlignment(.center)
                .font(.system(size: 30))
                .padding(EdgeInsets(top: 0, leading: 16, bottom: 0, trailing: 16))
            
            Button(action: {
                viewModel.addContact(id: id, name: name, number: number)
            }) {
                Text("Add")
                    .frame(maxWidth: .infinity)
                    .font(.headline)
                    .foregroundColor(.white)
                    .padding()
                    .background(Color.blue)
                    .cornerRadius(10)
            }.padding(EdgeInsets(top: 32, leading: 16, bottom: 0, trailing: 16))
        }.onChange(of: viewModel.addState) { state in
            if (state == .added) {
                dismiss()
                contactAdded()
            }
        }
    }
}

struct ContactEditView_Previews: PreviewProvider {
    static var previews: some View {
        ContactEditView(contactAdded: {})
    }
}
