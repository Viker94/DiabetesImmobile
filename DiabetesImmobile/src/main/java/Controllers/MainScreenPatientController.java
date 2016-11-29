/**
 * Sample Skeleton for 'MainScreenPatient.fxml' Controller Class
 */

package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Tab;
import javafx.stage.Stage;

import java.io.IOException;

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

    @FXML // fx:id="wylogujButton"
    private Button wylogujButton; // Value injected by FXMLLoader

    @FXML // fx:id="imieNazwiskoPacjenta"
    private Label imieNazwiskoPacjenta; // Value injected by FXMLLoader

    @FXML // fx:id="wrocDoListy"
    private Button wrocDoListy; // Value injected by FXMLLoader

    @FXML
    private Button refreshWodaButton;

    @FXML
    private Button refreshSodButton;

    @FXML
    private Button refreshPotasButton;

    @FXML
    private Button refreshLimityButton;

    @FXML
    void wrocDoListy(ActionEvent event) {

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

    @FXML
    void refreshLimity(ActionEvent event) {

    }

    @FXML
    void refreshPotas(ActionEvent event) {

    }

    @FXML
    void refreshSod(ActionEvent event) {

    }

    @FXML
    void refreshWoda(ActionEvent event) {

    }
}
