/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;


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
public class SplashScreen extends Thread{
    Stage stage;
    ImageView iv;
    VBox box;
    
   public SplashScreen()
    {
     stage=new Stage();
     box=new VBox();
     iv=new ImageView();
    }
    
    @Override
       public void run()
       {
         try {
                Thread.sleep(5000);
                Platform.runLater(() -> {
                    stage.close();          
                });
            } catch (InterruptedException ex) {
                Logger.getLogger(SplashScreen.class.getName()).log(Level.SEVERE, null, ex);
            }
       }   
    
    
    public void display()
    {   
        start();
         Image i;
                 i = new Image(getClass().getResourceAsStream("/view/splashImg.png"));
             //stage.initModality(Modality.APPLICATION_MODAL);
             //remove exit and maximize and minimize
             stage.initStyle(StageStyle.UNDECORATED);
             stage.setMinWidth(500);
             stage.setMinHeight(500);
             stage.setMaxWidth(500);
             stage.setMaxHeight(500);
             
             
             iv.setFitWidth(500);
             iv.setFitHeight(500);
             iv.setImage(i);
             
             box.getChildren().add(iv);
             box.setAlignment(Pos.CENTER);
             Scene scene=new Scene(box);
             stage.setScene(scene);
             stage.showAndWait();
  
    }
    
}
