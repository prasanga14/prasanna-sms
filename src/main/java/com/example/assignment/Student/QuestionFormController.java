package com.example.assignment.Student;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import static com.example.assignment.Static.Uses.changeScene;

public class QuestionFormController {

    @FXML
    private TextField questionIdField;


    @FXML
    private TextField questionTextField;

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
        changeScene(event, "ViewCourse.fxml", "STUDENT MANAGEMENT SYSTEM - ViewCourse");
    }

    @FXML
    public void clickStudentQuestionFrom(ActionEvent event) throws IOException {
        changeScene(event, "QuestionForm.fxml", "STUDENT MANAGEMENT SYSTEM - QuestionForm");
    }

    @FXML
    public void clickStudentReportFrom(ActionEvent event) throws IOException {
        changeScene(event, "ReportProblem.fxml", "STUDENT MANAGEMENT SYSTEM - ReportProblem");
    }

    @FXML
    public void clickStudentMCQ(ActionEvent event) throws IOException {
        changeScene(event, "MCQ.fxml", "STUDENT MANAGEMENT SYSTEM - MCQ");
    }

    @FXML
    public void handleSend(ActionEvent event) {
        String questionId = questionIdField.getText();
        String questionText = questionTextField.getText();

        try (PrintWriter writer = new PrintWriter(new FileWriter("question_forms.csv", true))) {
            writer.println(questionId + ","  + questionText);
            // Clear the fields after saving
            questionIdField.clear();
            questionTextField.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
