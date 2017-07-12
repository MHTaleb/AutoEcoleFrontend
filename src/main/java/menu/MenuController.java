/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menu;

import board.BoardController;
import com.jfoenix.controls.JFXButton;
import com.mycompany.fxutilities.Frame;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author taleb
 */
public class MenuController implements Initializable {

    @FXML
    private VBox drawerContent;
    @FXML
    private ImageView userImage;
    @FXML
    private Label userName;
    @FXML
    private Label userLastName;
    @FXML
    private JFXButton btQuit;
    @FXML
    private JFXButton btAcceuil;
    @FXML
    private JFXButton btCandidat;
    @FXML
    private JFXButton btExamens;
    @FXML
    private JFXButton btCaisse;
    @FXML
    private JFXButton btImprimes;
    @FXML
    private JFXButton btConfig;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void doExit(ActionEvent event) {
        Stage stage = (Stage) btQuit.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void showAcceuil(ActionEvent event) {
    }

    @FXML
    private void showCandidat(ActionEvent event) {
    }

    @FXML
    private void showExamin(ActionEvent event) {
    }

    @FXML
    private void showCaisse(ActionEvent event) {
    }

    @FXML
    private void showImprimer(ActionEvent event) {
    }

    @FXML
    private void showConfig(ActionEvent event) {
        try {
            BoardController.mainRoot.getChildren().setAll((Node)
                    FXMLLoader.load(getClass().getResource("/fxml/Config.fxml")));
            Frame.autoSize();
        } catch (IOException ex) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
