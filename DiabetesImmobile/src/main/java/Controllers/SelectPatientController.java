/**
 * Sample Skeleton for 'SelectPatient.fxml' Controller Class
 */

package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class SelectPatientController {

    @FXML // fx:id="tabPacjenci"
    private TableView<?> tabPacjenci; // Value injected by FXMLLoader

    @FXML // fx:id="tabPacjenciID"
    private TableColumn<?, ?> tabPacjenciID; // Value injected by FXMLLoader

    @FXML // fx:id="tabPacjenciImie"
    private TableColumn<?, ?> tabPacjenciImie; // Value injected by FXMLLoader

    @FXML // fx:id="tabPacjenciNazwisko"
    private TableColumn<?, ?> tabPacjenciNazwisko; // Value injected by FXMLLoader

    @FXML // fx:id="tabPacjenciLogin"
    private TableColumn<?, ?> tabPacjenciLogin; // Value injected by FXMLLoader

    @FXML // fx:id="tabPacjenciLiczbaPielegniarek"
    private TableColumn<?, ?> tabPacjenciLiczbaPielegniarek; // Value injected by FXMLLoader

    @FXML // fx:id="wybierzButton"
    private Button wybierzButton; // Value injected by FXMLLoader

    @FXML // fx:id="anulujButton"
    private Button anulujButton; // Value injected by FXMLLoader

    @FXML
    void anuluj(ActionEvent event) {

    }

    @FXML
    void wybierz(ActionEvent event) {

    }

}
