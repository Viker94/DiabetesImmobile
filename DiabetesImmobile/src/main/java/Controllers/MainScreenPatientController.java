package Controllers;

import Global.Commons;
import Model.Consumption;
import Model.ConsumptionHistory;
import Model.User;
import Model.UsersForTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    private Button historyRefreshButton;

    @FXML
    private Button acceptDateButton;

    @FXML
    private DatePicker newDatePicker;

    @FXML
    private Label plannedDateLabel;

    @FXML
    private TableView<ConsumptionHistory> tabHistory;

    @FXML
    private TableColumn<ConsumptionHistory, String> tabHistoryDate;

    @FXML
    private TableColumn<ConsumptionHistory, String> tabHistoryProduct;

    @FXML
    private TableColumn<ConsumptionHistory, Integer> tabHistoryQuantity;

    @FXML
    private TableColumn<ConsumptionHistory, String> tabHistoryPotassium;

    @FXML
    private TableColumn<ConsumptionHistory, String> tabHistorySodium;

    @FXML
    private TableColumn<ConsumptionHistory, String> tabHistoryWater;

    @FXML
    void initialize() {
        user = Commons.getSelectedUser();
        refreshLimits();
        refreshPotassium();
        refreshSodium();
        refreshWater();
        refreshHistory();
        firstAndLastName.setText(Commons.getImie()+" "+Commons.getNazwisko());
        patientFirstAndLastName.setText(Commons.getSelectedUser().getFirstName()+" "+Commons.getSelectedUser().getLastName());
        if(user.getNextVisit()!=null) plannedDateLabel.setText(user.getNextVisit().toString());
        else plannedDateLabel.setText("Nie ustalono");
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
    void acceptNewDate() throws IOException {
        Date temp = java.sql.Date.valueOf(newDatePicker.getValue()); //błąd bo retarded środowisko, ale kompiluje sie i działa
        Commons.conn.newVisit(user.getId(), temp);
        User tmp = Commons.conn.refreshSingleUser(user.getId());
        Commons.getSelectedUser().setNextVisit(tmp.getNextVisit());
        user = Commons.getSelectedUser();
        if(user.getNextVisit()!=null)plannedDateLabel.setText(user.getNextVisit().toString());
        else plannedDateLabel.setText("Nie ustalono");
    }

    @FXML
    void refreshHistory(){
        List<ConsumptionHistory> historia = new ArrayList<ConsumptionHistory>();
        List<Consumption> cons = Commons.getSelectedUser().getConsumed();
        Consumption temp;
        ConsumptionHistory tmpH;
        for(int i=0;i<cons.size();i++){
            temp = cons.get(i);
            tmpH = new ConsumptionHistory();
            tmpH.setData(temp.getDate().toString());
            tmpH.setProdukt(temp.getProduct().getName());
            tmpH.setIlosc(temp.getAmount());
            tmpH.setDeltaPotasu(temp.getProduct().getPotassium()*temp.getAmount()+" mg");
            tmpH.setDeltaSodu(temp.getProduct().getSodium()*temp.getAmount()+" mg");
            tmpH.setDeltaWody(temp.getProduct().getWater()*temp.getAmount()+" mg");
            historia.add(tmpH);
        }
        ObservableList<ConsumptionHistory> consHist = FXCollections.observableArrayList(historia);
        tabHistoryDate.setCellValueFactory(new PropertyValueFactory<ConsumptionHistory, String>("data"));
        tabHistoryProduct.setCellValueFactory(new PropertyValueFactory<ConsumptionHistory, String>("produkt"));
        tabHistoryQuantity.setCellValueFactory(new PropertyValueFactory<ConsumptionHistory, Integer>("ilosc"));
        tabHistoryPotassium.setCellValueFactory(new PropertyValueFactory<ConsumptionHistory, String>("deltaPotasu"));
        tabHistorySodium.setCellValueFactory(new PropertyValueFactory<ConsumptionHistory, String>("deltaSodu"));
        tabHistoryWater.setCellValueFactory(new PropertyValueFactory<ConsumptionHistory, String>("deltaWody"));
        tabHistory.setItems(consHist);

    }

    @FXML
    void refreshLimits() {
        potassiumCurrent.setText(user.getPotassium()+" mg");
        waterCurrent.setText(user.getWater()+" mg");
        sodiumCurrent.setText(user.getSodium()+" mg");
        potassiumLimit.setText(user.getLimitPotassium()+" mg");
        waterLimit.setText(user.getLimitWater()+" mg");
        sodiumLimit.setText(user.getLimitSodium()+" mg");
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
    void refreshPotassium() {

    }

    @FXML
    void refreshSodium() {

    }

    @FXML
    void refreshWater() {

    }
}
