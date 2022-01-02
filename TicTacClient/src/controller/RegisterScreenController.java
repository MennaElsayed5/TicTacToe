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

/**
 *
 * @author menna
 */
public class RegisterScreenController implements Initializable {

    @FXML
    private void buttonBackPressed(ActionEvent event) {
        SceneNavigationController controller = new SceneNavigationController();
        System.out.println("BackPressed");
        try {
            controller.switchToMainScene(event);
        } catch (IOException ex) {
            Logger.getLogger(RegisterScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    
    @FXML
    private  void btnRegisterToMainOnline(ActionEvent event){
        SceneNavigationController controller= new SceneNavigationController();
        try {
            controller.switchToOnlineMainScene(event);
        } catch (IOException ex) {
            Logger.getLogger(RegisterScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
