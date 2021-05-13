package javafx.toDoList;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.toDoList.dataModle.ToDoItem;
import javafx.toDoList.dataModle.TodoData;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

public class Controller {

    private List<ToDoItem> toDoItems;
    @FXML
    private ListView<ToDoItem> toDoListFxmlView;
    @FXML
    private TextArea itemDetailsInTextArea;
    @FXML
    private Label deadlineLabel;
    @FXML
    private BorderPane mainBorderPain;

    public void initialize(){
    
    //****** This is needed in the start to add new items to the list, it has been commented because the items are now in the text file
//        ToDoItem item1 = new ToDoItem("Mail Birthday card", "Buy card for Jhon",
//                LocalDate.of(2019,10,12));
//        ToDoItem item2 = new ToDoItem("Doc appointment", "See Dr.Vakil at NIBM",
//                LocalDate.of(2019,10,3));
//        ToDoItem item3 = new ToDoItem("Finish Design Proposal", "I promised mom for the NHP design",
//                LocalDate.of(2019,9,12));
//        ToDoItem item4 = new ToDoItem("Pick up Sohail", "Train timing is 9am",
//                LocalDate.of(2019,9,22));
//        ToDoItem item5 = new ToDoItem("Pick up dry cleaning", "Blazer + 2 more clothes",
//                LocalDate.of(2019,4,20));
//
//        toDoItems = new ArrayList<ToDoItem>();
//        toDoItems.add(item1);
//        toDoItems.add(item2);
//        toDoItems.add(item3);
//        toDoItems.add(item4);
//        toDoItems.add(item5);
//
//        TodoData.getInstance().setToDoItems(toDoItems);



        toDoListFxmlView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ToDoItem>() {
            @Override
            public void changed(ObservableValue<? extends ToDoItem> observableValue, ToDoItem toDoItem, ToDoItem t1) {
                if(t1 != null){
                    ToDoItem item = toDoListFxmlView.getSelectionModel().getSelectedItem();
                    itemDetailsInTextArea.setText(item.getDetails());
                    DateTimeFormatter df = DateTimeFormatter.ofPattern("MMM d, yyyy");
                    deadlineLabel.setText(df.format(item.getDealLine()));
                }
            }
        });

        toDoListFxmlView.getItems().setAll(TodoData.getInstance().getToDoItems());
        toDoListFxmlView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        toDoListFxmlView.getSelectionModel().selectFirst();
    }


    @FXML
    public void showNewItemDialog(){
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainBorderPain.getScene().getWindow());
        dialog.setTitle("New item adding window");
        dialog.setHeaderText("Use this to create a new todoItem");

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("todoItemDialogue.fxml"));

        try{

//            Parent root = FXMLLoader.load(getClass().getResource("todoItemDialogue.fxml"));
//            dialog.getDialogPane().setContent(root);
            dialog.getDialogPane().setContent(fxmlLoader.load());
        }catch (IOException e){
            System.out.println("Coulden't load the dialog");
            e.printStackTrace();
            return;
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();
        if(result.isPresent() && result.get()==ButtonType.OK){
            DialogueController controller = fxmlLoader.getController();
            ToDoItem newItem = controller.processResult();
            toDoListFxmlView.getItems().setAll(TodoData.getInstance().getToDoItems());
            toDoListFxmlView.getSelectionModel().select(newItem);
            System.out.println("Ok presses");
        }else{
            System.out.println("Cancel pressed");
        }

    }


//    OnMouseClicked="#handleClickListView"
//    This is not needed as we made a generic listner to check what is selected

//    public void handleClickListView(){
//        ToDoItem item = toDoListFxmlView.getSelectionModel().getSelectedItem();
////        System.out.println("selected item is: "+item.toString());
////        StringBuilder sb = new StringBuilder(item.getDetails());
////        sb.append("\n\n\n");
////        sb.append("Due on : "+item.getDealLine().toString());
////        itemDetailsInTextArea.setText(sb.toString());
//
//        itemDetailsInTextArea.setText(item.getDetails());
//        deadlineLabel.setText(item.getDealLine().toString());
//    }
}
