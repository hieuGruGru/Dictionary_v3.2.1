package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class OptionController implements Initializable {


    private double x,y;
    int q1 = 1;


    @FXML
    ImageView optionImage;
    @FXML
    ImageView optionImage2;
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
        App.appTheme.setTheme("7hMode");
        optionImage.setImage(GetImage.getImageUrl(App.appTheme.getOptionImage()));
        App.loadCSS("7hMode");
    }
    @FXML
    private void switchTo12Mode() {
        App.appTheme.setTheme("12hMode");
        optionImage.setImage(GetImage.getImageUrl(App.appTheme.getOptionImage()));
        App.loadCSS("12hMode");
        System.out.println("");

    }
    @FXML
    private void switchTo19Mode() {
        App.appTheme.setTheme("19hMode");
        optionImage.setImage(GetImage.getImageUrl(App.appTheme.getOptionImage()));
        App.loadCSS("19hMode");

    }
    @FXML
    private void switchTo24Mode() {
        App.appTheme.setTheme("24hMode");
        optionImage.setImage(GetImage.getImageUrl(App.appTheme.getOptionImage()));
        App.loadCSS("24hMode");

    }

    @FXML
    private void showInfo(ActionEvent event) {
        String action = "Q1";
        String imagePath = GetImage.getImagePath(action, q1, App.appTheme.getThemeName());
        optionImage2.setImage(GetImage.getImageUrl(imagePath));
        if (q1 > 3) {
            optionImage.setImage((GetImage.getImageUrl(imagePath)));
            optionImage2.setImage((GetImage.getImageUrl("")));
            q1 = 1;
        } else {
            q1++;
        }


    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        optionImage.setImage(GetImage.getImageUrl(App.appTheme.getOptionImage()));
    }

}
