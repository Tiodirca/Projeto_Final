package com.example.projetofinal.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

import com.example.projetofinal.Controller.SmartViewTabListas.SmartViewTabListAmigosListBiblioteca;
import com.example.projetofinal.Controller.Loja.LojaHomeScreen;
import com.example.projetofinal.Controller.Usuario.ListaUsuarios;
import com.example.projetofinal.R;

public class Home_screen extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
    }

    public void listaAmigosBiblioteca(View view) {
        Intent intent = new Intent(getApplicationContext(), SmartViewTabListAmigosListBiblioteca.class);
        startActivity(intent);
        finish();
    }

    public void telaLoja(View view) {
        Intent intent = new Intent(getApplicationContext(), LojaHomeScreen.class);
        startActivity(intent);
        finish();
    }
    public void telaListaCadJogosListaUsuarios(View view) {
        Intent intent = new Intent(getApplicationContext(), Configuracoes.class);
       startActivity(intent);
        finish();
    }
    public void sairDaHome(View view) {
        Intent intent = new Intent(getApplicationContext(), Login.class);
        startActivity(intent);
        finish();
    }

}