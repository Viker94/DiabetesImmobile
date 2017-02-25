package Controllers;

import Global.Commons;
import Model.Consumption;
import Model.ConsumptionHistory;
import Model.User;
import Model.UsersForTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.swing.*;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
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
    void initialize() throws IOException {
        Platform.runLater(new Runnable() {
            @Override public void run() {
                try {
                    Commons.conn.refreshSingleUser(Commons.getSelectedUser());
                    Commons.conn.refreshSingleNurse(Commons.getSelectedNurse());
                    user = Commons.getSelectedUser();
                    refreshLimits();
                    refreshHistory();
                    firstAndLastName.setText(Commons.getImie()+" "+Commons.getNazwisko());
                    patientFirstAndLastName.setText(Commons.getSelectedUser().getFirstName()+" "+Commons.getSelectedUser().getLastName());
                    if(user.getNextVisit()!=null) plannedDateLabel.setText(user.getNextVisit().toString());
                    else plannedDateLabel.setText("Nie ustalono");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
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
        Commons.conn.refreshSingleUser(Commons.getSelectedUser());
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
    void saveToPDF(){
        //generowanie raportu jako kodu HTML
        String html = "<html><h1 align=\"center\">Historia pacjenta</h1>";
        html += "<h2 align=\"center\">"+user.getFirstName()+" "+user.getLastName()+"</h2>";
        html += "<h3 align=\"center\">Wygenerowano: "+ LocalDate.now().toString() +"</h3>";
        html += "<h4 align=\"center\">Przez: "+ Commons.getImie()+" "+Commons.getNazwisko() +"</h4>";
        html += "<table style=\"width:100%\" border=\"1\" cellpadding=\"3\"><tr><th>Data</th><th>Produkt</th><th>Ilosc</th><th>Z. potasu</th><th>Z. sodu</th><th>Z. wody</th></tr>";

        List<Consumption> cons = Commons.getSelectedUser().getConsumed();
        Consumption temp;
        for(int i=0;i<cons.size();i++){
            temp = cons.get(i);
            html += "<tr>";
            html += "<td>"+temp.getDate().toString()+"</td>";
            html += "<td>"+temp.getProduct().getName()+"</td>";
            html += "<td>"+temp.getAmount()+"</td>";
            html += "<td>"+String.format("%.4g%n",temp.getProduct().getPotassium()*temp.getAmount())+" mg</td>";
            html += "<td>"+String.format("%.4g%n",temp.getProduct().getSodium()*temp.getAmount())+" mg</td>";
            html += "<td>"+String.format("%.4g%n",temp.getProduct().getWater()*temp.getAmount())+" mg</td>";
            html += "</tr>";
        }
        html += "</table></html>";

        //Zapis kodu HTML jako PDF

        com.itextpdf.text.Document doc = new com.itextpdf.text.Document();
        try {
            PdfWriter wr = PdfWriter.getInstance(doc, new FileOutputStream("Historia-"+user.getId()+"-"+LocalDate.now().toString()+".pdf"));
            doc.open();
            ByteArrayInputStream is = new ByteArrayInputStream(html.getBytes());
            XMLWorkerHelper.getInstance().parseXHtml(wr, doc, is);
            doc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        JOptionPane.showMessageDialog(null, "Zapisano");

    }

}
