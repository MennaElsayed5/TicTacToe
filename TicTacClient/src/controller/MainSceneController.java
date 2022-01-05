package controller;


import helper.ConnectionHelper;
import java.io.IOException;
import java.net.URL;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


/**
 *
 * @author Eslam Esmael
 */
public class MainSceneController implements Initializable {
    @FXML
    private Button playerVsAiBtn;

    SceneNavigationController controller;
    static String fname;

    @FXML
    private void handleVsAiBtn(ActionEvent event) {
        Stage stage=(Stage)playerVsAiBtn.getScene().getWindow();
        try {
            controller.switchToPlayerVsAIScene(stage);
        } catch (IOException ex) {
            Logger.getLogger(MainSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handleVsPlayerBtn(ActionEvent event) {
        String namePlayer11 = "";
        String namePlayer22 = "";
        boolean flag = true;
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Enter Names");
        Label namePlayer1 = new Label("Name player 1: " + namePlayer11);
        Label namePlayer2 = new Label("Name player 2: " + namePlayer22);
        TextField text1 = new TextField();
        TextField text2 = new TextField();
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 100, 10, 10));
        grid.add(namePlayer1, 1, 1);
        grid.add(text1, 2, 1);
        grid.add(namePlayer2, 1, 2);
        grid.add(text2, 2, 2);
        dialog.getDialogPane().setContent(grid);
        ButtonType buttonTypeOk = new ButtonType("Okay", ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().add(buttonTypeOk);
        Optional<String> result = dialog.showAndWait();

        if (result.isPresent()) {

            if (!text1.getText().isEmpty() && !text2.getText().isEmpty()) {
                PlayerVsPlayerController.playerOneName = text1.getText();
                PlayerVsPlayerController.playerTwoName = text2.getText();
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/PlayerVsPlayerView.fxml"));
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    Scene scene = new Scene(loader.load());

                    //((PlayerVsPlayerController) loader.getController()).setNames(text1.getText(), text2.getText());
                    stage.setScene(scene);
                    stage.show();

                } catch (IOException ex) {
                    Logger.getLogger(MainSceneController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid user Name");
                alert.setHeaderText("Error Dialog");
                alert.setContentText("Please Enter user name for both players ");
                alert.showAndWait();
            }

        } else {
            try {
                controller = new SceneNavigationController();
                Stage stage=(Stage)playerVsAiBtn.getScene().getWindow();
                controller.switchToMainScene(stage);
            } catch (IOException ex) {
                Logger.getLogger(MainSceneController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void handleVsPlayerOnlineBtn(ActionEvent event) {
              String ip = "";
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Dialog");
        dialog.setContentText("Please Enter The Server IP Address : ");
        Optional<String> result = dialog.showAndWait();
    
     //boolean flag =  ConnectionHelper.isConnected();
         boolean ex_flag = true;
         try{
        if (isIPVaild(result.get()) == true) {
            try {
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        try {
//                            ConnectionHelper.connectToServer();
//                                  //show.setConnected(ConnectionHelper.isConnected());
//
//                        } catch (IOException ex) {
//                            Logger.getLogger(MainSceneController.class.getName()).log(Level.SEVERE, null, ex);
//                        }
//                    }
//                }).start();
               ConnectionHelper.connectToServer();
            
                controller = new SceneNavigationController();
                controller.switchToOnlineScene(event);
            } catch (IOException ex) {
              ConnectionHelper.showErrorDialog("Server Not Connection");
                System.out.println("not connect");
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error Dialog");
            alert.setContentText("Wrong IP Try Again");
            alert.showAndWait();
        }
         }catch (NoSuchElementException e) {
            try {
                ex_flag = false;
                controller = new SceneNavigationController();
                 Stage stage=(Stage)playerVsAiBtn.getScene().getWindow();
                controller.switchToMainScene(stage);
            } catch (IOException ex) {
                Logger.getLogger(MainSceneController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (Exception ex) {
            Logger.getLogger(MainSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    @FXML
    private void handleExitButton(ActionEvent event) {
        Platform.exit();
       // ConnectionHelper.disconnectFromServer();
    }

    public boolean isIPVaild(String Ip) {
        if (Ip == null) {
            return false;
        }
        String ip = "^"
                + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\."
                + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
                + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
                + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$";
        Pattern pattern = Pattern.compile(ip);
        Matcher matcher = pattern.matcher(Ip);
        return matcher.matches();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        controller = new SceneNavigationController();
    }

}
