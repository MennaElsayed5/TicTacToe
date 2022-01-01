package controller;

import java.io.IOException;
import java.net.URL;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author Eslam Esmael
 */
public class MainSceneController implements Initializable {

    SceneController controller;

    @FXML
    private ImageView applicationImageView;

    @FXML
    private void handleVsAiBtn(ActionEvent event) {
        controller = new SceneController();
        try {
            controller.switchToPlayerVsAIScene(event);
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
        dialog.setTitle("Dialog");
        Label namePlayer1 = new Label("Name player 1: " + namePlayer11);
        Label namePlayer2 = new Label("Name player 2: " + namePlayer22);
        TextField text1 = new TextField();
        TextField text2 = new TextField();
        GridPane grid = new GridPane();
        grid.add(namePlayer1, 1, 1);
        grid.add(text1, 2, 1);
        grid.add(namePlayer2, 1, 2);
        grid.add(text2, 2, 2);
        dialog.getDialogPane().setContent(grid);
        ButtonType buttonTypeOk = new ButtonType("Okay", ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().add(buttonTypeOk);
        Optional<String> result = dialog.showAndWait();

        if (result.isPresent() && text1.getText().matches("^[A-Za-z]\\w{3,25}$") || text2.getText().matches("^[A-Za-z]\\w{3,25}$")) {

            controller = new SceneController();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/PlayerVsPlayerView2.fxml"));
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(loader.load());
                ((PlayerVsPlayerController2) loader.getController()).setNames(text1.getText(), text2.getText());
                stage.setScene(scene);
                stage.show();

            } catch (IOException ex) {
                Logger.getLogger(MainSceneController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error Dialog");
            alert.setContentText("Wrong name Try Again");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleVsPlayerOnlineBtn(ActionEvent event) {
        String ip = "";
        boolean ex_flag = true;
        //  validation IpVaild = new validation();
        //  boolean flagIp = IpVaild.isStringNumeric(ip);
        //  int countIP = IpVaild.periodCount(ip);
        // int octIp = IpVaild.parseOctetToInt(ip);
        try {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Dialog");
            dialog.setContentText("Please Enter The Server IP Address :" + ip);
            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()) {

            }
            ip = result.get();
        } catch (NoSuchElementException e) {
            ex_flag = false;
        }
        //Temporarily
        boolean flag = ipVaild(ip);
        if (flag) {
            controller = new SceneController();
            try {
                controller.switchToOnlineScene(event);
            } catch (IOException ex) {
                Logger.getLogger(MainSceneController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error Dialog");
            alert.setContentText("Wrong IP Try Again");
            alert.showAndWait();
        }
    }

    public boolean ipVaild(String Ip) {
        if (Ip == null) {
            return false;
        }
        String ip = "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\."
                + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
                + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
                + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$";
        Pattern pattern = Pattern.compile(ip);
        Matcher matcher = pattern.matcher(Ip);
        return matcher.matches();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        controller = new SceneController();
        applicationImageView.setImage(new Image(getClass().getResourceAsStream("/assets/splashImg.png")));
    }

}
