package com.thanhclub.mvp_demo_listusergithub.model.fetch_user;

/**
 * Created by MyPC on 16/01/2018.
 */

public class User {
    private String urlImage;
    private String idUser;
    private String nameUser;

    public User(String urlImage, String idUser, String nameUser) {
        this.urlImage = urlImage;
        this.idUser = idUser;
        this.nameUser = nameUser;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public String getIdUser() {
        return idUser;
    }

    public String getNameUser() {
        return nameUser;
    }
}
