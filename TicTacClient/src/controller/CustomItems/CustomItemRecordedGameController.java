/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.CustomItems;

import controller.RecordedGameController;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author mina
 */
public class CustomItemRecordedGameController {
    
    @FXML
     TextField itemListTextField;
    
    FXMLLoader fxmlLoader;
    
      public void setInfo(String name)
    {
        itemListTextField.setText(name);
    }
      public void showRecordedGames()
      {
        try {
            fxmlLoader = new FXMLLoader(getClass().getResource("/view/RecordedGameView.fxml"));
            Parent root = fxmlLoader.load();
            ((RecordedGameController) fxmlLoader.getController()).setFileName(itemListTextField.getText());
              Scene scene = new Scene(root);
            Stage window = (Stage) itemListTextField.getScene().getWindow();
            window.setScene(scene);
            window.show();
        } catch (IOException ex) {
            Logger.getLogger(CustomItemRecordedGameController.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
    
}
