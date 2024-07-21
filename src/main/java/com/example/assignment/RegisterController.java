package com.example.assignment;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import static com.example.assignment.Static.Uses.changeScene;

public class RegisterController {

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    public void handleRegister(ActionEvent event) throws IOException {
        String email = emailField.getText();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        if (!password.equals(confirmPassword)) {
            showAlert(Alert.AlertType.ERROR, "Registration Error", "Passwords do not match!");
            return;
        }

        String encryptedPassword = encryptPassword(password);
        saveUserDetails(email, encryptedPassword);

        clearFields();
        showAlert(Alert.AlertType.INFORMATION, "Registration Successful", "Your account has been created successfully.");
        changeScene(event, "login.fxml", "login page");

    }

    private String encryptPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;

        }
    }

    private void saveUserDetails(String email, String encryptedPassword) {
        try (FileWriter writer = new FileWriter("users.csv", true)) {
            writer.append(email).append(",").append(encryptedPassword).append("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void clearFields() {
        emailField.clear();
        passwordField.clear();
        confirmPasswordField.clear();
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void openLogin(ActionEvent event) throws IOException{
        changeScene(event, "Login.fxml", "Login page");
    }
}
