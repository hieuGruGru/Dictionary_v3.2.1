package org.example;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class PrimaryController implements Initializable {

    private double x, y;
    private int addFlag;
    private int allowDel;
    private String defaultImage = GetImage.getImagePath
            ("main", 1, App.appTheme.getThemeName());

    private String[] word = {"aaaaaa", "bbbbbb", "cc", "deeee", "aaaaaa", "bbbbbb", "cc", "deeee",
            "aaaaaa", "bbbbbb", "cc", "deeee", "aaaaaa", "bbbbbb", "cc", "deeee",
            "aaaaaa", "bbbbbb", "cc", "deeee", "aaaaaa", "bbbbbb", "cc", "deeee",
            "aaaaaa", "bbbbbb", "cc", "deeee", "aaaaaa", "bbbbbb", "cc", "deeee",
            "aaaaaa", "bbbbbb", "cc", "deeee"};

    @FXML
    private ListView<String> list = new ListView<>();
    @FXML
    private TextField secondLang;
    @FXML
    ImageView statusImage = new ImageView();
    @FXML
    Button yes;
    @FXML
    Button no;
    @FXML
    private void dragged(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
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
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void minimize(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    private void Setting() throws IOException {
        App.setScene("option");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addFlag = 0;
        allowDel = -1;
        list.getItems().addAll(word);
        statusImage.setImage(GetImage.getImageUrl(defaultImage));
    }

    @FXML
    private void saveFile(ActionEvent event) {
        String imagePath = GetImage.getImagePath("save", 1, App.appTheme.getThemeName());
        statusImage.setImage(GetImage.getImageUrl(imagePath));
    }

    @FXML
    private void addWord(ActionEvent event) {
        String imagePath = GetImage.getImagePath("add", addFlag, App.appTheme.getThemeName());
        if (addFlag == 0) {
            statusImage.setImage(GetImage.getImageUrl(imagePath));
            addFlag ++;
        } else {
            statusImage.setImage(GetImage.getImageUrl(imagePath));
        }
    }

    @FXML
    private void deleteWord(ActionEvent event) {
        String imagePath = "";
        if (allowDel == 1) {
            imagePath = GetImage.getImagePath("delete", 1, App.appTheme.getThemeName());
        } else {
            if (allowDel == 0) {
                imagePath = GetImage.getImagePath("delete", 2, App.appTheme.getThemeName());
            } else {
                imagePath = GetImage.getImagePath("delete", 0, App.appTheme.getThemeName());;
            }
        }
        allowDel = -1;
        statusImage.setImage(GetImage.getImageUrl(imagePath));
    }

    @FXML
    private void speak() {
        String imagePath = GetImage.getImagePath("speak", 1, App.appTheme.getThemeName());
        statusImage.setImage(GetImage.getImageUrl(imagePath));
    }

    @FXML
    private void allowDel() throws IOException {
        allowDel = 1;
    }

    @FXML
    private void deniedDel() throws IOException {
        allowDel = 0;
    }

    @FXML
    private void clearSearchText() {
        statusImage.setImage(GetImage.getImageUrl(defaultImage));
    }


}
