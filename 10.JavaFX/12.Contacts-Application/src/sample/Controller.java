package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.util.Iterator;
import java.util.Optional;

public class Controller {

    @FXML
    private BorderPane mainWindow;
    @FXML
    private TableView tableView;
    @FXML
    private TableColumn tableViewFirstName;
    @FXML
    private TableColumn tableViewLastName;
    @FXML
    private TableColumn tableViewPhoneNumber;
    @FXML
    private TableColumn tableViewNotes;
    private ObservableList<ContactDetails> observableContactsList = FXCollections.observableArrayList();



    public void initialize(){

//        Contacts contacts = new Contacts();

        ContactDetails contact = new ContactDetails("Taher", "Mulla",
                "999110926", "Taher's Phone");
        observableContactsList.add(contact);
        contact = new ContactDetails("Sohail", "Mulla",
                "9999999", "Sohail's Phone");
        observableContactsList.add(contact);
        contact = new ContactDetails("Yash", "Oswal",
                "91111111111926", "Yash's Phone");
        observableContactsList.add(contact);
        contact = new ContactDetails("Rohan", "Bhand",
                "990000000", "Bhand's Phone");
        observableContactsList.add(contact);


        tableViewFirstName.setCellValueFactory(new PropertyValueFactory<ContactDetails,String>("firstName"));
        tableViewLastName.setCellValueFactory(new PropertyValueFactory<ContactDetails, String>("lastName"));
        tableViewPhoneNumber.setCellValueFactory(new PropertyValueFactory<ContactDetails,String>("phoneNumber"));
        tableViewNotes.setCellValueFactory(new PropertyValueFactory<ContactDetails, String>("note"));


        tableView.setItems(observableContactsList);
        tableView.getColumns().addAll(tableViewFirstName,tableViewLastName,tableViewPhoneNumber,tableViewNotes);


    }

    public void menuItemNewClicked(){
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainWindow.getScene().getWindow());

        System.out.println("New clicked");

        FXMLLoader fxmlLoder = new FXMLLoader();
        fxmlLoder.setLocation(getClass().getResource("NewContactDialogue.fxml"));

        try{
            dialog.getDialogPane().setContent(fxmlLoder.load());
        }catch(IOException e){
            System.out.println("Could not load the dialogue");
            e.printStackTrace();
            return;
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        Optional<ButtonType> result = dialog.showAndWait();

        if(result.isPresent() && result.get()==ButtonType.OK){
            System.out.println("Okay presses");
            DialogueControler controller = fxmlLoder.getController();
            ContactDetails newContact = controller.results();
            observableContactsList.add(newContact);
        }else{
            System.out.println("Cancel presses");
        }
    }

    public void menuItemDeleteClicked(){
        //Iterator iterator = new Ob
        System.out.println(observableContactsList.remove(tableView.getSelectionModel().getSelectedItem()));
    }

    public void menuItemEditClicked(){
        ContactDetails contact = (ContactDetails)tableView.getSelectionModel().getSelectedItem();
//        System.out.println(contact.getFirstName());

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainWindow.getScene().getWindow());

        System.out.println("Edit clicked");

        FXMLLoader fxmlLoder = new FXMLLoader();
        fxmlLoder.setLocation(getClass().getResource("NewContactDialogue.fxml"));

        try{
            dialog.getDialogPane().setContent(fxmlLoder.load());
        }catch(IOException e){
            System.out.println("Could not load the dialogue");
            e.printStackTrace();
            return;
        }

        DialogueControler controller = fxmlLoder.getController();
        controller.setDialogue(contact);

        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        Optional<ButtonType> result = dialog.showAndWait();



        if(result.isPresent() && result.get()==ButtonType.OK){
            System.out.println("Okay presses");

            observableContactsList.remove(tableView.getSelectionModel().getSelectedItem());

            ContactDetails newContact = controller.results();
            observableContactsList.add(newContact);
        }else{
            System.out.println("Cancel presses");
        }

    }



}
