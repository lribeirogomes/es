package pt.tecnico.phonebook;

import java.util.Set;

import pt.ist.fenixframework.FenixFramework;
import pt.ist.fenixframework.TransactionManager;

import pt.tecnico.phonebook.domain.PhoneBook;
import pt.tecnico.phonebook.domain.Contact;
import pt.tecnico.phonebook.domain.Person;

import javax.transaction.*;

public class PhoneBookApplication {

    public static void main(String[] args) {
   	System.out.println("Welcome to the PhoneBook application!");

	TransactionManager tm = FenixFramework.getTransactionManager();
    	boolean committed = false;

   	try {
	    tm.begin();

	    PhoneBook pb = PhoneBook.getInstance();
	    setupIfNeed(pb);

	    for (Person person : pb.getPersonSet()) {
		System.out.println("The Contact book of " + person.getName() + " :");
		for(Contact contact : person.getContactSet()) {
		    System.out.println("\t Name: " + contact.getName() + " phone: " + contact.getPhoneNumber());
		}
	    }
	    tm.commit();
	    committed = true;
	}catch (SystemException| NotSupportedException | RollbackException| HeuristicMixedException | HeuristicRollbackException ex) {
	    System.err.println("Error in execution of transaction: " + ex);
	} finally {
	    if (!committed) 
		try {
		    tm.rollback();
		} catch (SystemException ex) {
		    System.err.println("Error in roll back of transaction: " + ex);
		}
    	}
    }

    // setup the initial state if phonebook is empty
    private static void setupIfNeed(PhoneBook pb) {
	if (pb.getPersonSet().isEmpty())
	    SetupDomain.populateDomain();
    }
}
