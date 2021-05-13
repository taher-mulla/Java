package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class DialogueControler {

    @FXML
    private TextField firstNameTextField;
    @FXML
    private TextField lastNaeTextField;
    @FXML
    private TextField phoneNumberTextField;
    @FXML
    private TextArea notesTextField;

    public ContactDetails results(){
        String firstName = firstNameTextField.getText().trim();
        String lastName = lastNaeTextField.getText().trim();
        String phoneNumber = phoneNumberTextField.getText().trim();
        String notes = notesTextField.getText().trim();

        return (new ContactDetails(firstName,lastName,phoneNumber,notes));
    }

    public void setDialogue(ContactDetails contact){
        firstNameTextField.setText(contact.getFirstName());
        lastNaeTextField.setText(contact.getLastName());
        phoneNumberTextField.setText(contact.getPhoneNumber());
        notesTextField.setText(contact.getNote());
    }

}
