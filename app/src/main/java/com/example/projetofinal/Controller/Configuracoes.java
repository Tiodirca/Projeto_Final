package com.example.projetofinal.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.projetofinal.Controller.Loja.CadastroLoja;
import com.example.projetofinal.Controller.SmartViewTabListas.SmartViewTabListAmigosListBiblioteca;
import com.example.projetofinal.Controller.Usuario.ListaUsuarios;
import com.example.projetofinal.R;

public class Configuracoes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracoes);
    }
    public void listaDeUsuarios(View view) {
        Intent intent = new Intent(getApplicationContext(), ListaUsuarios.class);
        startActivity(intent);
        finish();
    }
    public void telaCadastrarJogoLoja(View view) {
        Intent intent = new Intent(getApplicationContext(), CadastroLoja.class);
        startActivity(intent);
        finish();
    }
    public void sairDaTelaDeConfiguracos(View view) {
        Intent intent = new Intent(getApplicationContext(), Home_screen.class);
        startActivity(intent);
        finish();
    }
}