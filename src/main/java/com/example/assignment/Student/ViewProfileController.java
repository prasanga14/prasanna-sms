package com.example.assignment.Student;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

import static com.example.assignment.Static.Uses.changeScene;

public class ViewProfileController {

    @FXML
    public void clickLogout(ActionEvent event) throws IOException {
        changeScene(event, "Login.fxml", "STUDENT MANAGEMENT SYSTEM");
    }

    @FXML
    public void clickStudentDashboard(ActionEvent event) throws IOException {
        changeScene(event, "StudentDashboard.fxml", "STUDENT MANAGEMENT SYSTEM - StudentDashboard");
    }

    @FXML
    public void clickStudentViewProfile(ActionEvent event) throws IOException {
        changeScene(event, "ViewProfile.fxml", "STUDENT MANAGEMENT SYSTEM - ViewProfile");
    }

    @FXML
    public void clickStudentViewCourse(ActionEvent event) throws IOException {
        System.out.println("View course clicked");
        changeScene(event, "ViewCourse.fxml", "STUDENT MANAGEMENT SYSTEM - ViewCourse ");
    }

    @FXML
    public void clickStudentQuestionFrom(ActionEvent event) throws IOException {
        System.out.println("QN FORM OPENED");
        changeScene(event, "QuestionForm.fxml", "STUDENT MANAGEMENT SYSTEM - QuestionForm");
    }

    @FXML
    public void clickStudentReportFrom(ActionEvent event) throws IOException {
        System.out.println("Report form OPENED");
        changeScene(event, "ReportProblem.fxml", "STUDENT MANAGEMENT SYSTEM - ReportProblem");
    }

    @FXML
    public void clickStudentMCQ(ActionEvent event) throws IOException {
        changeScene(event, "MCQ.fxml", "STUDENT MANAGEMENT SYSTEM - MCQ");
    }

    public void clickstudentviewreport(ActionEvent event) {

    }
}
