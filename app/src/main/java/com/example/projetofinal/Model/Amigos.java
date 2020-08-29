package com.example.projetofinal.Model;

import java.io.Serializable;

public class Amigos implements Serializable {

    private int id;
    private String nickname;
    private String jogaOnde;
    private String telefone;
    private String email;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getJogaOnde() {
        return jogaOnde;
    }

    public void setJogaOnde(String jogaOnde) {
        this.jogaOnde = jogaOnde;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Amigos() {
    }



    public Amigos(int id, String nickname, String jogaOnde, String telefone, String email) {
        this.id = id;
        this.nickname = nickname;
        this.jogaOnde = jogaOnde;
        this.telefone = telefone;
        this.email = email;
    }
}
