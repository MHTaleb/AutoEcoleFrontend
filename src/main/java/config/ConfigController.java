/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author taleb
 */
public class ConfigController implements Initializable {

    @FXML
    private JFXTextField name;
    @FXML
    private JFXTextField lastName;
    @FXML
    private JFXDatePicker birthDate;
    @FXML
    private JFXComboBox<String> phoneNumbers;
    @FXML
    private JFXTextField username;
    @FXML
    private JFXPasswordField password;
    @FXML
    private JFXButton btAdd;
    @FXML
    private ImageView image;
    @FXML
    private JFXButton reset;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        phoneNumbers.onMouseClickedProperty().addListener((observable) -> {
            phoneNumbers.getItems().remove(phoneNumbers.selectionModelProperty().get().getSelectedItem());
        });
    }    

    @FXML
    private void addPhoneNumber(ActionEvent event) {
        String addedPhone = phoneNumbers.editorProperty().getValue().getText();
        ObservableList<String> items = phoneNumbers.itemsProperty().getValue();
        if(!items.contains(addedPhone)){
             items.add(addedPhone);
        }
        
    }

    @FXML
    private void showPassword(ActionEvent event) {
    }

    @FXML
    private void addUser(ActionEvent event) {
    }

    @FXML
    private void uploadImage(MouseEvent event) {
    }

    @FXML
    private void resetForm(ActionEvent event) {
    }
    
}
