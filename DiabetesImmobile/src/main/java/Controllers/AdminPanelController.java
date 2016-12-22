package Controllers;

import Global.Commons;
import Model.NursesForTable;
import Model.UsersForTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.swing.*;
import java.io.IOException;
import java.util.List;

public class AdminPanelController {

    @FXML
    private Label firstAndLastName;

    @FXML
    private Button logoutButton;

    @FXML
    private TableView<UsersForTable> tabPatients;

    @FXML
    private TableColumn<UsersForTable, Long> tabPatientsID;

    @FXML
    private TableColumn<UsersForTable, String> tabPatientsFirstName;

    @FXML
    private TableColumn<UsersForTable, String> tabPatientsLastName;

    @FXML
    private TableColumn<UsersForTable, String> tabPatientsLogin;

    @FXML
    private Button deletePatientButton;

    @FXML
    private Button editPatientButton;

    @FXML
    private TextField patientFirstNameTextfield;

    @FXML
    private TextField patientLastNameTextfield;

    @FXML
    private TextField patientLoginTextfield;

    @FXML
    private TextField patientPasswordTextfield;

    @FXML
    private Button addPatientButton;

    @FXML
    private TableView<NursesForTable> tabNurses;

    @FXML
    private TableColumn<NursesForTable, Long> tabNurseID;

    @FXML
    private TableColumn<NursesForTable, String> tabNurseFirstName;

    @FXML
    private TableColumn<NursesForTable, String> tabNurseLastName;

    @FXML
    private TableColumn<NursesForTable, String> tabNurseLogin;

    @FXML
    private TableColumn<NursesForTable, Integer> tabNurseNumberOfPatients;

    @FXML
    private Button deleteNurseButton;

    @FXML
    private Button editNurseButton;

    @FXML
    private TextField nurseFirstNameTextfield;

    @FXML
    private TextField nurseLastNameTextfield;

    @FXML
    private TextField nurseLoginTextfield;

    @FXML
    private TextField nursePasswordTextfield;

    @FXML
    private Button addNurseButton;

    @FXML
    private Button refreshPatientsButton;

    @FXML
    private Button refreshNursesTableButton;

    @FXML
    private Button manageAssignmentsButton;

    @FXML
    public void initialize() throws IOException {
        firstAndLastName.setText(Commons.getImie()+" "+ Commons.getNazwisko());
        refreshNursesTable();
        refreshPatientsTable();
    }

    @FXML
    void logout() throws IOException {
        Commons.windowControls.logout(logoutButton);
    }

    @FXML
    void manageAssignments() throws IOException {
        NursesForTable nurse = tabNurses.getSelectionModel().getSelectedItem();
        if(nurse!=null){
            Commons.setSelectedNurse(nurse);
            Commons.windowControls.newWindow("ManageAssignments.fxml","Zarządzanie przypisaniami");
            refreshPatientsTable();
        }
    }

