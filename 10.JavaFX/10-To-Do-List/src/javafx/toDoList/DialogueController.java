package javafx.toDoList;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.toDoList.dataModle.ToDoItem;
import javafx.toDoList.dataModle.TodoData;

import java.time.LocalDate;

// *************** This is the controller to control the dialogue panel ***************

public class DialogueController {
    @FXML
    private TextField shortDescriptionField;
    @FXML
    private TextArea detailsArea;
    @FXML
    private DatePicker deadlinePicker;

    public ToDoItem processResult(){
        String shortDescription = shortDescriptionField.getText().trim();
        String details = detailsArea.getText().trim();
        LocalDate dateValue = deadlinePicker.getValue();

        ToDoItem newItem = new ToDoItem(shortDescription, details, dateValue);

        TodoData.getInstance().addTodoItem(newItem);

        return newItem;
    }

}
