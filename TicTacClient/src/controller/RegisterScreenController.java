/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import helper.ConnectionHelper;
import java.io.EOFException;
import java.io.IOException;
import java.net.SocketException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Player;

/**
 *
 * @author menna
 */
public class RegisterScreenController implements Initializable {

    @FXML
    private TextField userNameTextField;
    @FXML
    private TextField EmailTextField;
    @FXML
    private PasswordField passwordTextField;
    
    SceneNavigationController controller;

    @FXML
    private void buttonBackPressed() {
        Stage stage=(Stage)userNameTextField.getScene().getWindow();
        System.out.println("BackPressed");
        try {
            controller.switchToMainScene(stage);
        } catch (IOException ex) {
            Logger.getLogger(RegisterScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void btnRegisterToMainOnline(ActionEvent event) {
        boolean flagName = isValidUsername(userNameTextField.getText());
        boolean flagEmail = isValidEmail(EmailTextField.getText());
        boolean flagPassword = isaValidPassword(passwordTextField.getText());

        if (flagName && flagEmail && flagPassword) {
          //  register("menna", "menna@gmail.com", "11111111");
            try {
                Stage stage=(Stage)userNameTextField.getScene().getWindow();
                controller.switchToOnlineMainScene(stage);
            } catch (IOException ex) {
                Logger.getLogger(RegisterScreenController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            if (!flagName) {
                errorAlert("Please Enter Valid Name");
                userNameTextField.clear();
            } 
            else if (!flagEmail) {
                errorAlert("Please Enter Valid Email");
                EmailTextField.clear();
            } 
            else if (!flagPassword) {
                errorAlert("Invalid password");
                passwordTextField.clear();
            } 
        }

    }

 public void register(String name, String email, String password) {
        try {
            ConnectionHelper.connectToServer();
            Player obj = new Player(name, email, password);
            (ConnectionHelper.getObjectOutputStream()).writeObject(obj);
            Player respons = (Player) ConnectionHelper.getObjectInputStream().readObject();
        } catch (SocketException ex) {
            ConnectionHelper.disconnectFromServer();

        } catch (EOFException ex) {
            ConnectionHelper.disconnectFromServer();
        } catch (IOException ex) {
            Logger.getLogger(RegisterScreenController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RegisterScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean isValidUsername(String name) {
        String regex = "^[A-Za-z]\\w{2,29}$";
        Pattern p = Pattern.compile(regex);
        if (name == null) {
            return false;
        }
        if (name.length() >= 30) {
            return false;
        }
        Matcher m = p.matcher(name);

        return m.matches();
    }

    public boolean isaValidPassword(String password) {
        if (!((password.length() >= 8) && (password.length() <= 29))) {
            return false;
        }
        if (password.contains(" ")) {
            return false;
        }
        if ((password.contains("#"))) {
            return false;
        }
        return true;
    }

    public boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile("^[\\w_\\.+]*\\@([\\w]+\\.)+[\\w]+[\\w]$");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public void errorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Error Dialog");
        alert.setContentText(message);
        alert.showAndWait();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        controller = new SceneNavigationController();
    }
}
