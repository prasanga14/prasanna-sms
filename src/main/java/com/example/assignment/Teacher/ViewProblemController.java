package com.example.assignment.Teacher;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.example.assignment.Static.Uses.changeScene;

public class ViewProblemController implements Initializable {

    @FXML
    private VBox problemContainer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String csvFile = "report_problems.csv";
        String line;
        String csvSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                String[] problem = line.split(csvSplitBy);
                if (problem.length > 0) {
                    // Get the last item from the array
                    String lastItem = problem[problem.length - 1];
                    Label problemLabel = new Label(lastItem);
                    problemContainer.getChildren().add(problemLabel);
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
