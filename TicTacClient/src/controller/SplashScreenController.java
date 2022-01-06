/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


/**
 *
 * @author mina
 */
public class SplashScreenController extends Thread{
    Stage stage;
    ImageView iv;
    VBox box;
    
   public SplashScreenController()
    {
     stage=new Stage();
     box=new VBox();
     iv=new ImageView();
    }
    
    @Override
       public void run()
       {
         try {
                Thread.sleep(2500);
                Platform.runLater(() -> {
                    stage.close();          
                });
            } catch (InterruptedException ex) {
                Logger.getLogger(SplashScreenController.class.getName()).log(Level.SEVERE, null, ex);
            }
       }   
    
    
    public void display()
    {   
        start();
         Image i;
                 i = new Image(getClass().getResourceAsStream("/assets/splash.png"));
             //stage.initModality(Modality.APPLICATION_MODAL);
             //remove exit and maximize and minimize
             stage.initStyle(StageStyle.UNDECORATED);
             stage.setMinWidth(500);
             stage.setMinHeight(500);
             stage.setMaxWidth(800);
             stage.setMaxHeight(432);
             
             
             iv.setFitWidth(800);
             iv.setFitHeight(432);
             iv.setImage(i);
             
             box.getChildren().add(iv);
             box.setAlignment(Pos.CENTER);
             Scene scene=new Scene(box);
             stage.setScene(scene);
             stage.showAndWait();
  
    }
    
}
