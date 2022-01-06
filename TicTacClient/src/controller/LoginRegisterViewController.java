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
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Player;

/**
 *
 * @author menna
 */
public class LoginRegisterViewController implements Initializable {

    SceneNavigationController controller;
    @FXML
    private Button btnLogin;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private ProgressIndicator progress;

    @FXML
    private Button btnRegister;

    @FXML
    private void handleRegisterBtn(ActionEvent event) {
        controller = new SceneNavigationController();
        try {
            controller.switchToRegisterScene(event);
        } catch (IOException ex) {
            Logger.getLogger(LoginRegisterViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void buttonBackPressed() {
        Stage stage = (Stage) btnLogin.getScene().getWindow();
        try {
            controller.switchToMainScene(stage);
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
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 100, 10, 10));
        grid.add(emailLabel, 1, 1);
        grid.add(emailField, 2, 1);
        grid.add(passwordLabel, 1, 2);
        grid.add(passwordFeild, 2, 2);
        dialog.getDialogPane().setContent(grid);
        ButtonType buttonTypeOk = new ButtonType("Okay", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().add(buttonTypeOk);
        Optional<String> result = dialog.showAndWait();

        if (emailField.getText().isEmpty()) {
            errorAlert("Please Enter Your Email");
        } else if (passwordFeild.getText().isEmpty()) {
            errorAlert("Please Enter Your Password");
        } else if (checkEmailvalidity(passwordFeild.getText())) {
            errorAlert("Please Enter Valid Email");
        } else if (passwordFeild.getText().length() < 8 || passwordFeild.getText().length() > 16) {
            errorAlert("Please Enter Valid Password");
        } else {
            blockUi();
            Thread th = new Thread(new Runnable() {
                @Override
                public void run() {

                    //login(emailField.getText(), passwordFeild.getText());
                    login("Menna@menna.com", "123456789");

                }
            });
            th.start();
        }

    }

    public void errorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Error Dialog");
        alert.setContentText(message);
        alert.showAndWait();
    }

    public boolean checkEmailvalidity(String email) {
        Pattern pattern = Pattern.compile("^[\\w_\\.+]*\\@([\\w]+\\.)+[\\w]+[\\w]$");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public void login(String email, String password) {
        try {
            ConnectionHelper.connectToServer();
            Player obj = new Player(email, password);
            (ConnectionHelper.getObjectOutputStream()).writeObject(obj);
            ConnectionHelper.getObjectInputStream().readObject();
            Stage stage = (Stage) btnLogin.getScene().getWindow();
            controller.switchToOnlineMainScene(stage);
        } catch (SocketException ex) {
            ConnectionHelper.disconnectFromServer();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RegisterScreenController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (EOFException ex) {
            ConnectionHelper.disconnectFromServer();
        } catch (IOException ex) {
            unblockUi();
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
                ConnectionHelper.showErrorDialog("incorrect email or password");
            }
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        controller = new SceneNavigationController();
    }
}
