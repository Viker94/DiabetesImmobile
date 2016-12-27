package Controllers;

import Global.Commons;
import Model.UsersForTable;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.List;

public class ManageAssignmentsController {

    @FXML
    private Label firstLastName;

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
    private Button finishButton;

    @FXML
    private Button refreshButton;

    @FXML
    private Button addPatientButton;

    @FXML
    private Button removePatientButton;

    @FXML
    public void initialize() throws IOException {
        Platform.runLater(new Runnable() {
            @Override public void run() {
                try {
                    Commons.conn.refreshSingleUser(Commons.getSelectedUser());
                    Commons.conn.refreshSingleNurse(Commons.getSelectedNurse());
                    firstLastName.setText(Commons.getSelectedNurse().getFirstName()+" "+ Commons.getSelectedNurse().getLastName());
                    refreshTable();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @FXML
    void addPatient() throws IOException {
        Commons.windowControls.newWindow("SelectPatient.fxml","Użytkownicy");
    }

    @FXML
    void removePatient() throws IOException {
        UsersForTable user = tabPatients.getSelectionModel().getSelectedItem();
        if(user!=null){
            Commons.conn.deassignSingleUser(Commons.getSelectedNurse().getId(),user.getId());
            refreshTable();
        }
    }

    @FXML
    void finish() {

        Commons.windowControls.closeWindow(finishButton);
    }

    @FXML
    void refreshTable() throws IOException {
        List<UsersForTable> users = Commons.conn.getPatientsOfNurse(Commons.getSelectedNurse().getId());
        ObservableList<UsersForTable> ousers = FXCollections.observableArrayList(users);
        tabPatientsID.setCellValueFactory(new PropertyValueFactory<UsersForTable, Long>("id"));
        tabPatientsFirstName.setCellValueFactory(new PropertyValueFactory<UsersForTable, String>("firstName"));
        tabPatientsLastName.setCellValueFactory(new PropertyValueFactory<UsersForTable, String>("lastName"));
        tabPatientsLogin.setCellValueFactory(new PropertyValueFactory<UsersForTable, String>("login"));
        tabPatients.setItems(ousers);
    }

}
