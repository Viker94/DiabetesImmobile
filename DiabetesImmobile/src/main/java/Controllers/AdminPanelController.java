/**
 * Sample Skeleton for 'AdminPanel.fxml' Controller Class
 */

package Controllers;

import Global.Commons;
import Model.NursesForTable;
import Model.UsersForTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class AdminPanelController {

    @FXML // fx:id="imieNazwisko"
    private Label imieNazwisko; // Value injected by FXMLLoader

    @FXML // fx:id="wylogujButton"
    private Button wylogujButton; // Value injected by FXMLLoader

    @FXML // fx:id="tabPacjenci"
    private TableView<UsersForTable> tabPacjenci; // Value injected by FXMLLoader

    @FXML // fx:id="tabPacjenciID"
    private TableColumn<UsersForTable, Long> tabPacjenciID; // Value injected by FXMLLoader

    @FXML // fx:id="tabPacjenciImie"
    private TableColumn<UsersForTable, String> tabPacjenciImie; // Value injected by FXMLLoader

    @FXML // fx:id="tabPacjenciNazwisko"
    private TableColumn<UsersForTable, String> tabPacjenciNazwisko; // Value injected by FXMLLoader

    @FXML // fx:id="tabPacjenciLogin"
    private TableColumn<UsersForTable, String> tabPacjenciLogin; // Value injected by FXMLLoader

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
    private TableView<NursesForTable> tabPiguly; // Value injected by FXMLLoader

    @FXML // fx:id="tabPigulyID"
    private TableColumn<NursesForTable, Long> tabPigulyID; // Value injected by FXMLLoader

    @FXML // fx:id="tabPigulyImie"
    private TableColumn<NursesForTable, String> tabPigulyImie; // Value injected by FXMLLoader

    @FXML // fx:id="tabPigulyNazwisko"
    private TableColumn<NursesForTable, String> tabPigulyNazwisko; // Value injected by FXMLLoader

    @FXML // fx:id="tabPigulyLogin"
    private TableColumn<NursesForTable, String> tabPigulyLogin; // Value injected by FXMLLoader

    @FXML // fx:id="tabPigulyLiczbaPacjentow"
    private TableColumn<NursesForTable, Integer> tabPigulyLiczbaPacjentow; // Value injected by FXMLLoader

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
    private Button refreshPacjenciButton;

    @FXML
    private Button refreshPigulyButton;

    @FXML
    private Button usunPacjOdPigulyButton;

    @FXML
    public void initialize() {
        imieNazwisko.setText(Commons.getImie()+" "+ Commons.getNazwisko());
    }

    @FXML
    void logout(ActionEvent event) throws IOException {
        Stage stage = (Stage) imieNazwisko.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Login.fxml"));
        stage.close();
        Stage stageNew = new Stage();
        stageNew.setTitle("Logowanie");
        stageNew.setScene(new Scene(root));
        stageNew.show();
        stage.getScene().getWindow().hide();
    }

    @FXML
    void addPatient(ActionEvent event) throws IOException {
        String imie = pacjentImieTF.getText();
        String nazwisko = pacjentNazwiskoTF.getText();
        String login = pacjentLoginTF.getText();
        String password = pacjentHasloTF.getText();

        Commons.conn.addUser(imie,nazwisko,login,password);
        pacjentImieTF.setText("");
        pacjentNazwiskoTF.setText("");
        pacjentHasloTF.setText("");
        pacjentLoginTF.setText("");
    }

    @FXML
    void addNurse(ActionEvent event) throws IOException {
        String imie = pigulaImieTF.getText();
        String nazwisko = pigulaNazwiskoTF.getText();
        String login = pigulaLoginTF.getText();
        String password = pigulaHasloTF.getText();

        Commons.conn.addNurse(imie,nazwisko,login,password);

        pigulaImieTF.setText("");
        pigulaNazwiskoTF.setText("");
        pigulaLoginTF.setText("");
        pigulaHasloTF.setText("");
    }

    @FXML
    void editPatient(ActionEvent event) throws IOException {
        //Stage stage = (Stage) imieNazwisko.getScene().getWindow();
        if(tabPacjenci.getSelectionModel().getSelectedItem()!=null) {
            Commons.setUserOrNurse(1);
            Commons.setSelectedUser(tabPacjenci.getSelectionModel().getSelectedItem());
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("UserEdit.fxml"));
            //stage.close();
            Stage stageNew = new Stage();
            stageNew.setTitle("Edycja pacjenta");
            stageNew.setScene(new Scene(root));
            stageNew.show();
            // stage.getScene().getWindow().hide();
        }
    }

    @FXML
    void editNurse(ActionEvent event) throws IOException {
        if(tabPiguly.getSelectionModel().getSelectedItem()!=null) {
            //Stage stage = (Stage) imieNazwisko.getScene().getWindow();
            Commons.setUserOrNurse(2);
            Commons.setSelectedNurse(tabPiguly.getSelectionModel().getSelectedItem());
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("UserEdit.fxml"));
            //stage.close();

            Stage stageNew = new Stage();
            stageNew.setTitle("Edycja pielegniarki");
            stageNew.setScene(new Scene(root));
            stageNew.show();
            // stage.getScene().getWindow().hide();
        }
    }

    @FXML
    void assignPatient(ActionEvent event) {

    }

    @FXML
    void deletePatient(ActionEvent event) throws IOException {
        if(tabPacjenci.getSelectionModel().getSelectedItem()!=null) {
            long id = tabPacjenci.getSelectionModel().getSelectedItem().getId();
            Commons.conn.deleteUser(id);
        }
    }

    @FXML
    void deleteNurse(ActionEvent event) throws IOException {
        if(tabPiguly.getSelectionModel().getSelectedItem()!=null) {
            long id = tabPiguly.getSelectionModel().getSelectedItem().getId();
            Commons.conn.nurseDel(id);
        }
    }

    @FXML
    void refreshPatientsTable(ActionEvent event) throws IOException {
        List<UsersForTable> users = Commons.conn.getPatients();
        ObservableList<UsersForTable> ousers = FXCollections.observableArrayList(users);
        tabPacjenciID.setCellValueFactory(new PropertyValueFactory<UsersForTable, Long>("id"));
        tabPacjenciImie.setCellValueFactory(new PropertyValueFactory<UsersForTable, String>("firstName"));
        tabPacjenciNazwisko.setCellValueFactory(new PropertyValueFactory<UsersForTable, String>("lastName"));
        tabPacjenciLogin.setCellValueFactory(new PropertyValueFactory<UsersForTable, String>("login"));
        tabPacjenci.setItems(ousers);
    }

    @FXML
    void refreshNursesTable(ActionEvent event) throws IOException {
        List<NursesForTable> piguly = Commons.conn.getNurses();

        ObservableList<NursesForTable> opiele = FXCollections.observableArrayList(piguly);
        tabPigulyID.setCellValueFactory(new PropertyValueFactory<NursesForTable, Long>("id"));
        tabPigulyImie.setCellValueFactory(new PropertyValueFactory<NursesForTable, String>("firstName"));
        tabPigulyNazwisko.setCellValueFactory(new PropertyValueFactory<NursesForTable, String>("lastName"));
        tabPigulyLogin.setCellValueFactory(new PropertyValueFactory<NursesForTable, String>("login"));
        tabPigulyLiczbaPacjentow.setCellValueFactory(new PropertyValueFactory<NursesForTable, Integer>("liczbaPacjentow"));
        tabPiguly.setItems(opiele);
    }

    @FXML
    void resetNurseConnections(ActionEvent event) throws IOException {
        if(tabPiguly.getSelectionModel().getSelectedItem()!=null) {
            long id = tabPiguly.getSelectionModel().getSelectedItem().getId();
            Commons.conn.resetPolaczenPiguly(id);
        }
    }
}
