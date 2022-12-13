package org.example;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class OptionController implements Initializable {


    private double x,y;
    protected String statusMode;
    private int q;
    private String optionImage;
    private String qqqImage;
    @FXML
    Text check;
    @FXML
    Pane contactPane;
    @FXML
    ImageView optionImageView1;
    @FXML
    ImageView optionImageView2;
    @FXML
    FontAwesomeIconView infoButton;
    @FXML
    Button qqqButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        statusMode = "00";
        q = 0;//tt10
        optionImage = GetImage.getImagePath
                ("option", 1, App.appTheme.getThemeName());
        qqqImage = GetImage.getImagePath
                ("Q1", q, App.appTheme.getThemeName());
        optionImageView1.setImage(GetImage.getImageUrl(optionImage));
        optionImageView1.setVisible(true);
        optionImageView2.setVisible(false);
        qqqButton.setVisible(true);
        infoButton.setVisible(false);
        contactPane.toBack();
        contactPane.setVisible(false);
    }

    @FXML
    private void dragged(MouseEvent event) {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setX(event.getScreenX() - x);
        stage.setY(event.getScreenY() - y);
    }
    @FXML
    private void pressed(MouseEvent event) {
        x = event.getSceneX();
        y = event.getSceneY();
    }
    @FXML
    private void close(MouseEvent event) {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }
    @FXML
    private void minimize(MouseEvent event) {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }
    @FXML
    private void logOut() throws IOException {
        App.setScene("login");
    }
    @FXML
    private void loadMainScene() throws IOException {
        App.setScene("main");
    }
    @FXML
    private void switchTo7Mode() {
        App.loadCSS("7hMode");
        App.appTheme.setTheme("7hMode");
        statusMode = "01";

    }
    @FXML
    private void switchTo12Mode() {
        App.loadCSS("12hMode");
        App.appTheme.setTheme("12hMode");
        statusMode = "01";

    }
    @FXML
    private void switchTo19Mode() {
        App.loadCSS("19hMode");
        App.appTheme.setTheme("19hMode");
        statusMode = "01";
    }
    @FXML
    private void switchTo24Mode() {
        App.appTheme.setTheme("24hMode");
        App.loadCSS("24hMode");
        statusMode = "01";
    }

    @FXML
    private void qqqtrox(ActionEvent event) {
        q++;
        if (q < 4) {//tt10, thay 4 ảnh
            statusMode = "10";
        } else {//tt11
            statusMode = "11";
        }
    }

    @FXML
    private void showInfo() {
        statusMode = "12";
    }

    @FXML
    private void statusHandle() {
        if (statusMode == "01") {
            q = 0;
            optionImageView1.setVisible(true);
            optionImageView2.setVisible(false);
            qqqButton.setVisible(true);
            infoButton.setVisible(false);
            contactPane.toBack();
            contactPane.setVisible(false);
            optionImage = GetImage.getImagePath
                    ("option", 1, App.appTheme.getThemeName());
            optionImageView1.setImage(GetImage.getImageUrl(optionImage));
        }
        if (statusMode == "10") {
            optionImageView1.setVisible(false);
            optionImageView2.setVisible(true);
            qqqImage = GetImage.getImagePath
                    ("Q1", q, App.appTheme.getThemeName());
            optionImageView2.setImage(GetImage.getImageUrl(qqqImage));
        }
        if (statusMode == "11") {
            qqqButton.setVisible(false);
            infoButton.setVisible(true);
            qqqImage = GetImage.getImagePath
                    ("Q1", q, App.appTheme.getThemeName());
            optionImageView2.setImage(GetImage.getImageUrl(qqqImage));
        }
        if (statusMode == "12") {
            contactPane.toFront();
            contactPane.setVisible(true);
        }
    }
}
