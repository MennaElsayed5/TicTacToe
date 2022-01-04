/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;

/**
 *
 * @author Eslam Esmael
 */
public class ConnectionHelper {

    private static Socket socket = null;
    private static ObjectOutputStream objOutputStream;
    private static ObjectInputStream objInputStream;
    private static final String MY_IP = "10.178.241.76";

    public static void connectToServer() {
        if (socket == null) {
            try {
                socket = new Socket(MY_IP, 5005);
                objOutputStream = new ObjectOutputStream(socket.getOutputStream());
                objInputStream = new ObjectInputStream(socket.getInputStream());
            } catch (IOException ex) {
                showErrorDialog("Cannot connect to the server!\nPlease check your connection.");
            }
        }

    }

    public static void disconnectFromServer() {
        try {
            objOutputStream.close();
            objInputStream.close();
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(ConnectionHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static boolean isConnected() {
        return socket.isConnected();
    }

    public static ObjectInputStream getObjectInputStream() {
        return objInputStream;
    }

    public static ObjectOutputStream getObjectOutputStream() {
        return objOutputStream;
    }

    public static void showErrorDialog(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Test Connection");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
