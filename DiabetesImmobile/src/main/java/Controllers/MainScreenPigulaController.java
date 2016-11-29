/**
 * Sample Skeleton for 'MainScreenPigula.fxml' Controller Class
 */

package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;

public class MainScreenPigulaController {

    @FXML // fx:id="imieNazwisko"
    private Label imieNazwisko; // Value injected by FXMLLoader

    @FXML // fx:id="wyswDanePacjenta"
    private Button wyswDanePacjenta; // Value injected by FXMLLoader

    @FXML // fx:id="wylogujButton"
    private Button wylogujButton; // Value injected by FXMLLoader

    @FXML // fx:id="tabPacjenci"
    private TableView<?> tabPacjenci; // Value injected by FXMLLoader

    @FXML // fx:id="tabPacjenciID"
    private TableColumn<?, ?> tabPacjenciID; // Value injected by FXMLLoader

    @FXML // fx:id="tabPacjenciImie"
    private TableColumn<?, ?> tabPacjenciImie; // Value injected by FXMLLoader

    @FXML // fx:id="tabPacjenciNazwisko"
    private TableColumn<?, ?> tabPacjenciNazwisko; // Value injected by FXMLLoader

    @FXML
    void wybierz(ActionEvent event) {

    }

    @FXML
    void wyloguj(ActionEvent event) throws IOException {
        Stage stage = (Stage) wylogujButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Login.fxml"));
        stage.close();
        Stage stageNew = new Stage();
        stageNew.setTitle("Logowanie");
        stageNew.setScene(new Scene(root));
        stageNew.show();
        stage.getScene().getWindow().hide();
    }

}
