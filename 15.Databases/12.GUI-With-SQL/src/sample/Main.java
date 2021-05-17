package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.model.Datasource;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root = loader.load();
        Controller controller = loader.getController();
        controller.listArtists();

        primaryStage.setTitle("Music Database");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }

    @Override
    public void init() throws Exception {
        super.init();
        if(!Datasource.getInstance().open()) {
            System.out.println("FATAL ERROR: Couldn't connect to database");
            Platform.exit();
        }
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        Datasource.getInstance().close();
    }

    public static void main(String[] args) {
        launch(args);
    }
}





//***********************************************


/*
package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.model.Datasource;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        //we are doing this to get access to the controller so that we can load the artists
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));

        //we are doing this to get access to the controller so that we can load the artists
        Parent root = loader.load();

        //we are doing this to get access to the controller so that we can load the artists
        Controller controller = loader.getController();

        //we are calling the listArtist to display
        controller.listArtist();

        primaryStage.setTitle("Music Database");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }


    //this is called before the fxml start
    @Override
    public void init() throws Exception {
        super.init();

       if(!Datasource.getInstance().open()){
           System.out.println("Could not connect to the database");
           Platform.exit();
       }
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        Datasource.getInstance().close();
    }

    public static void main(String[] args) {
        launch(args);
    }
}*/
