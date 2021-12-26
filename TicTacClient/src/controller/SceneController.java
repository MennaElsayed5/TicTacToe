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
public class SceneController {
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    public void switchToOnlineScene(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("/view/OnlineSceneView.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    public void switchToPlayerVsPlayerScene(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("/view/PlayerVsPlayerView.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    public void switchToMainScene(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("/view/MainScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    private void switchToScene(ActionEvent event) throws IOException{
        
        
    }
    
    
    
    
}
