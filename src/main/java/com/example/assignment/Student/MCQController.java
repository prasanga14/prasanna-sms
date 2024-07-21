package com.example.assignment.Student;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.example.assignment.Static.Uses.changeScene;

public class MCQController {
    @FXML
    private VBox mcqContainer;

    @FXML
    private Button nextButton;

    @FXML
    private Button submitButton;

    private List<MCQ> mcqList = new ArrayList<>();
    private int currentIndex = 0;

    @FXML
    public void initialize() {
        loadMCQs();
        if (!mcqList.isEmpty()) {
            displayMCQ(currentIndex);
        }
    }

    private void loadMCQs() {
        mcqList = loadMCQsFromCSV();
    }

    private List<MCQ> loadMCQsFromCSV() {
        List<MCQ> mcqs = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("mcq_questions.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 7) {
                    int id = Integer.parseInt(parts[0]);
                    String questionText = parts[1];
                    List<String> options = new ArrayList<>();
                    for (int i = 2; i <= 5; i++) {
                        options.add(parts[i]);
                    }
                    String correctAnswer = parts[6];
                    mcqs.add(new MCQ(id, questionText, options, correctAnswer));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mcqs;
    }

    private void displayMCQ(int index) {
        if (index >= 0 && index < mcqList.size()) {
            mcqContainer.getChildren().clear();
            MCQ mcq = mcqList.get(index);
            Label questionLabel = new Label(mcq.getQuestion());
            mcqContainer.getChildren().add(questionLabel);

            for (String option : mcq.getOptions()) {
                CheckBox checkBox = new CheckBox(option);
                mcqContainer.getChildren().add(checkBox);
            }
        }
    }

    @FXML
    public void handleNextButton(ActionEvent event) {
        if (currentIndex < mcqList.size() - 1) {
            currentIndex++;
            displayMCQ(currentIndex);
        }
    }

    @FXML
    public void handleSubmitButton(ActionEvent event) {
        List<String> results = new ArrayList<>();
        for (MCQ mcq : mcqList) {
            results.add(validateMCQ(mcq));
        }
        saveResultsToCSV(results);
    }

    private String validateMCQ(MCQ mcq) {
        // Placeholder implementation
        return mcq.getQuestion() + "," + mcq.getCorrectAnswer();
    }

    private void saveResultsToCSV(List<String> results) {
        File file = new File("submitted_mcqs.csv");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (String result : results) {
                writer.write(result);
                writer.newLine();
            }
            showAlert(AlertType.INFORMATION, "Results Saved", "The results have been saved to submitted_mcqs.csv.");
        } catch (IOException e) {
            showAlert(AlertType.ERROR, "Error Saving Results", "An error occurred while saving the results.");
        }
    }

    private void showAlert(AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

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
        changeScene(event, "ViewCourse.fxml", "STUDENT MANAGEMENT SYSTEM - ViewCourse ");
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
}
