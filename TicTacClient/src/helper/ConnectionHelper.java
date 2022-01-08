package helper;

import controller.SceneNavigationController;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import model.Player;

/**
 *
 * @author Eslam Esmael
 */
public class ConnectionHelper {

    public static int userId;
    public static Player player;
    public static String IP_FROM_CLIENT;

    public static Socket socket = null;
    private static ObjectOutputStream objOutputStream;
    private static ObjectInputStream objInputStream;
    private static SceneNavigationController controller = new SceneNavigationController();

    public static final String SERVER_IP = "10.178.241.76";
    private static final int PORT = 5006;

    public static synchronized Socket getInstanceOf(String ip) {
        if (socket == null || socket.isClosed()) {
            try {
                //TODO change static ip to real ip from the dialog
                socket = new Socket(SERVER_IP, PORT);
                objOutputStream = new ObjectOutputStream(socket.getOutputStream());
                objInputStream = new ObjectInputStream(socket.getInputStream());
            } catch (ConnectException ex) {
                System.out.println("problem in server");
            } catch (SocketException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return socket;
    }

    public static void disconnectFromServer() {
        closeStreams();
        try {
            socket.close();
            socket = null;
            objInputStream = null;
            objInputStream = null;
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

    public static void closeStreams() {
        try {
            objOutputStream.close();
            objInputStream.close();
        } catch (IOException ex) {
            Logger.getLogger(ConnectionHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void closeSocket() {
        try {
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(ConnectionHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void logOut(Stage stage) {
        ButtonType Yes = new ButtonType("yes");
        ButtonType No = new ButtonType("NO", ButtonBar.ButtonData.CANCEL_CLOSE);
        Alert a = new Alert(Alert.AlertType.NONE);
        a.setContentText("Are you sure you want to log out?");
        a.getDialogPane().getButtonTypes().addAll(Yes, No);
        a.setHeaderText("LOGOUT");
        a.showAndWait();
        if (stage == null) {
            System.out.println("stage null");
        }
        if (a.getResult() == Yes) {
            //  disconnectFromServer();
            try {
                controller.switchToMainScene(stage);
            } catch (IOException ex) {
                Logger.getLogger(ConnectionHelper.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void handleServerDisconnation(Stage stage) {
        disconnectFromServer();
        showErrorDialog("Server not found");
        try {
            controller.switchToMainScene(stage);
        } catch (IOException ex) {
            Logger.getLogger(ConnectionHelper.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
