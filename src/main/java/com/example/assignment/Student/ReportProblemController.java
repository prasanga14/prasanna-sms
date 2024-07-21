package com.example.assignment.Student;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import static com.example.assignment.Static.Uses.changeScene;

public class ReportProblemController {

    @FXML
    private TextField problemIdField;

    @FXML
    private TextField studentIdField;

    @FXML
    private TextField creatorIdField;

    @FXML
    private TextArea titleField;

    @FXML
    public void clickLogout(ActionEvent event) throws IOException {
        changeScene(event, "Login.fxml", "STUDENT MANAGEMENT SYSTEM");
    }

    @FXML
    public void clickStudentDashboard(ActionEvent event) throws IOException {
        changeScene(event, "StudentDashboard.fxml", "STUDENT MANAGEMENT SYSTEM");
    }

    @FXML
    public void clickStudentViewProfile(ActionEvent event) throws IOException {
        changeScene(event, "ViewProfile.fxml", "STUDENT MANAGEMENT SYSTEM");
    }

    @FXML
    public void clickStudentViewCourse(ActionEvent event) throws IOException {
        changeScene(event, "ViewCourse.fxml", "STUDENT MANAGEMENT SYSTEM");
    }

    @FXML
    public void clickStudentQuestionFrom(ActionEvent event) throws IOException {
        changeScene(event, "QuestionForm.fxml", "STUDENT MANAGEMENT SYSTEM");
    }

    @FXML
    public void clickStudentReportFrom(ActionEvent event) throws IOException {
        changeScene(event, "ReportProblem.fxml", "STUDENT MANAGEMENT SYSTEM");
    }

    @FXML
    public void clickStudentMCQ(ActionEvent event) throws IOException {
        changeScene(event, "MCQ.fxml", "STUDENT MANAGEMENT SYSTEM");
    }

    @FXML
    public void handleSend(ActionEvent event) {
        String problemId = problemIdField.getText();
        String studentId = studentIdField.getText();
        String creatorId = creatorIdField.getText();
        String title = titleField.getText();

        try (PrintWriter writer = new PrintWriter(new FileWriter("report_problems.csv", true))) {
            writer.println(problemId + "," + studentId + "," + creatorId + "," + title);
            // Clear the fields after saving
            problemIdField.clear();
            studentIdField.clear();
            creatorIdField.clear();
            titleField.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void handleBack(ActionEvent event) throws IOException{
        changeScene(event, "StudentDashboard.fxml", "Student Dashboard");
    }
}
