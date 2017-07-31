/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import board.BoardController;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.mycompany.fxutilities.ImageConverter;
import entity.Account;
import entity.UserInfo;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

/**
 *
 * @author taleb
 */
public class FXAccount {

    private Account account;

    private final SimpleStringProperty nameProperty = new SimpleStringProperty();
    private final SimpleStringProperty lasNameProperty = new SimpleStringProperty();
    private final SimpleStringProperty usernameProperty = new SimpleStringProperty();
    private final ImageView userImageProperty = new ImageView();

    public FXAccount(Account account) throws IOException {
        this.account = account;
        install();
    }

    private void install() throws IOException {
        UserInfo userInfo = account.getUserInfo();
        usernameProperty.setValue(account.getUsername());
        if (userInfo == null) {
            userInfo = new UserInfo();
            userInfo.setNom("non définit");
            userInfo.setPrenom("non définit");
            userInfo.setImage(null);
        }
        nameProperty.setValue(userInfo.getNom());
        lasNameProperty.setValue(userInfo.getPrenom());
        if (userInfo.getImage() != null) {
            entity.Image imageDB = userInfo.getImage();
            File readFile = ImageConverter.readFile(imageDB.getId(), imageDB.getImage(), imageDB.getExtension());
            Image image = new Image(new FileInputStream(readFile));
            userImageProperty.setImage(image);
            userImageProperty.setFitHeight(50);
            userImageProperty.setPreserveRatio(true);
           
        }
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getNameProperty() {
        return nameProperty.getValue();
    }

    public void setNameProperty(String nameProperty) {
        this.nameProperty.setValue(nameProperty);
    }

    public String getLasNameProperty() {
        return lasNameProperty.getValue();
    }

    public void setLasNameProperty(String lasNameProperty) {
        this.lasNameProperty.setValue(lasNameProperty);
    }

    public String getUsernameProperty() {
        return usernameProperty.getValue();
    }

    public void setUsernameProperty(String usernameProperty) {
        this.usernameProperty.setValue(usernameProperty);
    }

    public ImageView getUserImageProperty() {
        return userImageProperty;
    }

    public void setUserImageProperty(ImageView userImageProperty) {
        this.userImageProperty.setImage(userImageProperty.getImage());
    }

}