    @FXML
    void addPatient() throws IOException {
        String imie = patientFirstNameTextfield.getText();
        String nazwisko = patientLastNameTextfield.getText();
        String login = patientLoginTextfield.getText();
        String password = patientPasswordTextfield.getText();
        if(imie.trim().equals("") || nazwisko.trim().equals("") || login.trim().equals("") || password.trim().equals("")){
            JOptionPane.showMessageDialog(null,"Wartości nie mogą być puste","Błąd",JOptionPane.ERROR_MESSAGE);
        }
        else{
            if(Commons.conn.checkUserAvailibility(login.trim(),null)){
                Commons.conn.addUser(imie.trim(),nazwisko.trim(),login.trim(),password.trim());
                patientFirstNameTextfield.setText("");
                patientLastNameTextfield.setText("");
                patientPasswordTextfield.setText("");
                patientLoginTextfield.setText("");
            }
            else{
                JOptionPane.showMessageDialog(null,"Podany login jest już zajęty","Błąd",JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    @FXML
    void addNurse() throws IOException {
        String imie = nurseFirstNameTextfield.getText();
        String nazwisko = nurseLastNameTextfield.getText();
        String login = nurseLoginTextfield.getText();
        String password = nursePasswordTextfield.getText();

        if(imie.trim().equals("") || nazwisko.trim().equals("") || login.trim().equals("") || password.trim().equals("")){
            JOptionPane.showMessageDialog(null,"Wartości nie mogą być puste","Błąd",JOptionPane.ERROR_MESSAGE);
        }
        else{
            if(Commons.conn.checkNurseAvailibility(login.trim(),null)){
                Commons.conn.addNurse(imie.trim(),nazwisko.trim(),login.trim(),password.trim());
                nurseFirstNameTextfield.setText("");
                nurseLastNameTextfield.setText("");
                nurseLoginTextfield.setText("");
                nursePasswordTextfield.setText("");
            }
            else{
                JOptionPane.showMessageDialog(null,"Podany login jest już zajęty","Błąd",JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    @FXML
    void editPatient() throws IOException {
        if(tabPatients.getSelectionModel().getSelectedItem()!=null) {
            Commons.setUserOrNurse(1);
            Commons.setSelectedUser(tabPatients.getSelectionModel().getSelectedItem());
            Commons.windowControls.newWindow("UserEdit.fxml","Edycja pacjenta");
        }
    }

    @FXML
    void editNurse() throws IOException {
        if(tabNurses.getSelectionModel().getSelectedItem()!=null) {
            Commons.setUserOrNurse(2);
            Commons.setSelectedNurse(tabNurses.getSelectionModel().getSelectedItem());
            Commons.windowControls.newWindow("UserEdit.fxml","Edycja pielęgniarki");
        }
    }

    @FXML
    void deletePatient() throws IOException {
        if(tabPatients.getSelectionModel().getSelectedItem()!=null) {
            long id = tabPatients.getSelectionModel().getSelectedItem().getId();
            Commons.conn.deleteUser(id);
        }
    }

    @FXML
    void deleteNurse() throws IOException {
        if(tabNurses.getSelectionModel().getSelectedItem()!=null) {
            long id = tabNurses.getSelectionModel().getSelectedItem().getId();
            Commons.conn.nurseDel(id);
        }
    }

    @FXML
    void refreshPatientsTable() throws IOException {
        List<UsersForTable> users = Commons.conn.getPatients();
        ObservableList<UsersForTable> ousers = FXCollections.observableArrayList(users);
        tabPatientsID.setCellValueFactory(new PropertyValueFactory<UsersForTable, Long>("id"));
        tabPatientsFirstName.setCellValueFactory(new PropertyValueFactory<UsersForTable, String>("firstName"));
        tabPatientsLastName.setCellValueFactory(new PropertyValueFactory<UsersForTable, String>("lastName"));
        tabPatientsLogin.setCellValueFactory(new PropertyValueFactory<UsersForTable, String>("login"));
        tabPatients.setItems(ousers);
    }

    @FXML
    void refreshNursesTable() throws IOException {
        List<NursesForTable> piguly = Commons.conn.getNurses();
        ObservableList<NursesForTable> opiele = FXCollections.observableArrayList(piguly);
        tabNurseID.setCellValueFactory(new PropertyValueFactory<NursesForTable, Long>("id"));
        tabNurseFirstName.setCellValueFactory(new PropertyValueFactory<NursesForTable, String>("firstName"));
        tabNurseLastName.setCellValueFactory(new PropertyValueFactory<NursesForTable, String>("lastName"));
        tabNurseLogin.setCellValueFactory(new PropertyValueFactory<NursesForTable, String>("login"));
        tabNurseNumberOfPatients.setCellValueFactory(new PropertyValueFactory<NursesForTable, Integer>("liczbaPacjentow"));
        tabNurses.setItems(opiele);
    }
}
