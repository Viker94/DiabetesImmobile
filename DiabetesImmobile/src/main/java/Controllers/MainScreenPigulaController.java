/**
 * Sample Skeleton for 'MainScreenPigula.fxml' Controller Class
 */

package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

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
    void wyloguj(ActionEvent event) {

    }

}
