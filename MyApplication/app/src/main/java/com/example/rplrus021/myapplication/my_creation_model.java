package com.example.rplrus021.myapplication;

public class my_creation_model {
    private String name;
    private String text;
    private String email;
    private String image;
    private String judul;
    public my_creation_model(){

    }

    public my_creation_model(String email, String name,String text,String image,String judul){
        this.email = email;
        this.name = name;
        this.text = text;
        this.image = image;
        this.setJudul(judul);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }
}
