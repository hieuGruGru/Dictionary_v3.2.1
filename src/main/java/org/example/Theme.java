package org.example;

import javafx.scene.image.Image;

import java.net.URL;

public class Theme {
    private String themeName;
    private String loginImage;
    private String logoImage;
    private String optionImage;

    public Theme(String themeName) {
        this.themeName = themeName;
        this.loginImage = "/org/image/login" + themeName + ".jpg";
        this.logoImage = "/org/image/logo.jpg";
        this.optionImage = "/org/image/option" + themeName + ".jpg";
    }

    public String getThemeName() {
        return themeName;
    }

    public String getLoginImage() {
        return loginImage;
    }

    public String getLogoImage() {
        return logoImage;
    }

    public String getOptionImage() {
        return optionImage;
    }

    public void setTheme(String themeName) {
        this.themeName = themeName;
        this.loginImage = "/org/image/login" + themeName + ".jpg";
        this.optionImage = "/org/image/option" + themeName + ".jpg";
    }

}
