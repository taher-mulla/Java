package javafx.toDoList;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.toDoList.dataModle.ToDoItem;
import javafx.toDoList.dataModle.TodoData;
import javafx.util.Callback;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

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
    @FXML
    private ContextMenu listContextMenue;
    @FXML
    private ToggleButton filterToggleButton;
    private FilteredList<ToDoItem> filteredList;
    private Predicate<ToDoItem> wantAllItems;
    private Predicate<ToDoItem> wantTodaysItems;


    public void initialize(){

        listContextMenue = new ContextMenu();
        MenuItem deleteMenuItem = new MenuItem("Delete");
        deleteMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ToDoItem item = toDoListFxmlView.getSelectionModel().getSelectedItem();
                deleteItem(item);
            }
        });

        listContextMenue.getItems().addAll(deleteMenuItem);


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

        wantAllItems = new Predicate<ToDoItem>() {
            @Override
            public boolean test(ToDoItem item) {
                return true;
            }
        };

        wantTodaysItems = new Predicate<ToDoItem>() {
            @Override
            public boolean test(ToDoItem item) {
                return item.getDealLine().equals(LocalDate.now());
            }
        };

        filteredList = new FilteredList<ToDoItem>(TodoData.getInstance().getToDoItems(),wantAllItems);

        SortedList<ToDoItem> sortedList = new SortedList<>(filteredList,
                new Comparator<ToDoItem>() {
                    @Override
                    public int compare(ToDoItem item, ToDoItem t1) {

                        return item.getDealLine().compareTo(t1.getDealLine());
                    }
                });

        //toDoListFxmlView.getItems().setAll(TodoData.getInstance().getToDoItems());
//        toDoListFxmlView.setItems(TodoData.getInstance().getToDoItems()); //Changing to show the sorted list -- sorted wrt  deadline
        toDoListFxmlView.setItems(sortedList);
        toDoListFxmlView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        toDoListFxmlView.getSelectionModel().selectFirst();

        toDoListFxmlView.setCellFactory(new Callback<ListView<ToDoItem>, ListCell<ToDoItem>>() {
            @Override
            public ListCell<ToDoItem> call(ListView<ToDoItem> toDoItemListView) {
                ListCell<ToDoItem> cell = new ListCell<>(){
                    @Override
                    protected void updateItem(ToDoItem item, boolean b) {
                        super.updateItem(item, b);
                        if(b){
                            setText(null);
                        }else{
                            setText(item.getShortDiscription());
                            if(item.getDealLine().equals(LocalDate.now())){
                                setTextFill(Color.RED);
                            }else if(item.getDealLine().equals(LocalDate.now().plusDays(1))){
                                setTextFill(Color.ORANGE);
                            }else if(item.getDealLine().isBefore(LocalDate.now())){
                                setTextFill(Color.DARKRED);
                            }
                        }
                    }
                };
                cell.emptyProperty().addListener(
                        (obs, wasEmpty, isNowEmpty) ->{
                            if(isNowEmpty){
                                cell.setContextMenu(null);
                            }else{
                                cell.setContextMenu(listContextMenue);
                            }
                        }
                );
                return cell;
            }
        });
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
//            toDoListFxmlView.getItems().setAll(TodoData.getInstance().getToDoItems());
            toDoListFxmlView.getSelectionModel().select(newItem);
            System.out.println("Ok presses");
        }else{
            System.out.println("Cancel pressed");
        }

    }

    @FXML
    public void handleKeyPressed(KeyEvent keyevent){
        ToDoItem selectedItem = toDoListFxmlView.getSelectionModel().getSelectedItem();
        if(selectedItem != null){
            if(keyevent.getCode().equals(KeyCode.DELETE));{
                deleteItem(selectedItem);
            }
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


    public void deleteItem(ToDoItem item){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete TodoItem");
        alert.setHeaderText("Delete item "+item.getShortDiscription());
        alert.setContentText("Are you sure press ok to confirm");
        Optional<ButtonType> result = alert.showAndWait();

        if( (result.isPresent()) && (result.get() == ButtonType.OK) ){
            TodoData.getInstance().deleteTodoItem(item);
        }
    }

    @FXML
    public  void handleFilterButton(){
        ToDoItem selectedItem =toDoListFxmlView.getSelectionModel().getSelectedItem();
        if (filterToggleButton.isSelected()){
            filteredList.setPredicate(wantTodaysItems);
            if(filteredList.isEmpty()){
                itemDetailsInTextArea.clear();
                deadlineLabel.setText("");
            }else if(filteredList.contains(selectedItem)){
                toDoListFxmlView.getSelectionModel().select(selectedItem);
            }else{
                toDoListFxmlView.getSelectionModel().selectFirst();
            }
        }else{
            filteredList.setPredicate(wantAllItems);
            toDoListFxmlView.getSelectionModel().select(selectedItem);
        }
    }

    @FXML
    public void handleExit(){
        Platform.exit();
    }
}
