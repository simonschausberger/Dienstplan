package sample;

import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sun.plugin2.message.Message;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class CustomException extends Exception{
     CustomException(String message){
        super(message);
         Alert alert = new Alert(Alert.AlertType.ERROR);
         alert.setContentText(message);
         alert.setHeaderText("Du böser Wicht!");
         FileInputStream inputstream = null;
         try {
             inputstream = new FileInputStream("./images/mike_boese.png");
         } catch (FileNotFoundException e) {
             e.printStackTrace();
         }
         Image image = new Image(inputstream);
         ImageView imageView = new ImageView(image);
         alert.setGraphic(imageView);
         alert.showAndWait();
     }

}
