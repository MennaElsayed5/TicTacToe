package controller;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Eslam Esmael
 */
public class SceneNavigationController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToPlayerVsPlayerScene(ActionEvent event) throws IOException {
        switchToScene(event, "/view/PlayerVsPlayerView.fxml");
    }

    public void switchToPlayerVsAIScene(ActionEvent event) throws IOException {
        switchToScene(event, "/view/PlayerVsAIView.fxml");
    }

    public void switchToMainScene(ActionEvent event) throws IOException {
        switchToScene(event, "/view/MainScene.fxml");
    }

    public void switchToOnlineMainScene(ActionEvent event) throws IOException {
        switchToScene(event, "/view/OnlineMainView.fxml");
    }

    public void switchToOnlineScene(ActionEvent event) throws IOException {
        switchToScene(event, "/view/LoginRegisterView.fxml");
    }

    public void switchToLoginScene(ActionEvent event) throws IOException {
        //  switchToScene(event,"/view/FirstScreenAfterEnteringIP.fxml");
    }

    public void switchToRegisterScene(ActionEvent event) throws IOException {
        switchToScene(event, "/view/RegisterScreenView.fxml");
    }
    
    public void switchToRecordedGame(ActionEvent event) throws IOException {
        switchToScene(event, "/view/RecordedGameView.fxml");
    }

    private void switchToScene(ActionEvent event, String path) throws IOException {
        root = FXMLLoader.load(getClass().getResource(path));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
