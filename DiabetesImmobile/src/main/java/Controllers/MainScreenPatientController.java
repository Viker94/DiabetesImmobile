/**
 * Sample Skeleton for 'MainScreenPatient.fxml' Controller Class
 */

package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Tab;

public class MainScreenPatientController {

    @FXML // fx:id="potasBar"
    private ProgressBar potasBar; // Value injected by FXMLLoader

    @FXML // fx:id="SodBar"
    private ProgressBar SodBar; // Value injected by FXMLLoader

    @FXML // fx:id="WodaBar"
    private ProgressBar WodaBar; // Value injected by FXMLLoader

    @FXML // fx:id="potasProcent"
    private Label potasProcent; // Value injected by FXMLLoader

    @FXML // fx:id="sodProcent"
    private Label sodProcent; // Value injected by FXMLLoader

    @FXML // fx:id="wodaProcent"
    private Label wodaProcent; // Value injected by FXMLLoader

    @FXML // fx:id="potasAktualny"
    private Label potasAktualny; // Value injected by FXMLLoader

    @FXML // fx:id="sodAktualny"
    private Label sodAktualny; // Value injected by FXMLLoader

    @FXML // fx:id="wodaAktualna"
    private Label wodaAktualna; // Value injected by FXMLLoader

    @FXML // fx:id="potasLimit"
    private Label potasLimit; // Value injected by FXMLLoader

    @FXML // fx:id="sodLimit"
    private Label sodLimit; // Value injected by FXMLLoader

    @FXML // fx:id="wodaLimit"
    private Label wodaLimit; // Value injected by FXMLLoader

    @FXML // fx:id="potasChart"
    private LineChart<?, ?> potasChart; // Value injected by FXMLLoader

    @FXML // fx:id="wodaChart"
    private Tab wodaChart; // Value injected by FXMLLoader

    @FXML // fx:id="sodChart"
    private Tab sodChart; // Value injected by FXMLLoader

    @FXML // fx:id="imieNazwisko"
    private Label imieNazwisko; // Value injected by FXMLLoader

    @FXML // fx:id="wyloguj"
    private Button wyloguj; // Value injected by FXMLLoader

    @FXML // fx:id="imieNazwiskoPacjenta"
    private Label imieNazwiskoPacjenta; // Value injected by FXMLLoader

    @FXML // fx:id="wrocDoListy"
    private Button wrocDoListy; // Value injected by FXMLLoader

    @FXML
    void wrocDoListy(ActionEvent event) {

    }

    @FXML
    void wyloguj(ActionEvent event) {

    }

}
