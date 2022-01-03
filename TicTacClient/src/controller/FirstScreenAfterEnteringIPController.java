/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

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
     @FXML
    private void buttonloginPressed(ActionEvent event) {
        System.out.println("Login pressed ");
        String email = "";
        String password = "";
        boolean flag = true;
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Login");
        Label emailLabel = new Label("Email : " + email);
        Label passwordLabel = new Label("Password: " + password);
        TextField emailField = new TextField();
        PasswordField passwordFeild = new PasswordField();
        GridPane grid = new GridPane();
        grid.add(emailLabel, 1, 1);
        grid.add(emailField, 2, 1);
        grid.add(passwordLabel, 1, 2);
        grid.add(passwordFeild, 2, 2);
        dialog.getDialogPane().setContent(grid);
        ButtonType buttonTypeOk = new ButtonType("Okay", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().add(buttonTypeOk);
        Optional<String> result = dialog.showAndWait();

        if (emailField.getText().isEmpty()){
            errorAlert("Please Enter Your Email");
        } else if (passwordFeild.getText().isEmpty()){
            errorAlert("Please Enter Your Password");
        } else if(checkEmailvalidity(passwordFeild.getText())){
            errorAlert("Please Enter Valid Email");
        } else if (passwordFeild.getText().length() < 8 || passwordFeild.getText().length() > 16){
            errorAlert("Please Enter Valid Password");
        } else {
           controller = new SceneNavigationController();
            try {
                controller.switchToOnlineMainScene(event);
            } catch (IOException ex) {
                Logger.getLogger(MainSceneController.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }
    }

    public void errorAlert(String message){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Error Dialog");
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    public boolean checkEmailvalidity(String email){
        Pattern pattern = Pattern.compile("^[\\w_\\.+]*\\@([\\w]+\\.)+[\\w]+[\\w]$");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
      }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
}
