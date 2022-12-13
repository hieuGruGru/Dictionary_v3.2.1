package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;


public class App extends Application {

    private static Scene scene;
    private static Parent parent;

    protected static Theme appTheme = new Theme("7hMode");
    private Image logoImage = GetImage.getImageUrl(App.appTheme.getLogoImage());



    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("login"), 700, 550);
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setResizable(false);
        stage.setTitle("Dictionary_v3.2.1");
        stage.getIcons().add(logoImage);
        stage.show();
    }

    protected static void setScene(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
        loadCSS(appTheme.getThemeName());
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        parent = fxmlLoader.load();
        return parent;
    }

    protected static Parent loadCSS(String css) {
        parent.getStylesheets().clear();
        parent.getStylesheets().add(App.class.getResource(css + ".css").toExternalForm());
        return parent;
    }


    public static void main(String[] args) {
        launch();
    }

}