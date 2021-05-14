package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;

public class Controller {


//    private Task<ObservableList> task;

    @FXML
    private ProgressBar progressBar;
    @FXML
    private Label progressLabel;
    @FXML
    private ListView listView;
    private Service<ObservableList<String>> service;



    public void initialize(){

//        task = new Task<ObservableList>() {
//            @Override
//            protected ObservableList call() throws Exception {
//
//                String[] names = {"Tim B",
//                        "Taher M",
//                        "Sohail M",
//                        "Harry P",
//                        "Emma W"};
//                ObservableList<String> employees = FXCollections.observableArrayList();
//                for(int i=0; i<5; i++){
//                    employees.add(names[i]);
//                    updateMessage("Added "+names[i]+" to the list");
//                    updateProgress(i+1,5);
//                    Thread.sleep(200);
//                }
//                return employees;
//            }
//        };

        service = new EmployeeService();

        

        progressLabel.textProperty().bind(service.messageProperty());
        listView.itemsProperty().bind(service.valueProperty());
        progressBar.progressProperty().bind(service.progressProperty());


//        progressLabel.textProperty().bind(task.messageProperty());
//        listView.itemsProperty().bind(task.valueProperty());
//        progressBar.progressProperty().bind(task.progressProperty());


    }

    @FXML
    public void buttonPressed(){
        if(service.getState() == Service.State.SUCCEEDED){
            service.reset();
            service.start();
        }else if(service.getState() == Worker.State.READY){
            service.start();
        }
    }

}
