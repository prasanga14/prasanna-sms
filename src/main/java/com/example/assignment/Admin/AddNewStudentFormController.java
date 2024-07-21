package com.example.assignment.Admin;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

import static com.example.assignment.Static.CSVUtils.appendCSV;

// Java Doc

/**
 * <h1><code>AdminAddNewStudentForm.java</code></h1>
 * <p>
 * Controller file to manage new students.
 * </p>
 */

public class AddNewStudentFormController implements Initializable {

    @FXML
    private TextField studentIdField, firstNameField, lastNameField,
            numberField, emailField, facultyField;
    @FXML
    private PasswordField passwordField;

    @FXML
    private ComboBox<String> genderCombo;

    @FXML
    private Button addButton;

    @FXML
    private Label idErrorLabel, facultyErrorLabel, nameErrorLabel,
            genderErrorLabel, numberErrorLabel, emailErrorLabel, passwordErrorLabel;

    @FXML
    public void onAddStudent() {
        String studentId = this.studentIdField.getText(),
                firstName = this.firstNameField.getText(),
                lastName = this.lastNameField.getText(),
                gender = this.genderCombo.getValue(),
                phoneNumber = this.numberField.getText(),
                email = this.emailField.getText(),
                faculty = this.facultyField.getText(),
                password = this.passwordField.getText();

        boolean areFieldsFilled = !studentId.isEmpty() && !firstName.isEmpty() && !lastName.isEmpty() &&
                !gender.isEmpty() && (!phoneNumber.isEmpty() && phoneNumber.matches("^\\d{10}")) &&
                !email.isEmpty() && !faculty.isEmpty() && !password.isEmpty();
        boolean isRecordAdded;

        if (!areFieldsFilled) {
            if (studentId.isEmpty()) {
                idErrorLabel.setText("This is a required field.");
            } else {
                idErrorLabel.setText("");
            }
            if (firstName.isEmpty() || lastName.isEmpty()) {
                nameErrorLabel.setText("This is a required field.");
            } else {
                nameErrorLabel.setText("");
            }
            if (gender == null || gender.isEmpty()) {
                genderErrorLabel.setText("This is a required field.");
            } else {
                genderErrorLabel.setText("");
            }
            if (phoneNumber.length() < 10) {
                numberErrorLabel.setText("Please enter a valid phone number.");
            } else {
                numberErrorLabel.setText("");
            }
            if (!email.matches("[\\w\\.-]+@[\\w\\.-]+\\.\\w{2,4}")) {
                emailErrorLabel.setText("Please enter a valid email.");
            } else {
                emailErrorLabel.setText("");
            }
            if (faculty.isEmpty()) {
                facultyErrorLabel.setText("This is a required field.");
            } else {
                facultyErrorLabel.setText("");
            }
            if (password.length() < 8) {
                passwordErrorLabel.setText("Passwords must be greater than 8 characters.");
            } else {
                passwordErrorLabel.setText("");
            }

        } else {
//        refer to the headers for positioning
            String[] data = new String[]{studentId, firstName, lastName, gender, phoneNumber, email, faculty, password};
            isRecordAdded = appendCSV("csv_files/add_student_form.csv", data);

            if (isRecordAdded) {
                ((Stage) addButton.getScene().getWindow()).close();
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//       in 2024 too :/
        String[] genders = {"Male", "Female"};
        this.genderCombo.getItems().addAll(genders);
    }
}
