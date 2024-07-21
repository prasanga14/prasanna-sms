package com.example.assignment.Staff;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

import static com.example.assignment.Static.Uses.changeScene;

public class DashboardController {

    @FXML
    public void clickDashboard(ActionEvent event) throws IOException {
        changeScene(event, "StaffDashboard.fxml", "Staff: Dashboard");
    }

    @FXML
    public void clickManageStudentInfo(ActionEvent event) throws IOException {
        changeScene(event, "StaffDashboard.fxml", "Staff: Manage Admissions");
    }

    @FXML
    public void clickManageAdmissions(ActionEvent event) throws IOException {
        changeScene(event, "ManageAdmissions.fxml", "Staff: Manage Admissions");
    }

    @FXML
    public void clickManageLibrary(ActionEvent event) throws IOException {
        changeScene(event, "ManageLibrary.fxml", "Staff: Manage Library");
    }

    @FXML
    public void clickLogout(ActionEvent event) {

    }
}
