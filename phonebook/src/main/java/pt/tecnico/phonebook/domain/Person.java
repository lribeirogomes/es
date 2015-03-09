package pt.tecnico.phonebook.domain;

import java.util.ArrayList;
import java.util.List;

import pt.tecnico.phonebook.exception.ContactDoesNotExistException;
import pt.tecnico.phonebook.exception.NameAlreadyExistsException;

public class Person extends Person_Base {
    
    public Person(String name) {
        super();
	setName(name);
    }    	      
    
    private Contact getContactByName(String name) {
	for(Contact contact : getContactSet()) {
	    if(contact.getName().equals(name)) {
		return contact;
	    }
	}
	return null;
    }
    
    public boolean hasContact(String contactName) {
	return getContactByName(contactName) != null;
    }
    
    @Override
    public void addContact(Contact contactToBeAdded) throws NameAlreadyExistsException {
    	if (hasContact(contactToBeAdded.getName()))
    		throw new NameAlreadyExistsException(contactToBeAdded.getName());
	
    	super.addContact(contactToBeAdded);
    }
    
    public void removeContact(String contactName) throws ContactDoesNotExistException {
    	Contact toRemove = getContactByName(contactName);
    	if (toRemove == null)
	    throw new ContactDoesNotExistException(contactName);
	
    	removeContact(toRemove);
    }

    // returns a list with all contacts whose name contains the string given as parameter.
    public List<Contact> searchContact(String token) {
    	List<Contact> matchingContacts = new ArrayList<Contact>();

    	// add code here

    	return matchingContacts;
    }
}
