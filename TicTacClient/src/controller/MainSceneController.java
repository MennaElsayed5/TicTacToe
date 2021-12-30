package controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Eslam Esmael
 */
public class MainSceneController implements Initializable {
    SceneController controller;
    
    @FXML
    private ImageView applicationImageView;
    
    
    @FXML
    private void handleVsAiBtn(ActionEvent event){
        controller = new SceneController();
        try {
            controller.switchToPlayerVsAIScene(event);
        } catch (IOException ex) {
            Logger.getLogger(MainSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 
    
    @FXML
    private void handleVsPlayerBtn(ActionEvent event){
        controller = new SceneController();
        try {
            controller.switchToPlayerVsPlayerScene(event);
        } catch (IOException ex) {
            Logger.getLogger(MainSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void handleVsPlayerOnlineBtn(ActionEvent event){
               String ip ="";
        //  validation IpVaild = new validation();
        //  boolean flagIp = IpVaild.isStringNumeric(ip);
        //  int countIP = IpVaild.periodCount(ip);
       // int octIp = IpVaild.parseOctetToInt(ip);

            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Dialog");
            dialog.setContentText("Please Enter The Server IP Address :");
            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()) {
            }
            ip = result.get();
            
            //Temporarily
     boolean flag =ipVaild(ip);
       if(flag){
            controller = new SceneController();
        try {
            controller.switchToOnlineScene(event);
        } catch (IOException ex) {
            Logger.getLogger(MainSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }
       }
       else{
       Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error Dialog");
            alert.setContentText("Wrong IP Try Again");
            alert.showAndWait();
       }
    }
     public boolean ipVaild(String Ip) {
        if (Ip == null) {
            return false;
        }
        String ip = "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\."
                + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
                + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
                + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$";
        Pattern pattern = Pattern.compile(ip);
        Matcher matcher = pattern.matcher(Ip);
        return matcher.matches();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        controller = new SceneController();
        applicationImageView.setImage(new Image(getClass().getResourceAsStream("/assets/splashImg.png")));
    }    
    
}
