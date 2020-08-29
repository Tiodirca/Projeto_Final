package com.example.projetofinal.Model;

import java.io.Serializable;

public class Usuario implements Serializable {

    private int id;
    private int senha;
    private String usuario;
    private int idade;
    private String sexo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSenha() {
        return senha;
    }

    public void setSenha(int senha) {
        this.senha = senha;
    }

    public String getUsuario() {
        return usuario;
    }



    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Usuario(int id, int senha, String usuario, int idade, String sexo) {
        this.id = id;
        this.senha = senha;
        this.usuario = usuario;
        this.idade = idade;
        this.sexo = sexo;
    }
    public Usuario() {
    }
}
