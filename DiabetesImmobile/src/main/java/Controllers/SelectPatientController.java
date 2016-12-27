package Controllers;

import Global.Commons;
import Model.UsersForTable;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.List;

public class SelectPatientController {

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
    private Button selectButton;

    @FXML
    private Button cancelButton;

    @FXML
    private Button refreshButton;

    @FXML
    void initialize() throws IOException {
        Platform.runLater(new Runnable() {
            @Override public void run() {
                try {
                    Commons.conn.refreshSingleUser(Commons.getSelectedUser());
                    Commons.conn.refreshSingleNurse(Commons.getSelectedNurse());
                    refreshTable();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @FXML
    void cancel() {
        Commons.windowControls.closeWindow(cancelButton);
    }

    @FXML
    void select() throws IOException {
        UsersForTable selected = tabPatients.getSelectionModel().getSelectedItem();
        if(selected!=null) {
            Commons.conn.assignSingleUser(Commons.getSelectedNurse().getId(),selected.getId());
            refreshTable();
        }
    }

    @FXML
    void refreshTable() throws IOException {
        List<UsersForTable> nurseUsers = Commons.conn.getPatientsOfNurse(Commons.getSelectedNurse().getId());
        List<UsersForTable> allUsers = Commons.conn.getPatients();
        allUsers.removeAll(nurseUsers);
        ObservableList<UsersForTable> ousers = FXCollections.observableArrayList(allUsers);
        tabPatientsID.setCellValueFactory(new PropertyValueFactory<UsersForTable, Long>("id"));
        tabPatientsFirstName.setCellValueFactory(new PropertyValueFactory<UsersForTable, String>("firstName"));
        tabPatientsLastName.setCellValueFactory(new PropertyValueFactory<UsersForTable, String>("lastName"));
        tabPatientsLogin.setCellValueFactory(new PropertyValueFactory<UsersForTable, String>("login"));
        tabPatients.setItems(ousers);
    }

}
