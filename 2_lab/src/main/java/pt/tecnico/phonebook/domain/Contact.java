package pt.tecnico.phonebook.domain;

import pt.tecnico.phonebook.exception.NameAlreadyExistsException;

public class Contact extends Contact_Base {
    
    public Contact(String name, Integer phoneNumber) {
        this.setName(name);
        this.setPhoneNumber(phoneNumber);
    }

    @Override
    public void setPerson(Person person) throws NameAlreadyExistsException {
	person.addContact(this);
    }

}
