package org.example;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class PrimaryController implements Initializable {
    @FXML
    ListView<String> listView = new ListView<>();
    @FXML
    TextField searchText;
    @FXML
    TextField firstTextField;
    @FXML
    TextField secondTextField1;
    @FXML
    TextField secondTextField2;
    @FXML
    TextField statusText;
    @FXML
    Label firstLanguage;
    @FXML
    Label secondLanguage;
    @FXML
    ImageView statusImage = new ImageView();
    @FXML
    Button yes;
    @FXML
    Button no;

    private double x, y;
    private int allowDel;
    private String defaultImage = GetImage.getImagePath("main", 1, App.appTheme.getThemeName());
    String inputPath = "";
    String outputPath = "";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        allowDel = -1;
        if (App.languageMode == 1) {
            inputPath = "/org/example/EV-3900-" + App.sourceMode + ".txt";
            outputPath = "D:\\java\\Dictionary\\Dictionary_v3.2.1\\target\\classes" +
                    "\\org\\example\\EV-3900-" + (3 - App.sourceMode) + ".txt";
            firstLanguage.setText("Tiếng Việt");
            secondLanguage.setText("Tiếng Anh");
        } else {
            inputPath = "/org/example/VE-3900-" + App.sourceMode + ".txt";
            outputPath = "D:\\java\\Dictionary\\Dictionary_v3.2.1\\target\\classes" +
                    "\\org\\example\\VE-3900-"  + (3 - App.sourceMode) + ".txt";
            firstLanguage.setText("Tiếng Anh");
            secondLanguage.setText("Tiếng Việt");
        }
        statusImage.setImage(GetImage.getImageUrl(defaultImage));
        InputStream is = getClass().getResourceAsStream(inputPath);
        DictionaryMgmt.init(is);
        reloadList();
    }

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
        if (App.sourceMode == 1 ) {
            App.sourceMode = 2;
        } else {
            App.sourceMode = 1;
        }
    }

    @FXML
    public void getMeaning(MouseEvent event) {
        String word = listView.getSelectionModel().getSelectedItem();
        String meaning1 = DictionaryMgmt.search(word).getMeaning1();
        String meaning2 = DictionaryMgmt.search(word).getMeaning2();
        firstTextField.setText(word);
        secondTextField1.setText(meaning1);
        secondTextField2.setText(meaning2);
    }

    @FXML
    private void search() {
        String word = searchText.getText().trim();
        if (word.isEmpty()) {
            reloadList();
        } else {
            Dictionary dict2 = new Dictionary();
            dict2.trie.root = DictionaryMgmt.search(word);
            listView.getItems().clear();
            dict2.list.clear();
            DictionaryMgmt.loadToList(dict2.trie.root, dict2.list);
            for (int i = 0; i  < dict2.list.size(); i ++ ) {
                String word1 = dict2.list.get(i).getTarget();
                listView.getItems().add(word1);
            }
        }
    }

    @FXML
    private void addWord(ActionEvent event) {
        statusText.setVisible(true);
        String imagePath = defaultImage;
        String word = firstTextField.getText().trim();
        String meaning1 = secondTextField1.getText().trim();
        String meaning2 = secondTextField2.getText().trim();
        if (!DictionaryMgmt.validateString(word) || !DictionaryMgmt.validateString(meaning1)) {
            statusText.setText("Định lừa ai???");
            imagePath = GetImage.getImagePath("add", 1, App.appTheme.getThemeName());
        } else {
            if (!DictionaryMgmt.isWord(word)) {
                DictionaryMgmt.insert(word, meaning1, meaning2);
                statusText.setText(word + " | " + meaning1 + " | " + meaning2);
                imagePath = GetImage.getImagePath("add", 2, App.appTheme.getThemeName());
            } else {
                imagePath = GetImage.getImagePath("add", 0, App.appTheme.getThemeName());
            }
        }
        statusImage.setImage(GetImage.getImageUrl(imagePath));
        reloadList();
    }

    @FXML
    public void updateWord(ActionEvent event) throws IllegalAccessException {
        statusText.setVisible(true);
        String imagePath = defaultImage;
        String word = listView.getSelectionModel().getSelectedItem();
        if (word == null) {
            statusText.setText("Hãy chọn từ để sửa");
        } else {
            String meaning1 = secondTextField1.getText();
            String meaning2 = secondTextField2.getText();
            String currentMeaning1 = DictionaryMgmt.search(word).getMeaning1();
            String currentMeaning2 = DictionaryMgmt.search(word).getMeaning2();
            if (meaning1.equals(currentMeaning1)) {
                if (!meaning2.equals(currentMeaning2) && !meaning2.equals(currentMeaning1)) {
                    DictionaryMgmt.insert(word, meaning1, meaning2);
                    if (currentMeaning2.equals("")) {
                        imagePath = GetImage.getImagePath("update", 2, App.appTheme.getThemeName());
                        statusText.setText("Đã thêm nghĩa " + meaning2);
                    } else {
                        imagePath = GetImage.getImagePath("update", 2, App.appTheme.getThemeName());
                        statusText.setText("Đã sửa nghĩa " + currentMeaning2 + " thành " + meaning2);
                    }
                } else {
                    if (meaning2.equals(currentMeaning2)) {
                        imagePath = GetImage.getImagePath("update", 1, App.appTheme.getThemeName());
                        statusText.setText("Rồi khác gì 3");
                    } else {
                        imagePath = GetImage.getImagePath("update", 0, App.appTheme.getThemeName());
                        secondTextField2.setText(currentMeaning2);
                        statusText.setText("Nghĩa thứ 2 phải khác nghĩa đầu tiên");
                    }
                }
            } else {
                secondTextField1.setText(currentMeaning1);
                statusText.setText("Chỉ có thể chỉnh sửa nghĩa thứ 2");
            }
        }
        statusImage.setImage(GetImage.getImageUrl(imagePath));
    }

    @FXML
    private void deleteWord(ActionEvent event) throws IllegalAccessException {
        String imagePath = "";
        String word = listView.getSelectionModel().getSelectedItem().trim();
        int index = listView.getSelectionModel().getSelectedIndex();
        if (allowDel == 1) {
            imagePath = GetImage.getImagePath("delete", 1, App.appTheme.getThemeName());
            DictionaryMgmt.delete(word);
            listView.getItems().remove(index);
        } else {
            if (allowDel == 0) {
                imagePath = GetImage.getImagePath("delete", 2, App.appTheme.getThemeName());
            } else {
                imagePath = GetImage.getImagePath("delete", 0, App.appTheme.getThemeName());;
            }
        }
        allowDel = -1;
        statusImage.setImage(GetImage.getImageUrl(imagePath));
        reloadList();
    }

    @FXML
    private void speak() {
        String imagePath = GetImage.getImagePath("speak", 1, App.appTheme.getThemeName());
        statusImage.setImage(GetImage.getImageUrl(imagePath));
        String textPronounce1 = listView.getSelectionModel().getSelectedItem();
        Audio.Text_Speech(textPronounce1);
    }

    @FXML
    private void saveFile(ActionEvent event) throws IOException {
        String imagePath = GetImage.getImagePath("save", 1, App.appTheme.getThemeName());
        statusImage.setImage(GetImage.getImageUrl(imagePath));
        DictionaryMgmt.export(outputPath);
    }

    private void reloadList() {
        listView.getItems().clear();
        DictionaryMgmt.dict1.list.clear();
        DictionaryMgmt.loadToList(DictionaryMgmt.dict1.trie.root, DictionaryMgmt.dict1.list);
        for (int i = 0; i  < DictionaryMgmt.dict1.list.size(); i ++ ) {
            String word = DictionaryMgmt.dict1.list.get(i).getTarget();
            listView.getItems().add(word);
        }
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
        searchText.clear();
        firstTextField.clear();
        secondTextField1.clear();
        secondTextField2.clear();
        statusText.setVisible(false);
        statusText.clear();
    }

    @FXML
    private void clearStatus() {
        statusText.setVisible(false);
        statusText.clear();
    }
}
