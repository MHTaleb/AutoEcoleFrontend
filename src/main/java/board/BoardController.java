/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package board;

import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXDrawer;
import com.mycompany.fxutilities.Frame;
import com.mycompany.fxutilities.ImageConverter;
import com.mycompany.fxutilities.TalcorpDevTool;
import com.talcorpdz.autoecole.usermanagement.dao.AccountMetier;
import com.talcorpdz.autoecole.usermanagement.dao.AccountMetierLocal;
import entity.Account;
import entity.Image;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import scope.Scope;

/**
 * FXML Controller class
 *
 * @author taleb
 */
public class BoardController implements Initializable {

    @FXML
    private Label schoolName;
    @FXML
    private JFXDrawer drawerMenu;
    @FXML
    private AnchorPane root;

    public static AnchorPane mainRoot;

    private VBox drawerContent;
    @FXML
    private StackPane rootStackPane;
    @FXML
    private AnchorPane myWindow;
    
    public static StackPane myStackPAne;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            mainRoot = root;
            myStackPAne = rootStackPane;
            drawerContent = (VBox) FXMLLoader.load(getClass().getResource("/fxml/Menu.fxml"));
            drawerMenu.setSidePane(drawerContent);
            Frame.autoSize();
            drawerMenu.setOnDrawerOpened((handler)->{
                Frame.autoSize();
            });
            drawerMenu.setOnDrawerClosed((handler)->{
                Frame.autoSize();
            });
        } catch (IOException ex) {
            Logger.getLogger(BoardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void showDrawer(MouseEvent event) {
        if (drawerMenu.isShown()) {
            drawerMenu.close();
            drawerMenu.setPrefWidth(0);
        } else {
            drawerMenu.open();
            drawerMenu.setPrefWidth(202);
        }
    }

    @FXML
    private void showAbout(MouseEvent event) {
        JFXDialogLayout content = new JFXDialogLayout();
        JFXDialog dialog = new JFXDialog(rootStackPane, content, JFXDialog.DialogTransition.CENTER);

        content.setHeading(new Text("Taleb Mohammed Housseyn"));
        content.setBody(new Text("Adresse: Batiment des enseignents, logement °5, Maghnia. Tlemcen.\n\nTel: 0778217469. \n\nEmail: talcorpdz@gmail.com"));
        content.setStyle("-fx-background-color : #fff;");
        dialog.show(rootStackPane);
       
    }

}
