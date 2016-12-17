package Controllers;

import Global.Commons;
import Model.UsersForTable;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Tab;

import java.io.IOException;

public class MainScreenPatientController {

    private UsersForTable user;

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
    private LineChart<?, ?> waterChart;

    @FXML
    private LineChart<?, ?> sodiumChart;

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
        user = Commons.getSelectedUser();
        refreshLimits();
        refreshPotassium();
        refreshSodium();
        refreshWater();
        firstAndLastName.setText(Commons.getImie()+" "+Commons.getNazwisko());
        patientFirstAndLastName.setText(Commons.getSelectedUser().getFirstName()+" "+Commons.getSelectedUser().getLastName());
    }

    @FXML
    void backToList() {
        Commons.windowControls.closeWindow(backToList);
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
        potassiumCurrent.setText(user.getPotassium()+"mg");
        waterCurrent.setText(user.getWater()+"mg");
        sodiumCurrent.setText(user.getSodium()+"mg");
        potassiumLimit.setText(user.getLimitPotassium()+"mg");
        waterLimit.setText(user.getLimitWater()+"mg");
        sodiumLimit.setText(user.getLimitSodium()+"mg");
        double potassiumPer = user.getPotassium() / user.getLimitPotassium();
        double waterPer = user.getWater() / user.getLimitWater();
        double sodiumPer = user.getSodium() / user.getLimitSodium();
        potassiumPercent.setText(potassiumPer*100+"%");
        waterPercent.setText(waterPer*100+"%");
        sodiumPercent.setText(sodiumPer*100+"%");
        potassiumBar.setProgress(potassiumPer);
        waterBar.setProgress(waterPer);
        sodiumBar.setProgress(sodiumPer);
    }

    @FXML
    void refreshSodium() {

    }

    @FXML
    void refreshWater() {

    }
}
