package org.example;

import javafx.scene.image.Image;

import java.net.URL;

public class GetImage {

    public static String getImagePath(String action, int flag, String themeName) {
        return "/org/image/" + action +"/" + action + "-" + flag + "-" + themeName + ".jpg";
    }

    public static Image getImageUrl(String imagePath) {
        URL imageUrl = GetImage.class.getResource(imagePath);
        Image image = new Image(imageUrl.toExternalForm());
        return image;
    }

}
