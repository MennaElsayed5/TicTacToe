package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
        controller = new SceneController();
        try {
            controller.switchToOnlineScene(event);
        } catch (IOException ex) {
            Logger.getLogger(MainSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        controller = new SceneController();
        applicationImageView.setImage(new Image(getClass().getResourceAsStream("/assets/splashImg.png")));
    }    
    
}
