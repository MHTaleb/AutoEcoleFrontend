package com.talcorpdz.autoecole.clientgui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import com.mycompany.fxutilities.Frame;
import com.talcorpdz.autoecole.usermanagement.dao.AccountMetier;
import com.talcorpdz.autoecole.usermanagement.dao.AccountMetierLocal;
import de.jensd.fx.glyphs.GlyphsBuilder;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entity.Account;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import scope.Scope;

public class FXMLController implements Initializable {

    private static final String EM1 = "1em";
    private static final String ERROR = "error";

    @FXML
    private JFXButton btConnect;
    @FXML
    private JFXTextField username;
    @FXML
    private JFXPasswordField password;
    @FXML
    private StackPane rootStackPane;
    @FXML
    private Pane loginPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        btConnect.setDisable(true);
        
        // validation
        RequiredFieldValidator validator;

        validator = new RequiredFieldValidator();
        validator.setMessage("Veuillez saisir le nom d'utilisateur");
        validator.setIcon(GlyphsBuilder.create(FontAwesomeIconView.class)
                .glyph(FontAwesomeIcon.WARNING)
                .size(EM1)
                .styleClass(ERROR)
                .build());
        username.getValidators().add(validator);
        username.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal) {
            }
        });

        username.textProperty().addListener((listner) -> {
            username.validate();
            btConnect.setDisable(password.getText().isEmpty() || username.getText().isEmpty());
        });

        validator = new RequiredFieldValidator();
        validator.setMessage("Veuillez Saisir le mot de passe");
        validator.setIcon(GlyphsBuilder.create(FontAwesomeIconView.class)
                .glyph(FontAwesomeIcon.WARNING)
                .size(EM1)
                .styleClass(ERROR)
                .build());
        password.getValidators().add(validator);
        password.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal) {
            }
        });

        password.textProperty().addListener((list) -> {
            password.validate();
            btConnect.setDisable(password.getText().isEmpty() || username.getText().isEmpty());

        });

    }

    @FXML
    private void doConnect(ActionEvent event) {
        
        AccountMetierLocal accountMetier = new AccountMetier();
        List<Account> accounts = accountMetier.listerToutLesComptes();
        if (accounts != null) {
            switch(accounts.size()){
                case 0:{
                        Long accountID = accountMetier.ajouter(username.getText().trim(), password.getText().trim());
                        if (accountID != null   &&   accountID.intValue() >=0) {
                            try {
                                Scope.setAttribute(Scope.ApplicationContext.CURRENT_ACCOUNTID, accountID);
                                Frame.load(event, this.getClass() ,"/fxml/Board.fxml" , "se connecter");
                                Frame.autoSize();
                            } catch (IOException ex) {
                                Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    break;
                }
                default:{
                        Long accountID = accountMetier.connecter(username.getText().trim(), password.getText().trim());
                        if (accountID != null   &&   accountID.intValue() >=0) {
                            try {
                                Scope.setAttribute(Scope.ApplicationContext.CURRENT_ACCOUNTID, accountID);
                                Frame.load(event, this.getClass() ,"/fxml/Board.fxml" , "se connecter");
                                Frame.autoSize();
                            } catch (IOException ex) {
                                Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }else{
                            
                            JFXDialogLayout content = new JFXDialogLayout();
                            JFXDialog dialog = new JFXDialog(rootStackPane, content, JFXDialog.DialogTransition.CENTER);
                            
                            content.setHeading(new Text("Authentification Échoué !"));
                            content.setBody(new Text("Mot de passe ou nom utilisateur incorrect !\nVeuillez réessayer ."));
                            content.setStyle("-fx-background-color : yellow;");
                            JFXButton quitButton = new JFXButton("OK");
                            quitButton.setOnAction((action)->{
                                dialog.close();
                            });
                            content.setActions(quitButton);
                            
                            dialog.show(rootStackPane);
                        }
                    break;
                }
            }
        }
        
    }

    @FXML
    private void opaqueTrans(MouseEvent event) {
        loginPane.setOpacity(0.8);
    }

    @FXML
    private void opaqueFull(MouseEvent event) {
        loginPane.setOpacity(1);
    }

}
