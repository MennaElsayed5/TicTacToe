/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.sun.javafx.scene.SceneHelper;
import helper.ConnectionHelper;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
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
import model.PlayerOnline;

/**
 *
 * @author menna
 */
public class LoginRegisterViewController implements Initializable {

    Thread listener;

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
    private void buttonloginPressed(ActionEvent event) throws IOException {
        System.out.println("Login pressed ");
        String email = "";
        String password = "";

        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Login");
        Label emailLabel = new Label("Email : " + email);
        Label passwordLabel = new Label("Password: " + password);
        TextField emailField = new TextField();
        PasswordField passwordFeild = new PasswordField();

        emailField.setText("Test1@mail.com");
        passwordFeild.setText("123456789");

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

        //checking result
        if (result.isPresent()) {
            if (emailField.getText().isEmpty()) {
                errorAlert("Please Enter Your Email");
            } else if (passwordFeild.getText().isEmpty()) {
                errorAlert("Please Enter Your Password");
            } else if (checkEmailvalidity(passwordFeild.getText())) {
                errorAlert("Please Enter Valid Email");
            } else if (passwordFeild.getText().length() < 8 || passwordFeild.getText().length() > 16) {
                errorAlert("Please Enter Valid Password");
            } else {
                //blockUi();
                ConnectionHelper.getObjectOutputStream().writeObject(new PlayerOnline(emailField.getText(), passwordFeild.getText()));
                ConnectionHelper.getObjectOutputStream().flush();
                if (!listener.isAlive()) {
                    listener.start();
                }
            }
        } else {
            controller = new SceneNavigationController();
            try {
                listener.stop();
                controller.switchToLoginScene(event);
            } catch (IOException ex) {
                Logger.getLogger(LoginRegisterViewController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
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

    public boolean checkEmailvalidity(String email) {
        Pattern pattern = Pattern.compile("^[\\w_\\.+]*\\@([\\w]+\\.)+[\\w]+[\\w]$");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        controller = new SceneNavigationController();
        listener = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        //Todo uncomment next line 
                        //ConnectionHelper.getInstanceOf(ConnectionHelper.IP_FROM_CLIENT);
                        
                        //TODO comment next line 
                        ConnectionHelper.getInstanceOf(ConnectionHelper.SERVER_IP);
                        Object object = ConnectionHelper.getObjectInputStream().readObject();
                        if (object != null) {
                            if (object instanceof String) {
                                String loginResultString = (String) object;
                                if (loginResultString.equals("notFound")) {
                                    errorAlert("Wrong email or password");
                                }
                            } else if (object instanceof Player) {
                                Player player = (Player) object;
                                Stage stage = (Stage) btnLogin.getScene().getWindow();
                                controller.switchToOnlineMainScene(stage);
                            }
                            listener.stop();
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
