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
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Player;
import model.PlayerOnline;
import model.RegisterModel;

/**
 *
 * @author menna
 */
public class RegisterScreenController implements Initializable {

    Thread listener;

    @FXML
    private TextField userNameTextField;
    @FXML
    private TextField EmailTextField;
    @FXML
    private PasswordField passwordTextField;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private ProgressIndicator progress;

    SceneNavigationController controller;

    @FXML
    private void buttonBackPressed() {
        Stage stage = (Stage) userNameTextField.getScene().getWindow();
        System.out.println("BackPressed");
        try {
            controller.switchToMainScene(stage);
        } catch (IOException ex) {
            Logger.getLogger(RegisterScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnRegisterToMainOnline(ActionEvent event) {

        if (isValidUsername(userNameTextField.getText())) {
            if (isValidEmail(EmailTextField.getText())) {
                if (isaValidPassword(passwordTextField.getText())) {
                    try {
                        ConnectionHelper.getObjectOutputStream().writeObject(new RegisterModel(userNameTextField.getText(), EmailTextField.getText(), passwordTextField.getText()));
                        ConnectionHelper.getObjectOutputStream().flush();
                        if (!listener.isAlive()) {
                            listener.start();
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(RegisterScreenController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    errorAlert("invalid password");
                }
            } else {
                errorAlert("Invalid email");
            }
        } else {
            errorAlert("Invalid UserName");
        }

    }

    public void blockUi() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                anchorPane.setDisable(true);
                progress.setStyle(" -fx-progress-color: black");
                progress.setVisible(true);
            }

        });

    }

    public void unblockUi() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                anchorPane.setDisable(false);
                progress.setVisible(false);
            }
        });

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
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Error Dialog");
                alert.setContentText(message);
                alert.showAndWait();
            }
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        controller = new SceneNavigationController();
        listener = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Object object = ConnectionHelper.getObjectInputStream().readObject();
                        if (object != null) {
                            //return from server

                            if (object instanceof String) {
                                String result = (String) object;
                                if (result.equals("PlayerRegistered")) {
                                    Stage stage = (Stage) EmailTextField.getScene().getWindow();
                                    controller.switchToOnlineMainScene(stage);
                                } else if (result.equals("ServerError")) {
                                    errorAlert("Can't insert player to db may be already registered ");
                                }
                            }
                        }
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        listener.stop();
                    } catch (ClassNotFoundException ex) {
                        ex.printStackTrace();
                        listener.stop();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        listener.stop();
                    }
                }
            }
        });

    }
}
