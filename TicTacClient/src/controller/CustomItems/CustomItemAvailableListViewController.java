/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.CustomItems;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author mina
 */
public class CustomItemAvailableListViewController {
    @FXML
    AnchorPane itemListAnchorPane; 
    
    @FXML
    TextField itemListTextField;
    
    @FXML
    Button itemListBtn;
    
    String name;
    int i=0;
    
    @FXML
    public void setInfo()
    {
     
        itemListTextField.setText("mina"+i++);
    }
    
}
