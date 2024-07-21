package com.example.assignment.Teacher;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

import static com.example.assignment.Static.Uses.changeScene;

public class DashboardController {


    @FXML
    public void openTeacherDashboard(ActionEvent event) throws IOException {
        changeScene(event, "TeacherDashboard.fxml", "STUDENT MANAGEMENT SYSTEM");
    }


    @FXML
    public void openGradeManagement(ActionEvent event) throws IOException {
        changeScene(event, "GradeManagement.fxml", "STUDENT MANAGEMENT SYSTEM");
    }


    @FXML
    public void openQnForm(ActionEvent event) throws IOException {
        changeScene(event, "ViewQuestion.fxml", "STUDENT MANAGEMENT SYSTEM");
    }


    @FXML
    public void openViewProblemForm(ActionEvent event) throws IOException {
        changeScene(event, "ViewProblem.fxml", "STUDENT MANAGEMENT SYSTEM");
    }


    @FXML
    public void openCreateMcq(ActionEvent event) throws IOException {
        changeScene(event, "CreateMCQ.fxml", "STUDENT MANAGEMENT SYSTEM");
    }


    @FXML
    public void clickLogout(ActionEvent event) throws IOException {
        changeScene(event, "Login.fxml", "STUDENT MANAGEMENT SYSTEM");
    }



}
