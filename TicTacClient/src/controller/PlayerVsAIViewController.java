package controller;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import model.PlayerMove;


/**
 *
 * @author Eslam Esmael
 */
public class PlayerVsAIViewController extends GameBoardComponentController implements Initializable {
        @FXML
    private AnchorPane anchorPane;
        
         FXMLLoader fxmlLoader;
         
         Button btn;
         ActionEvent event;
         PlayerMove[] playersMoves;
         int counter=0;
    
    public PlayerVsAIViewController() { }
    public PlayerVsAIViewController(ActionEvent event) {
        this.event = event;
        btn=(Button) event.getSource();
       
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fxmlLoader = new FXMLLoader(getClass().getResource("/view/GameBoardComponent.fxml"));
        playersMoves = new PlayerMove[9];
        try {
          anchorPane.getChildren().add(fxmlLoader.load());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
       
    }

   
        private void buttonOnePressedInAiView(ActionEvent event) {
        ((Button) event.getSource()).setDisable(true);
        gameSession.addMove(returnMove((Button) event.getSource()));
        playersMoves[counter++] = returnMove((Button) event.getSource());
        System.out.println("counter after "+counter);        
        ((Button) event.getSource()).setText(symbol);
        checkState();
        PlayerMove newAiMove =aiMove();
        gameSession.addMove(newAiMove);
        setAIText(newAiMove.getX() , newAiMove.getY());
        playersMoves[counter++]=newAiMove;
        checkState();
    }
        
    public PlayerMove aiMove() {
        Random r = new Random();
        int x = r.nextInt(3);
        int y = r.nextInt(3);
        if(counter<9)
        while(checkMoveFound(x,y))
        {
            x = r.nextInt(3);
            y = r.nextInt(3);
        }
            return new PlayerMove(x, y, false);
    }
        public boolean checkMoveFound(int x, int y) {
        for (int j = 0; j < counter; j++) {
            if (x == playersMoves[j].getX() && y == playersMoves[j].getY()) {
                return true;
            }
        }
        return false;
    }
         public void setAIText(int x , int y) {
        if (x == 0 && y == 0) {
            btn00.setText("O");
            btn00.setDisable(true);
        }

        if (x == 0 && y == 1) {
            btn01.setText("O");
            btn01.setDisable(true);
        }
        if (x == 0 && y == 2) {
            btn02.setText("O");
            btn02.setDisable(true);
        }
        if (x == 1 && y == 0) {
            btn10.setText("O");
            btn10.setDisable(true);
        }
        if (x == 1 && y == 1) {
            btn11.setText("O");
            btn11.setDisable(true);
        }
        if (x == 1 && y == 2) {
            btn12.setText("O");
            btn12.setDisable(true);
        }
        if (x == 2 && y == 0) {
            btn20.setText("O");
            btn20.setDisable(true);
        }
        if (x == 2 && y == 1) {
            btn21.setText("O");
            btn21.setDisable(true);
        }
        if (x == 2 && y == 2) {
            btn22.setText("O");
            btn22.setDisable(true);
        }
    }

   

   

}
