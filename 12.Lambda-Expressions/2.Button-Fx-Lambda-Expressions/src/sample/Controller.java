//Java Fx is the most common use of Lambda expressions

package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.BufferedReader;

public class Controller {

    //anonymous class
    @FXML
    private Button clickMeButtonAnonymousClass;

    //lambda expression
    @FXML
    private Button clickMeButtonLambdaExpression;

    public void initialize(){

        //using the anonymous class to set a 'onAction'
        clickMeButtonAnonymousClass.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("Anonymous Class clicked me");
            }
        });

        //using the lambda expression to set the 'onAction'
        clickMeButtonLambdaExpression.setOnAction(event -> System.out.println("Lambda Expression clicked me"));

    }

}
