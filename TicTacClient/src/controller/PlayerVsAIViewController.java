/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author mina
 */
public class PlayerVsAIViewController extends GameBoardComponentController implements Initializable {

    @FXML
    AnchorPane anchorPane;
    Parent root;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            GameBoardComponentController.previousView = "ai";
            root = FXMLLoader.load(getClass().getResource("/view/GameBoardComponent.fxml"));
            anchorPane.getChildren().add(root);
        } catch (IOException ex) {
            Logger.getLogger(PlayerVsAIViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
