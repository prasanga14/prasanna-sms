package com.example.assignment.Teacher;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static com.example.assignment.Static.Uses.changeScene;

public class CreateMCQController {

    @FXML
    private TextField questionIdField;

    @FXML
    private TextField questionTextField;

    @FXML
    private TextField option1Field;

    @FXML
    private TextField option2Field;

    @FXML
    private TextField option3Field;

    @FXML
    private TextField option4Field;

    @FXML
    private TextField correctAnswerField;  // New field for correct answer

    @FXML
    private Button sendButton;

    @FXML
    private void initialize() {
        sendButton.setOnAction(event -> saveMCQData());
    }

    private void saveMCQData() {
        String questionId = questionIdField.getText();
        String questionText = questionTextField.getText();
        String option1 = option1Field.getText();
        String option2 = option2Field.getText();
        String option3 = option3Field.getText();
        String option4 = option4Field.getText();
        String correctAnswer = correctAnswerField.getText();  // Get the correct answer

        // Define the file path
        File file = new File("mcq_questions.csv");

        // Append to the file if it exists, create it if it does not
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(questionId + "," + questionText + "," + option1 + "," + option2 + "," + option3 + "," + option4 + "," + correctAnswer);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception (e.g., show an alert to the user)
        }

        // Clear the input fields
        questionIdField.clear();
        questionTextField.clear();
        option1Field.clear();
        option2Field.clear();
        option3Field.clear();
        option4Field.clear();
        correctAnswerField.clear();
    }

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
