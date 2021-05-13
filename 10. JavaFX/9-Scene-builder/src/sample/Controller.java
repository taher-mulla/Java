package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Controller {

    @FXML
    private Label lable;

    @FXML
    public void handleAction(){
        lable.setText("Ok Button Pressed");
    }
}
