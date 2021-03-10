package com.project.rpr_app;

public class Profesor {

    private String id, nume, scoala, materie;

    public Profesor()
    {

    }

    public Profesor(String id, String nume, String scoala, String materie) {
        this.id = id;
        this.nume = nume;
        this.scoala = scoala;
        this.materie = materie;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getScoala() {
        return scoala;
    }

    public void setScoala(String scoala) {
        this.scoala = scoala;
    }

    public String getMaterie() {
        return materie;
    }

    public void setMaterie(String materie) {
        this.materie = materie;
    }
}
