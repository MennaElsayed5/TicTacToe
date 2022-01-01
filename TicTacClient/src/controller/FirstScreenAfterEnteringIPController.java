/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 *
 * @author menna
 */
public class FirstScreenAfterEnteringIPController implements Initializable {
     SceneNavigationController controller;
     @FXML
     private Button btnLogin;
     
     @FXML
     private Button btnRegister;
     
     @FXML
     private Button btnBack;
     
     @FXML
     private void handleRegisterBtn(ActionEvent event){
        controller = new SceneNavigationController();
          try {
              controller.switchToRegisterScene(event);
          } catch (IOException ex) {
              Logger.getLogger(FirstScreenAfterEnteringIPController.class.getName()).log(Level.SEVERE, null, ex);
          }
    }
     @FXML
    private void buttonBackPressed(ActionEvent event) {
         controller = new SceneNavigationController();
       
        try {
            controller.switchToMainScene(event);
        } catch (IOException ex) {
            Logger.getLogger(PlayerVsAIViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
}
