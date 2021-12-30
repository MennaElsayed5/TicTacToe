/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.CustomItems.CustomItemAvailableListViewController;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;


/**
 *
 * @author mina
 */
public class OnlineMainSceneController implements Initializable{
    @FXML
    ListView availablePlayersList;
    
    @FXML
    ListView playersInGameList;
    
    FXMLLoader fxmlLoader;
    int i=0;
    ArrayList<FXMLLoader> availablePlayers,playersInGame;
    CustomItemAvailableListViewController item;
   
    public void showAvailablePlayer()
    { 
        ObservableList observableList = FXCollections.observableArrayList();
        try {
            fxmlLoader = new FXMLLoader(getClass().getResource("/view/CustomItemAvailableListView.fxml"));
            item= fxmlLoader.getController();
           availablePlayers.add(fxmlLoader.load());
            observableList.addAll(availablePlayers);
            availablePlayersList.setItems( observableList);
        } catch (IOException ex) {
            Logger.getLogger(OnlineMainSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }
        showPlayersInGame();
    }
    public void showPlayersInGame()
    { 
        ObservableList observableList = FXCollections.observableArrayList();
        try {
            fxmlLoader = new FXMLLoader(getClass().getResource("/view/CustomItemPlayersInGameListView.fxml"));
            item= fxmlLoader.getController();
           playersInGame.add(fxmlLoader.load());
            observableList.addAll(playersInGame);
            playersInGameList.setItems( observableList);
        } catch (IOException ex) {
            Logger.getLogger(OnlineMainSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
         availablePlayers=new ArrayList<>();
         playersInGame=new ArrayList<>();
         item=new CustomItemAvailableListViewController();
    }
    
}