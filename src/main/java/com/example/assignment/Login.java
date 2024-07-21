package com.example.assignment;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.ResourceBundle;

import static com.example.assignment.Static.Uses.changeScene;

public class Login implements Initializable {

 @FXML
 private TextField emailField;

 @FXML
 private PasswordField passwordField;

 @FXML
 public void handleLogin(ActionEvent event) throws IOException {
  String email = emailField.getText();
  String password = passwordField.getText();
  String encryptedPassword = encryptPassword(password);

  if (validateLogin(email, encryptedPassword)) {
   if (email.endsWith("@admin.com")) {
    changeScene(event, "AdminDashboard.fxml", "Admin Dashboard");
   } else if (email.endsWith("@student.com")) {
    changeScene(event, "StudentDashboard.fxml", "Student Dashboard");
   } else if (email.endsWith("@teacher.com")) {
    changeScene(event, "TeacherDashboard.fxml", "Teacher Dashboard");
   } else if (email.endsWith("@staff.com")) {
    changeScene(event, "StaffDashboard.fxml", "Staff Dashboard");
   } else {
    showAlert(Alert.AlertType.ERROR, "Login Error", "Invalid email domain!");
   }
  } else {
   showAlert(Alert.AlertType.ERROR, "Login Error", "Invalid email or password!");
  }
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

 private boolean validateLogin(String email, String encryptedPassword) {
  try (BufferedReader reader = new BufferedReader(new FileReader("users.csv"))) {
   String line;
   while ((line = reader.readLine()) != null) {
    String[] details = line.split(",");
    if (details[0].equals(email) && details[1].equals(encryptedPassword)) {
     return true;
    }
   }
  } catch (IOException e) {
   e.printStackTrace();
  }
  return false;
 }

 private void showAlert(Alert.AlertType alertType, String title, String message) {
  Alert alert = new Alert(alertType);
  alert.setTitle(title);
  alert.setHeaderText(null);
  alert.setContentText(message);
  alert.showAndWait();
 }

 @Override
 public void initialize(URL url, ResourceBundle resourceBundle) {
  // Initialization if needed
 }

 public void openRegister(ActionEvent event) throws IOException {
  changeScene(event, "register.fxml", "Register Page");
 }
}
