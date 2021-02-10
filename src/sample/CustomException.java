package sample;

import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sun.plugin2.message.Message;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class CustomException extends Exception{
     CustomException(String content, String header, Alert.AlertType alertType, FileInputStream inputStream){
        super(content);
         Alert alert = new Alert(alertType);
         alert.setContentText(content);
         alert.setHeaderText(header);
         Image image = new Image(inputStream);
         ImageView imageView = new ImageView(image);
         alert.setGraphic(imageView);
         alert.showAndWait();
     }
}
