package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
      /*{ THE BELOW CODE IS USED TO BYPASS THE .fxml FILE AND RUN THE SAME FUNCTIONS FROM HERE
            IT IS BETTER TO USE THE .fxml FILE BUT.
            TO CHECK THE WORKING OF THIS COMMENT LINE 40 { Parent root = FXMLLoader.load(getClass().getResource("sample.fxml")); ]

        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

            //this ling is calling the .fxml file

        GridPane root = new GridPane(); //this is setting the window
        root.setAlignment(Pos.CENTER);
        root.setVgap(10);
        root.setHgap(10);

        Label greeting = new Label("Welcome to my world...");
            //setting a label and giving that object to greeting
        greeting.setTextFill(Color.GREEN);
        greeting.setFont(Font.font("Times New Roman", FontWeight.BOLD, 70));
        root.getChildren().add(greeting);
        }*/


        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 1000, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
