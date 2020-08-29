package com.example.projetofinal.Model;

import java.io.Serializable;

public class Games implements Serializable {

    private int id;
    private String nome;
    private String multiplataforma;
    private String genero;
    private String online;
    private String desenvolvedora;
    private Float preco;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMultiplataforma() {
        return multiplataforma;
    }

    public void setMultiplataforma(String multiplataforma) {
        this.multiplataforma = multiplataforma;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getDesenvolvedora() {
        return desenvolvedora;
    }

    public void setDesenvolvedora(String desenvolvedora) {
        this.desenvolvedora = desenvolvedora;
    }

    public Float getPreco() {
        return preco;
    }

    public void setPreco(Float preco) {
        this.preco = preco;
    }

    public String getOnline() {
        return online;
    }

    public void setOnline(String online) {
        this.online = online;
    }

    public Games() {
    }

    public Games(int id, String nome, String multiplataforma, String genero, String onlline, String desenvolvedora, Float preco) {
        this.id = id;
        this.nome = nome;
        this.multiplataforma = multiplataforma;
        this.genero = genero;
        this.online = online;
        this.desenvolvedora = desenvolvedora;
        this.preco = preco;
    }


}
