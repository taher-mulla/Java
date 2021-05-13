package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Contacts {

    private ObservableList<ContactDetails> observableContactsList = FXCollections.observableArrayList();


    public void addContact(ContactDetails contact){
        observableContactsList.add(contact);

    }

}
