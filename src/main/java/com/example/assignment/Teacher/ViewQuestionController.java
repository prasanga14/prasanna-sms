package com.example.assignment.Teacher;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;

import static com.example.assignment.Static.Uses.changeScene;

public class ViewQuestionController {

    @FXML
    private VBox questionContainer; // Add a VBox to hold the questions

    @FXML
    public void initialize() {
        // Read the CSV file and display the questions
        String csvFile = "question_forms.csv"; // Update this path as needed
        String line;
        String cvsSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                String[] question = line.split(cvsSplitBy);
                for (String q : question) {
                    Label questionLabel = new Label(q);
                    questionContainer.getChildren().add(questionLabel);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
