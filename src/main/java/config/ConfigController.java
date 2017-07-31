/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import board.BoardController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import com.mycompany.fxutilities.Frame;
import com.mycompany.fxutilities.ImageConverter;
import com.talcorpdz.autoecole.profilemanagement.dao.ProfileMetier;
import com.talcorpdz.autoecole.profilemanagement.dao.ProfileMetierLocal;
import com.talcorpdz.autoecole.usermanagement.dao.AccountMetier;
import com.talcorpdz.autoecole.usermanagement.dao.AccountMetierLocal;
import dao.ImageMetier;
import dao.ImageMetierLocal;
import entity.Account;
import entity.UserInfo;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import scope.Scope;

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
    @FXML
    private Label validTextDate;
    @FXML
    private Label telephoneValidationLabel;
    @FXML
    private TableView<FXAccount> tableAccounts;
    @FXML
    private TableColumn<FXAccount, String> nomColmn;
    @FXML
    private TableColumn<FXAccount, String> prenomColmn;
    @FXML
    private TableColumn<FXAccount, String> usernameColmn;
    @FXML
    private TableColumn<FXAccount, ImageView> userPhotoColmn;

    private void setupValidation() {
        RequiredFieldValidator fieldValidator;
        btAdd.setDisable(true);

        fieldValidator = new RequiredFieldValidator();
        fieldValidator.setMessage("Champ Obligatoire !");
        fieldValidator.setStyle("-fx-color : #BA1200 ;");
        name.getValidators().add(fieldValidator);
        name.focusedProperty().addListener((observable) -> {
            name.validate();
        });
        name.textProperty().addListener((observable) -> {
            btAdd.setDisable(
                    name.getText().isEmpty()
                    || lastName.getText().isEmpty()
                    || birthDate.getEditor().getText().isEmpty()
                    || phoneNumbers.getItems().isEmpty()
                    || username.getText().isEmpty()
                    || password.getText().isEmpty()
            );
        });

        fieldValidator = new RequiredFieldValidator();
        fieldValidator.setMessage("Champ Obligatoire !");
        fieldValidator.setStyle("-fx-color : #BA1200 ;");
        lastName.getValidators().add(fieldValidator);
        lastName.textProperty().addListener((observable) -> {
            btAdd.setDisable(
                    name.getText().isEmpty()
                    || lastName.getText().isEmpty()
                    || birthDate.getEditor().getText().isEmpty()
                    || phoneNumbers.getItems().isEmpty()
                    || username.getText().isEmpty()
                    || password.getText().isEmpty()
            );
        });
        lastName.focusedProperty().addListener((observable) -> {
            lastName.validate();
        });

        birthDate.focusedProperty().addListener((fcs) -> {

            validTextDate.setText("");
            if (birthDate.editorProperty().getValue().getText().isEmpty()) {
                validTextDate.setText("Champ Obligatoire !");
            }

            btAdd.setDisable(
                    name.getText().isEmpty()
                    || lastName.getText().isEmpty()
                    || birthDate.getEditor().getText().isEmpty()
                    || phoneNumbers.getItems().isEmpty()
                    || username.getText().isEmpty()
                    || password.getText().isEmpty()
            );
        });

        phoneNumbers.focusedProperty().addListener((observable) -> {
            telephoneValidationLabel.setText("");
            if (phoneNumbers.getItems().isEmpty()) {
                telephoneValidationLabel.setText("Champ Obligatoire !");
            }

            btAdd.setDisable(
                    name.getText().isEmpty()
                    || lastName.getText().isEmpty()
                    || birthDate.getEditor().getText().isEmpty()
                    || phoneNumbers.getItems().isEmpty()
                    || username.getText().isEmpty()
                    || password.getText().isEmpty()
            );
        });

        fieldValidator = new RequiredFieldValidator();
        fieldValidator.setMessage("Champ Obligatoire !");
        fieldValidator.setStyle("-fx-color : #BA1200 ;");
        username.getValidators().add(fieldValidator);
        username.textProperty().addListener((observable) -> {
            btAdd.setDisable(
                    name.getText().isEmpty()
                    || lastName.getText().isEmpty()
                    || birthDate.getEditor().getText().isEmpty()
                    || phoneNumbers.getItems().isEmpty()
                    || username.getText().isEmpty()
                    || password.getText().isEmpty()
            );
        });
        username.focusedProperty().addListener((observable) -> {
            username.validate();
        });

        fieldValidator = new RequiredFieldValidator();
        fieldValidator.setMessage("Champ Obligatoire !");
        fieldValidator.setStyle("-fx-color : #BA1200 ;");
        password.getValidators().add(fieldValidator);
        password.textProperty().addListener((observable) -> {
            btAdd.setDisable(
                    name.getText().isEmpty()
                    || lastName.getText().isEmpty()
                    || birthDate.getEditor().getText().isEmpty()
                    || phoneNumbers.getItems().isEmpty()
                    || username.getText().isEmpty()
                    || password.getText().isEmpty()
            );
        });
        password.focusedProperty().addListener((observable) -> {
            password.validate();
        });

    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            setupValidation();
            initGrid();
            readAllAccounts();
            setupAuthorization();
        } catch (IOException ex) {
            Logger.getLogger(ConfigController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void showPassword(ActionEvent event) {
        JFXDialogLayout content = new JFXDialogLayout();
        JFXDialog dialog = new JFXDialog(BoardController.myStackPAne, content, JFXDialog.DialogTransition.CENTER);

        content.setHeading(new Text("Le mot de passe saisi"));
        content.setBody(new Text("\b " + password.getText()));
        content.setStyle("-fx-background-color : #fff;");
        dialog.show(BoardController.myStackPAne);

    }

    private Long updatableID;

    @FXML
    private void addUser(ActionEvent event) {
        try {

            if (!actionUpdate) {
                // do add user
                AccountMetierLocal accountMetierLocal = new AccountMetier();
                Long idAccount = accountMetierLocal.ajouter(username.getText(), password.getText());

                ProfileMetierLocal profileMetierLocal = new ProfileMetier();
                DateTimeFormatter f = DateTimeFormatter.ofPattern("dd/MM/uuuu");
                LocalDate ld = LocalDate.parse(birthDate.getEditor().getText(), f);
                Long idProfile = profileMetierLocal.creerProfile(
                        name.getText(),
                        lastName.getText(),
                        ld, phoneNumbers.getItems());

                profileMetierLocal.lierCompteProfil(idAccount, idProfile);

                ImageMetierLocal imageMetierLocal = new ImageMetier();
                String extension = ImageConverter.getExtension(SelectedImage.getPath());
                byte[] imageData = ImageConverter.toByteArray(SelectedImage);
                System.out.println(" Path :" + SelectedImage.getAbsolutePath());
                System.out.println("image data lengh :" + imageData.length + "  Path :" + SelectedImage.getAbsolutePath());
                Long idImage = imageMetierLocal.enregistrer(extension, imageData);
                profileMetierLocal.lierProfilImage(idProfile, idImage);

            } else {
                // do update user
                AccountMetierLocal accountMetierLocal = new AccountMetier();
                accountMetierLocal.modifier(updatableID, username.getText(), password.getText());

                ProfileMetierLocal profileMetierLocal = new ProfileMetier();
                DateTimeFormatter f = DateTimeFormatter.ofPattern("dd/MM/uuuu");
                LocalDate ld = LocalDate.parse(birthDate.getEditor().getText(), f);
                try {
                    boolean modifier = profileMetierLocal.modifier(accountMetierLocal.rechercher(updatableID).getUserInfo().getId(), name.getText(),lastName.getText(), ld, phoneNumbers.getItems());
                } catch (Exception e) {
                     Long idProfile = profileMetierLocal.creerProfile(
                        name.getText(),
                        lastName.getText(),
                        ld, phoneNumbers.getItems());

                profileMetierLocal.lierCompteProfil(updatableID, accountMetierLocal.rechercher(updatableID).getUserInfo().getId());

                }
                ImageMetierLocal imageMetierLocal = new ImageMetier();
                 String extension = ImageConverter.getExtension(SelectedImage.getPath());
                 byte[] imageData = ImageConverter.toByteArray(SelectedImage);
                 try {
                    imageMetierLocal.midifier(accountMetierLocal.rechercher(updatableID).getUserInfo().getImage().getId(), extension, imageData);
                    
                } catch (Exception e) {
                     Long idImage = imageMetierLocal.enregistrer(extension, imageData);
                profileMetierLocal.lierProfilImage(accountMetierLocal.rechercher(updatableID).getUserInfo().getId(), idImage);
                }
                btAdd.setText("Valider");
            }

        } catch (IOException ex) {
            Logger.getLogger(ConfigController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ConfigController.class.getName()).log(Level.SEVERE, null, ex);

        }

    }
    private static File SelectedImage;

    @FXML
    private void uploadImage(MouseEvent event) throws FileNotFoundException {
        SelectedImage = Frame.showOpenDialog("Selection d'image ", new String[]{
            "bmp", "jpg"
        });

        Image imageShown = new Image(new FileInputStream(SelectedImage));
        image.setImage(imageShown);
    }

    @FXML
    private void resetForm(ActionEvent event) {
        InputStream is;
        is = getClass().getResourceAsStream("/media/config/manager.png");
        image.setImage(new Image(is));
        name.setText("");
        lastName.setText("");
        birthDate.getEditor().setText("");
        phoneNumbers.getItems().removeAll(phoneNumbers.getItems());
        username.setText("");
        password.setText("");
        try {
            is.close();
        } catch (IOException ex) {
            Logger.getLogger(ConfigController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void addPhoneNumber(KeyEvent event) {
        // deprecated not used
    }

    @FXML
    private void addPhone(ActionEvent event) {
        if (phoneNumbers.editorProperty().getValue().getText().isEmpty()) {
            return;
        }
        String phoneString = phoneNumbers.editorProperty().getValue().getText();
        if (phoneNumbers.getItems().contains(phoneString)) {
            return;
        }
        phoneNumbers.itemsProperty().getValue().add(phoneString);

    }

    @FXML
    private void delPhone(ActionEvent event) {
        try {
            phoneNumbers.getItems().remove(phoneNumbers.selectionModelProperty().getValue().getSelectedIndex());
        } catch (IndexOutOfBoundsException e) {
            // do nothing
        }
    }

    private void readAllAccounts() throws IOException {
        AccountMetierLocal accountMetierLocal = new AccountMetier();
        List<Account> accounts = accountMetierLocal.listerToutLesComptes();
        ObservableList<FXAccount> observableArrayList = FXCollections.observableArrayList();
        for (Account account : accounts) {
            observableArrayList.add(new FXAccount(account));
        }
        tableAccounts.getItems().setAll(observableArrayList);
    }

    private void initGrid() {
        nomColmn.setCellValueFactory(new PropertyValueFactory<>("nameProperty"));
        prenomColmn.setCellValueFactory(new PropertyValueFactory<>("lasNameProperty"));
        usernameColmn.setCellValueFactory(new PropertyValueFactory<>("usernameProperty"));
        userPhotoColmn.setCellValueFactory(new PropertyValueFactory<>("userImageProperty"));
        tableAccounts.setOnMouseClicked((event) -> {
            try {

                String column = tableAccounts.getSelectionModel().getSelectedCells().get(0).getTableColumn().getText();
                if (column != null) {
                    if (column.equalsIgnoreCase("photo")) {
                        ImageView userImageProperty = tableAccounts.getSelectionModel().getSelectedItem().getUserImageProperty();
                        Image image1 = userImageProperty.getImage();
                        JFXDialogLayout content = new JFXDialogLayout();
                        JFXDialog dialog = new JFXDialog(BoardController.myStackPAne, content, JFXDialog.DialogTransition.CENTER);
                        content.setHeading(new Text("Image Viewer"));
                        final ImageView myImageView = new ImageView(image1);
                        myImageView.setFitHeight(350);
                        myImageView.setFitWidth(350);
                        content.setBody(myImageView);
                        System.out.println("click");
                        content.setStyle("-fx-background-color : black;");
                        dialog.show(BoardController.myStackPAne);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        });
    }

    @FXML
    private void delete(ActionEvent event) {
        JFXDialogLayout content = new JFXDialogLayout();
        JFXDialog dialog = new JFXDialog(BoardController.myStackPAne, content, JFXDialog.DialogTransition.CENTER);
        content.setHeading(new Text("Saisir le mot de passe"));
        JFXPasswordField jfxPasswordField = new JFXPasswordField();
        content.setBody(jfxPasswordField);
        JFXButton deleteBTN = new JFXButton("Suprimmer");
        content.setActions(deleteBTN);
        deleteBTN.onActionProperty().set((ActionEvent evt) -> {
            Account account = tableAccounts.getSelectionModel().getSelectedItem().getAccount();
            if (account.getPassword().equals(jfxPasswordField.getText())) {

                UserInfo userInfo = null;
                try {
                    userInfo = account.getUserInfo();
                    
                } catch (Exception e) {
                }
                entity.Image image1 = null;
                try {
                    image1 = userInfo.getImage();
                } catch (Exception e) {
                }
                try {
                AccountMetierLocal accountMetierLocal = new AccountMetier();
                accountMetierLocal.suprimer(account.getId());
                    
                } catch (Exception e) {
                }

                try {
                    ProfileMetierLocal profileMetierLocal = new ProfileMetier();
                    profileMetierLocal.supprimer(userInfo.getId());
                    
                } catch (Exception e) {
                }
                try {
                    ImageMetierLocal imageMetierLocal = new ImageMetier();
                    imageMetierLocal.supprimer(image1.getId());
                    
                } catch (Exception e) {
                }
                try {
                    readAllAccounts();
                } catch (IOException ex) {
                    Logger.getLogger(ConfigController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        dialog.show();
    }

    private boolean actionUpdate;

    @FXML
    private void setupUpdate(ActionEvent event) {
        btAdd.setText("Valider");
        JFXDialogLayout content = new JFXDialogLayout();
        JFXDialog dialog = new JFXDialog(BoardController.myStackPAne, content, JFXDialog.DialogTransition.CENTER);
        content.setHeading(new Text("Saisir le mot de passe"));
        JFXPasswordField jfxPasswordField = new JFXPasswordField();
        content.setBody(jfxPasswordField);
        JFXButton updateBTN = new JFXButton("Vérifier");
        content.setActions(updateBTN);
        updateBTN.onActionProperty().set((ActionEvent evt) -> {
            Account account = tableAccounts.getSelectionModel().getSelectedItem().getAccount();
            if (account.getPassword().equals(jfxPasswordField.getText())) {
                actionUpdate = true;
                btAdd.setText("Modifier");
                final UserInfo userInfo = account.getUserInfo();
                image.setImage(new Image(getClass().getResourceAsStream("/media/config/manager.png")));
                if (userInfo != null) {
                    // showing image
                    entity.Image imageLoaded = userInfo.getImage();
                    if (imageLoaded != null) {
                        byte[] myImage = imageLoaded.getImage();
                        if (myImage != null) {
                            try {
                                SelectedImage = ImageConverter.readFile(imageLoaded.getId(), myImage, imageLoaded.getExtension());
                                
                                image.setImage(new Image(new FileInputStream(SelectedImage)));
                            } catch (IOException ex) {
                                Logger.getLogger(ConfigController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }

                    // showing user datas
                    String nom = userInfo.getNom();
                    name.setText(nom);
                    String prenom = userInfo.getPrenom();
                    lastName.setText(prenom);
                    List<String> telephones = userInfo.getTelephones();
                    phoneNumbers.getItems().setAll(telephones);
                    LocalDate dateNaissance = userInfo.getDateNaissance();
                    birthDate.getEditor().setText(dateNaissance.format(DateTimeFormatter.ofPattern("d/MM/uuuu")));

                } else {
                    name.setText("non définie");
                    lastName.setText("non définie");
                    phoneNumbers.getItems().removeAll(phoneNumbers.getItems());
                    birthDate.getEditor().setText("00/00/0000");
                }
                updatableID = account.getId();
                String username1 = account.getUsername();
                username.setText(username1);
                String password1 = account.getPassword();
                password.setText(password1);

            }
            dialog.close();
        });
        dialog.show(BoardController.myStackPAne);
    }

    private void setupAuthorization() {
        
        Long idAccount = (Long) Scope.getAttribute(Scope.ApplicationContext.CURRENT_ACCOUNTID);
        System.out.println("cuurent idAccount connected : "+idAccount);
    }

}
