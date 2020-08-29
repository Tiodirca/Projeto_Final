package com.example.projetofinal.Model;

import java.io.Serializable;

public class Loja implements Serializable {

    private int id;
    private String nomeJogo;
    private int preco;
    private String descricao;
    private String multiplataforma;
    private String developer;
    private String suporteOnline;
    private String genero;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeJogo() {
        return nomeJogo;
    }

    public void setNomeJogo(String nomeJogo) {
        this.nomeJogo = nomeJogo;
    }

    public int getPreco() {
        return preco;
    }

    public void setPreco(int preco) {
        this.preco = preco;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Loja() {
    }

    public String getMultiplataforma() {
        return multiplataforma;
    }

    public void setMultiplataforma(String multiplataforma) {
        this.multiplataforma = multiplataforma;
    }

    public String getSuporteOnline() {
        return suporteOnline;
    }

    public void setSuporteOnline(String suporteOnline) {
        this.suporteOnline = suporteOnline;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public Loja(int id, String nomeJogo, int preco, String descricao, String multiplataforma, String developer, String suporteOnline, String genero) {
        this.id = id;
        this.nomeJogo = nomeJogo;
        this.preco = preco;
        this.descricao = descricao;
        this.multiplataforma = multiplataforma;
        this.developer = developer;
        this.suporteOnline = suporteOnline;
        this.genero = genero;
    }
}
