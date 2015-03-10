package pt.tecnico.phonebook.domain;

import pt.ist.fenixframework.FenixFramework;

import java.util.ArrayList;
import java.util.List;

import pt.tecnico.phonebook.exception.PersonDoesNotExistException;
import pt.tecnico.phonebook.exception.NameAlreadyExistsException;

/**
 * This class implements the Singleton design pattern.
 **/

public class PhoneBook extends PhoneBook_Base {
    
    public static PhoneBook getInstance() {
	PhoneBook pb = FenixFramework.getDomainRoot().getPhonebook();
	if (pb == null)
	    pb = new PhoneBook();

	return pb;
    }


    private PhoneBook() {
	FenixFramework.getDomainRoot().setPhonebook(this);
    }
    
    private Person getPersonByName(String name) {
	for(Person person : getPersonSet()) {
	    if(person.getName().equals(name)) {
		return person;
	    }
	}
	return null;
    }
    
    public boolean hasPerson(String personName) {
	return getPersonByName(personName) != null;
    }
    
    public void removePerson(String personName) throws PersonDoesNotExistException {
    	Person toRemove = getPersonByName(personName);
    	if (toRemove == null)
	    throw new PersonDoesNotExistException(personName);
	
    	removePerson(toRemove);
    }

    public List<Person> searchPerson(String token) {
	List<Person> matchingPersons = new ArrayList<Person>();

	for(Person existingPerson : this.getPersonSet()) {
	    if(existingPerson.getName().contains(token)) {
		matchingPersons.add(existingPerson);
	    }
	}

	return matchingPersons;
    }
}
