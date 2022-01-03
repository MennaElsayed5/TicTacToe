package controller;

import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import model.PlayerMove;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 *
 * @author yasmeen
 */
public class RecordedGameController {

    int i = 0;
    final String DIRNAME = "RecordedGames";
    String fileName;
    File file;
    ArrayList<PlayerMove> arr;
    SceneNavigationController controller;

    public RecordedGameController(String fileName) {
        i = 0;
        this.fileName = fileName;

    }
    
    public RecordedGameController() {
        i = 0;
    }

    @FXML
    private Label label00;

    @FXML
    private Label label01;

    @FXML
    private Label label02;

    @FXML
    private Label label10;

    @FXML
    private Label label11;

    @FXML
    private Label label12;

    @FXML
    private Label label20;

    @FXML
    private Label label21;

    @FXML
    private Label label22;

    @FXML
    private void handleButtonStartRecord(ActionEvent event) {

        arr = game("game17-25-57.txt");
        try {
            final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
            executorService.scheduleAtFixedRate(new Runnable() {
                @Override
                public void run() {
                    if (arr.size() != 0 && i < arr.size()) {
                        desplayMove(arr.get(i));
                        i++;
                    } else {
                        executorService.shutdown();
                    }
                }
            }, 0, 1, TimeUnit.SECONDS);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        /*for (int x = 0; x < arr.size(); x++) {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    desplayMove(arr.get(i));
                    i++;
                    System.out.println(i);
                }
            });
        }*/
 /*        for (int x = 0; x < arr.size(); x++) {

            delay(1000, new Runnable() {
                @Override
                public void run() {
                    desplayMove(arr.get(i));
                    i++;
                }
            });
        }
         */
    }

    @FXML
    private void buttonBackPressed(ActionEvent event) {

        controller = new SceneNavigationController();
        try {
            controller.switchToMainScene(event);
        } catch (IOException ex) {
            Logger.getLogger(RecordedGameController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void desplayMove(PlayerMove move) {

        switch (move.getX()) {
            case 0:
                switch (move.getY()) {
                    case 0:
                        writeOnLabel(label00, move.isIsX());
                        break;
                    case 1:
                        writeOnLabel(label01, move.isIsX());
                        break;
                    case 2:
                        writeOnLabel(label02, move.isIsX());
                        break;
                }
                break;
            case 1:
                switch (move.getY()) {
                    case 0:
                        writeOnLabel(label10, move.isIsX());
                        break;
                    case 1:
                        writeOnLabel(label11, move.isIsX());
                        break;
                    case 2:
                        writeOnLabel(label12, move.isIsX());
                        break;
                }
                break;
            case 2:
                switch (move.getY()) {
                    case 0:
                        writeOnLabel(label20, move.isIsX());
                        break;
                    case 1:
                        writeOnLabel(label21, move.isIsX());
                        break;
                    case 2:
                        writeOnLabel(label22, move.isIsX());
                        break;
                }
                break;
        }
    }

    public void writeOnLabel(Label label, boolean isX) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                if (isX) {
                    label.setText("X");
                } else {
                    label.setText("O");
                }
                label.setStyle("-fx-background-color: yellow;");
            }
        });

    }

    public ArrayList<PlayerMove> game() {
        ArrayList<PlayerMove> moves = new ArrayList<>();
        moves.add(new PlayerMove(1, 1, true));
        moves.add(new PlayerMove(0, 0, true));
        moves.add(new PlayerMove(1, 0, true));
        moves.add(new PlayerMove(2, 0, true));
        moves.add(new PlayerMove(2, 1, true));
        moves.add(new PlayerMove(1, 2, true));
        moves.add(new PlayerMove(0, 2, true));
        moves.add(new PlayerMove(0, 1, true));
        return moves;
    }

    public ArrayList<PlayerMove> game(String fileName) {
        FileInputStream fis = null;
        try {
            String classeeeasd = getClass().toString();
            
            //TODO ya yasmin relative path 
          //  file = new File("C:\\Users\\Elhadidy labtop\\Desktop\\TicTacTaoClient\\TicTacToe\\TicTacClient\\game194734.txt");
            file = new File(DIRNAME + "\\" + fileName);
          //file = new File(getClass().getResource("game194734.txt").toString());
            fis = new FileInputStream(file);
            
            JsonReader reader = new JsonReader(new InputStreamReader(fis, "UTF-8"));
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<PlayerMove>>() {
            }.getType();
            ArrayList<PlayerMove> moves = gson.fromJson(reader, listType);
            reader.close();
            return moves;
        } catch (FileNotFoundException | UnsupportedEncodingException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static void delay(long delayMs, Runnable toRun) {
        Thread t = new Thread(() -> {
            try {
                Thread.sleep(delayMs);
            } catch (InterruptedException ignored) {
            }
            Platform.runLater(toRun);
        });
        t.setDaemon(true);
        t.start();
    }

}