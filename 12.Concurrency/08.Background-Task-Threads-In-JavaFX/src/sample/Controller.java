package sample;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;

public class Controller {

    private Task<ObservableList> task;

    @FXML
    private ProgressBar progressBar;
    @FXML
    private Label progressLabel;
    @FXML
    private ListView listView;

    public void initialize(){


        task = new Task<ObservableList>() {
            @Override
            protected ObservableList call() throws Exception {
                Thread.sleep(1000);
                String[] names = {"Tim B",
                        "Taher M",
                        "Sohail M",
                        "Harry P",
                        "Emma W"};
                ObservableList<String> employees = FXCollections.observableArrayList();
                for(int i=0; i<5; i++){
                    employees.add(names[i]);
                    updateMessage("Added "+names[i]+" to the list");
                    updateProgress(i+1,5);
                    Thread.sleep(200);
                }

                //WE WONT NEED THIS WHEN WE USE THE PROGRESSBAR
//                ObservableList<String> employees = FXCollections.observableArrayList(
//                        "Tim B",
//                        "Taher M",
//                        "Sohail M",
//                        "Harry P",
//                        "Emma W");

                //THIS RUNS AFTER THE THREAD HAS FINISHED ITS WORK, TO UPDATE THE UI
                //BUT THIS IS A IN-EFFICIENT WAY TO DO THE UI UPDATE
//                Platform.runLater(new Runnable() {
//                    @Override
//                    public void run() {
//                        listView.setItems(employees);
//                    }
//                });
                return employees;
            }
        };


        progressLabel.textProperty().bind(task.messageProperty());
        listView.itemsProperty().bind(task.valueProperty());
        progressBar.progressProperty().bind(task.progressProperty());

    }

    @FXML
    public void buttonPressed(){
        new Thread(task).start();

    }

}
