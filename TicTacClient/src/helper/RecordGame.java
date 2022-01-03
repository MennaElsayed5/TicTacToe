package helper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import model.PlayerMove;

/**
 *
 * @author yasmeen
 */
public class RecordGame {
    GsonBuilder builder;
    Gson gson;
    final String DIRNAME = "RecordedGames" ;
    
    public void recordGame(ArrayList<PlayerMove> gameMoves){
        String time = Time.valueOf(LocalTime.now()).toString().replace(":", "-");
        builder = new GsonBuilder();
        gson = builder.create();
        String fileName = "game" + time + ".txt";
        try {
            FileOutputStream writer = new FileOutputStream(DIRNAME + File.separator + fileName);
            JsonArray Array = new Gson().toJsonTree(gameMoves).getAsJsonArray();
            writer.write(Array.toString().getBytes());
            writer.close();   
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
