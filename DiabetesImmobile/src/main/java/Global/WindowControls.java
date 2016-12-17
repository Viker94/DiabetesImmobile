package Global;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class WindowControls {

    public void logout(Button logoutButton) throws IOException {
        Stage stage = (Stage) logoutButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Login.fxml"));
        stage.close();
        Stage stageNew = new Stage();
        stageNew.setTitle("Logowanie");
        stageNew.setScene(new Scene(root));
        stageNew.show();
        stage.getScene().getWindow().hide();
    }
    public void newWindow(String fxmlFile, String title) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(fxmlFile));
        Stage stageNew = new Stage();
        stageNew.setTitle(title);
        stageNew.setScene(new Scene(root));
        stageNew.showAndWait();
    }
    public void replaceWindow(String fxmlFile, String title, Button anyButton) throws IOException {
        Stage stage = (Stage) anyButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(fxmlFile));
        stage.close();
        Stage stageNew = new Stage();
        stageNew.setTitle(title);
        stageNew.setScene(new Scene(root));
        stageNew.show();
        stage.getScene().getWindow().hide();
    }
    public void closeWindow(Button anyButton){
        Stage stage = (Stage) anyButton.getScene().getWindow();
        stage.close();
        stage.getScene().getWindow().hide();
    }
}
