package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Controller {

    @FXML
    private TextField nameField;
    @FXML
    private Button clickMe;
    @FXML
    private Button secondButton;
    @FXML
    private CheckBox ourCheckBox;
    @FXML
    private Label ourLabel;

    @FXML
    public void onButtonClicked(ActionEvent e){
//        System.out.println("Hey, " +nameField.getText());
//        System.out.println("The following button was pressed "+e.getSource());

        if(e.getSource().equals(clickMe)){
            System.out.println("'Click Me' pressed");
        }else if(e.getSource().equals(secondButton)){
            System.out.println("'Second button' pressed");
        }


	//example of a thread, pls don't read if not understanding read the Concurrency section 
        Runnable task = new Runnable() {
            @Override
            public void run() {
                try{
                    String s =Platform.isFxApplicationThread() ? "UI thread is active" :
                                "Background thread is active";
                    System.out.println("I a going to sleep - current in run() --- "+s);
                    Thread.sleep(10000);
                    Platform.runLater(new Runnable() {
                        //Needed cause UI cant have 2 threads running and updating the
                        //UI interface at the same time. This will sorta run cause it is beeing forced
                        @Override
                        public void run() {
                            String s =Platform.isFxApplicationThread() ? "UI thread is active" :
                                    "Background thread is active";
                            System.out.println("I am awake and now updating the label - current in try block run() --- "+s);
                            ourLabel.setText("We did something");
                        }
                    });
                }catch (InterruptedException event){ }
            }
        };
        new Thread(task).start();

//        try{
//            Thread.sleep(10000);
//        }catch (InterruptedException event){
//        }

        if (ourCheckBox.isSelected()){
            nameField.clear();
            secondButton.setDisable(true);
            clickMe.setDisable(true);
        }
    }

    @FXML
    public void handleKeyRelease(){
        String text = nameField.getText();
        boolean disableButtons = text.isEmpty() || text.trim().isEmpty();
        secondButton.setDisable(disableButtons);
        clickMe.setDisable(disableButtons);
    }

    @FXML
    public void handleChange(){
        System.out.println("The checkbox is "+(ourCheckBox.isSelected() ? "checked" : "not checked"));
    }
}
