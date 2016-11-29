/**
 * Sample Skeleton for 'AdminPanel.fxml' Controller Class
 */

package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class AdminPanelController {

    @FXML // fx:id="imieNazwisko"
    private Label imieNazwisko; // Value injected by FXMLLoader

    @FXML // fx:id="wyloguj"
    private Button wyloguj; // Value injected by FXMLLoader

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

    @FXML // fx:id="usunPacjentaButton"
    private Button usunPacjentaButton; // Value injected by FXMLLoader

    @FXML // fx:id="edytujPacjentaButton"
    private Button edytujPacjentaButton; // Value injected by FXMLLoader

    @FXML // fx:id="pacjentImieTF"
    private TextField pacjentImieTF; // Value injected by FXMLLoader

    @FXML // fx:id="pacjentNazwiskoTF"
    private TextField pacjentNazwiskoTF; // Value injected by FXMLLoader

    @FXML // fx:id="pacjentLoginTF"
    private TextField pacjentLoginTF; // Value injected by FXMLLoader

    @FXML // fx:id="pacjentHasloTF"
    private TextField pacjentHasloTF; // Value injected by FXMLLoader

    @FXML // fx:id="dodajPacjentaButton"
    private Button dodajPacjentaButton; // Value injected by FXMLLoader

    @FXML // fx:id="tabPiguly"
    private TableView<?> tabPiguly; // Value injected by FXMLLoader

    @FXML // fx:id="tabPigulyID"
    private TableColumn<?, ?> tabPigulyID; // Value injected by FXMLLoader

    @FXML // fx:id="tabPigulyImie"
    private TableColumn<?, ?> tabPigulyImie; // Value injected by FXMLLoader

    @FXML // fx:id="tabPigulyNazwisko"
    private TableColumn<?, ?> tabPigulyNazwisko; // Value injected by FXMLLoader

    @FXML // fx:id="tabPigulyLogin"
    private TableColumn<?, ?> tabPigulyLogin; // Value injected by FXMLLoader

    @FXML // fx:id="tabPigulyLiczbaPacjentow"
    private TableColumn<?, ?> tabPigulyLiczbaPacjentow; // Value injected by FXMLLoader

    @FXML // fx:id="usunPiguleButton"
    private Button usunPiguleButton; // Value injected by FXMLLoader

    @FXML // fx:id="edytujPiguleButton"
    private Button edytujPiguleButton; // Value injected by FXMLLoader

    @FXML // fx:id="przypiszPacjentaButton"
    private Button przypiszPacjentaButton; // Value injected by FXMLLoader

    @FXML // fx:id="pigulaImieTF"
    private TextField pigulaImieTF; // Value injected by FXMLLoader

    @FXML // fx:id="pigulaNazwiskoTF"
    private TextField pigulaNazwiskoTF; // Value injected by FXMLLoader

    @FXML // fx:id="pigulaLoginTF"
    private TextField pigulaLoginTF; // Value injected by FXMLLoader

    @FXML // fx:id="pigulaHasloTF"
    private TextField pigulaHasloTF; // Value injected by FXMLLoader

    @FXML // fx:id="dodajPiguleButton"
    private Button dodajPiguleButton; // Value injected by FXMLLoader

    @FXML
    void dodajPacjenta(ActionEvent event) {

    }

    @FXML
    void dodajPigule(ActionEvent event) {

    }

    @FXML
    void edytujPacjenta(ActionEvent event) {

    }

    @FXML
    void edytujPigule(ActionEvent event) {

    }

    @FXML
    void przypiszPacjenta(ActionEvent event) {

    }

    @FXML
    void usunPacjenta(ActionEvent event) {

    }

    @FXML
    void usunPigule(ActionEvent event) {

    }

}
