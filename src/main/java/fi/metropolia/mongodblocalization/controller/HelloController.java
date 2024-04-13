package fi.metropolia.mongodblocalization.controller;

import fi.metropolia.mongodblocalization.model.HelloModel;
import fi.metropolia.mongodblocalization.model.User;
import fi.metropolia.mongodblocalization.view.HelloApplication;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class HelloController {
    public TextField idInput;
    public TextField nameInput;
    public TextField ageInput;
    public TextField cityInput;
    private HelloApplication view;
   private HelloModel model;
    public HelloController(HelloApplication application) {
        view = application;
        model = new HelloModel();
    }



   @FXML
   private void onReadBtn() {
      User user =model.readUser(idInput.getText());
      if(user!=null){
          clearFields();
          view.openPopup(user.toString());
        }else {
            view.openPopup("User not found");
        }
   }
    @FXML
    private void onAddBtn() {
        boolean success = false;
        try {
            success=model.createUser(idInput.getText(), nameInput.getText(), Integer.parseInt(ageInput.getText()), cityInput.getText());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        if(success){
            clearFields();
            view.openPopup("User added successfully");
        }else {
            view.openPopup("User add failed");
        }
        }
    @FXML
    private void onDeleteBtn() {
        if(model.deleteUser(idInput.getText())){
            clearFields();
            view.openPopup("User deleted successfully");
        }else {
            view.openPopup("User delete failed");
        }
    }
    @FXML
    private void onUpdateBtn() {
        boolean success = false;
        try {
            success=model.updateUser(idInput.getText(), nameInput.getText(), Integer.parseInt(ageInput.getText()),cityInput.getText());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        if(success){
            clearFields();
            view.openPopup("User updated successfully");
        }else {
            view.openPopup("User update failed");
        }
        }

        private void clearFields() {
        idInput.setText("");
        nameInput.setText("");
        ageInput.setText("");
        cityInput.setText("");
        }
    }
