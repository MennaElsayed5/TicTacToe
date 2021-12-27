package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

public class FXMLDocumentController implements Initializable {

    @FXML
    private AnchorPane anchorPane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/GameBoardComponent.fxml"));
            anchorPane.getChildren().add(fxmlLoader.load());
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

}
