/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;

/**
 *
 * @author menna
 */
public class FirstScreenAfterEnteringIP1 {
      SceneController controller;
     private void handleRegisterBtn(ActionEvent event){
        controller = new SceneController();
          try {
              controller.switchToRegisterScene(event);
          } catch (IOException ex) {
              Logger.getLogger(FirstScreenAfterEnteringIP1.class.getName()).log(Level.SEVERE, null, ex);
          }
    }
}
