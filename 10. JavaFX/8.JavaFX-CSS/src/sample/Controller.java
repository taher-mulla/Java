package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.List;

public class Controller {

    @FXML
    private Label lable;
    @FXML
    private Button button4;
    @FXML
    private GridPane gridPane;


    public void initialize(){
        button4.setEffect(new DropShadow());
    }

    @FXML
    public void handleMouseEnter(){
        lable.setScaleX(2.0);
        lable.setScaleY(2.0);
    }

    @FXML
    public void handleMouseExit(){
        lable.setScaleX(1.0);
        lable.setScaleY(1.0);
    }

    @FXML
    public void handleClick(){
//        DirectoryChooser chooser = new DirectoryChooser();
//        File file = chooser.showDialog(gridPane.getScene().getWindow());

//        FileChooser chooser = new FileChooser();
//      File file =   chooser.showOpenDialog(gridPane.getScene().getWindow());

        FileChooser chooser = new FileChooser();
        chooser.setTitle("Save application file");
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text","*.txt"),
                new FileChooser.ExtensionFilter("PDF", "*.pdf"),
                new FileChooser.ExtensionFilter("Image files", "*.png","*.gif","*.jpg"),
                new FileChooser.ExtensionFilter("All Files", "*.*")
        );

        List<File> file =   chooser.showOpenMultipleDialog(gridPane.getScene().getWindow());

        if(file != null){
            for(int i=0;i< file.size();i++){
                System.out.println(file.get(i));
            }
//            System.out.println(file.getPath());
        }else{
            System.out.println("Chooser was cancelled");
        }
    }

    @FXML
    public void handleLinkClick(){
        System.out.println("Link was clicked");


      /*  try{
            Desktop.getDesktop().browse(new URI("http://www.javafc.com"));
        }catch (IOException e){
            e.printStackTrace();
        }catch (URISyntaxException e){
            e.printStackTrace();
        }*/

    }

}
