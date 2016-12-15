package Controllers;

import Global.Commons;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Tab;

import java.io.IOException;

public class MainScreenPatientController {

    @FXML
    private ProgressBar potassiumBar;

    @FXML
    private ProgressBar sodiumBar;

    @FXML
    private ProgressBar waterBar;

    @FXML
    private Label potassiumPercent;

    @FXML
    private Label sodiumPercent;

    @FXML
    private Label waterPercent;

    @FXML
    private Label potassiumCurrent;

    @FXML
    private Label sodiumCurrent;

    @FXML
    private Label waterCurrent;

    @FXML
    private Label potassiumLimit;

    @FXML
    private Label sodiumLimit;

    @FXML
    private Label waterLimit;

    @FXML
    private LineChart<?, ?> potassiumChart;

    @FXML
    private Tab waterChart;

    @FXML
    private Tab sodiumChart;

    @FXML
    private Label firstAndLastName;

    @FXML
    private Button logoutButton;

    @FXML
    private Label patientFirstAndLastName;

    @FXML
    private Button backToList;

    @FXML
    private Button refreshWaterButton;

    @FXML
    private Button refreshSodiumButton;

    @FXML
    private Button refreshPotassiumButton;

    @FXML
    private Button refreshLimitsButton;

    @FXML
    void initialize() {
        refreshLimits();
        refreshPotassium();
        refreshSodium();
        refreshWater();
    }

    @FXML
    void backToList() {

    }

    @FXML
    void logout() throws IOException {
        Commons.windowControls.logout(logoutButton);
    }

    @FXML
    void refreshLimits() {

    }

    @FXML
    void refreshPotassium() {

    }

    @FXML
    void refreshSodium() {

    }

    @FXML
    void refreshWater() {

    }
}
