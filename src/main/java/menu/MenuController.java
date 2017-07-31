/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menu;

import board.BoardController;
import com.jfoenix.controls.JFXButton;
import com.mycompany.fxutilities.Frame;
import com.mycompany.fxutilities.ImageConverter;
import com.talcorpdz.autoecole.usermanagement.dao.AccountMetier;
import com.talcorpdz.autoecole.usermanagement.dao.AccountMetierLocal;
import entity.Account;
import entity.Image;
import java.io.File;
import java.io.FileInputStream;
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
import scope.Scope;


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
        Long accountID = (Long) Scope.getAttribute(Scope.ApplicationContext.CURRENT_ACCOUNTID);
        AccountMetierLocal accountMetier = new AccountMetier();

        Account account = accountMetier.rechercher(accountID);
        Image image = account.getUserInfo().getImage();
        try {
            File readFile = ImageConverter.readFile(image.getId(), image.getImage(), image.getExtension());
            userImage.setImage(new javafx.scene.image.Image(new FileInputStream(readFile)));
        } catch (IOException ex) {
            Logger.getLogger(BoardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        userLastName.setText(account.getUserInfo().getPrenom());
        userName.setText(account.getUserInfo().getNom());
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
            BoardController.mainRoot.getChildren().setAll((Node) FXMLLoader.load(getClass().getResource("/fxml/Config.fxml")));
            Frame.autoSize();
        } catch (IOException ex) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
