package com.project.rpr_app;

public class User {
    private String id;
    private String email;
    private String nume;
    private String prenume;
    private String dataNastere;
    private String phone;
    private String judet;
    private String oras;
    private String scoala;
    private String sex;


    public User() {


    }

    public User(String id, String email, String nume, String prenume, String dataNastere,String sex, String phone, String judet, String oras, String scoala) {
        this.id = id;
        this.email = email;
        this.nume = nume;
        this.prenume = prenume;
        this.dataNastere = dataNastere;
        this.phone = phone;
        this.judet = judet;
        this.oras = oras;
        this.scoala = scoala;
        this.sex = sex;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public String getDataNastere() {
        return dataNastere;
    }

    public void setDataNastere(String dataNastere) {
        this.dataNastere = dataNastere;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getJudet() {
        return judet;
    }

    public void setJudet(String judet) {
        this.judet = judet;
    }

    public String getOras() {
        return oras;
    }

    public void setOras(String oras) {
        this.oras = oras;
    }

    public String getScoala() {
        return scoala;
    }

    public void setScoala(String scoala) {
        this.scoala = scoala;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}

