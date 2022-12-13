package org.example;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    private double x,y;
    @FXML
    ImageView loginImage;
    @FXML
    TextField notification;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loginImage.setImage(GetImage.getImageUrl(App.appTheme.getLoginImage()));
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
    private void login() throws IOException {
        App.setScene("option");
    }

}
