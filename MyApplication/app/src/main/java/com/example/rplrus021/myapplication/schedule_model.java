package com.example.rplrus021.myapplication;

public class schedule_model {
    private String text;
    private String tanggal;
    private String email;
    private String judul;
    public schedule_model(String text, String tanggal,String email,String judul) {
        this.text = text;
        this.tanggal = tanggal;
        this.email = email;
        this.judul = judul;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
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

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }
}
