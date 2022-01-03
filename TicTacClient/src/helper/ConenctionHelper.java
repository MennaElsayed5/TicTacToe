/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javafx.scene.control.Alert;

/**
 *
 * @author Eslam Esmael
 */
public class ConenctionHelper {

    private static Socket socket = null;
    private static ObjectOutputStream objOutputStream;
    private static ObjectInputStream objInputStream;
    private static final String MY_IP = "10.178.241.76";

    private static void connectToServer() {
        if (socket == null) {
            try {
                socket = new Socket(MY_IP, 5005);
                objOutputStream = new ObjectOutputStream(socket.getOutputStream());
                objInputStream = new ObjectInputStream(socket.getInputStream());
            } catch (IOException ex) {
                //System.out.println("Connot connect socket to the server");
                showErrorDialog("Cannot connect to the server!\nPlease check your connection.");
            }
        }

    }

    private static boolean isConnected() {
        return socket.isConnected();
    }

    private static ObjectInputStream getObjectInputStream() {
        return objInputStream;
    }

    private static ObjectOutputStream getObjectOutputStream() {
        return objOutputStream;
    }

    private static void showErrorDialog(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Test Connection");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
