package com.example.projetofinal.Model;

public class Biblioteca {

    private int id;
    private String nomeJogoComprado;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeJogoComprado() {
        return nomeJogoComprado;
    }

    public void setNomeJogoComprado(String nomeJogoComprado) {
        this.nomeJogoComprado = nomeJogoComprado;
    }

    public Biblioteca(int id, String nomeJogoComprado) {
        this.id = id;
        this.nomeJogoComprado = nomeJogoComprado;
    }

    public Biblioteca() {
    }
}
