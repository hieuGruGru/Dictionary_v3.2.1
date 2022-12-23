package org.example;

import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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
    TextField usernameField = new TextField();
    @FXML
    TextField notification = new TextField();
    @FXML
    PasswordField passwordField;

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
        String username = usernameField.getText();
        String password = passwordField.getText();
        int flag = ConnectToSQLServer.login(username, password);
        if (flag == 2) {
            App.setScene("option");
        } else {
            if (flag == 1) {
                notification.setText("Mật khẩu không chính xác");
            } else {
                notification.setText("Tên đăng nhập không chính xác");
            }
        }
        usernameField.clear();
        passwordField.clear();
    }
}
