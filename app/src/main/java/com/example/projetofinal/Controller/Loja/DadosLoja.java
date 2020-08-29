package com.example.projetofinal.Controller.Loja;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projetofinal.Controller.SmartViewTabListas.SmartViewTabListAmigosListBiblioteca;
import com.example.projetofinal.DAO.BibliotecaDAO;
import com.example.projetofinal.DAO.LojaDAO;
import com.example.projetofinal.DAO.UsuarioDAO;
import com.example.projetofinal.Model.Biblioteca;
import com.example.projetofinal.Model.Games;
import com.example.projetofinal.Model.Loja;
import com.example.projetofinal.Model.Usuario;
import com.example.projetofinal.R;

import java.util.ArrayList;

public class DadosLoja extends AppCompatActivity {

    private Loja loja;
    private TextView nome;
    private TextView genero;
    private TextView developer;
    private TextView descricao;
    private TextView preco;
    private CheckBox multiplataforma;
    private CheckBox suporteOnline;
    ArrayList<Loja> arrayRetorno;
    ArrayList<Biblioteca> arrayRetornoJogoComprado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_loja);

        nome = findViewById(R.id.resulNomeJogo);
        genero = findViewById(R.id.resulGeneroJogo);
        developer = findViewById(R.id.resulDeveloperJogo);
        descricao = findViewById(R.id.resulDescricaoJogo);
        preco = findViewById(R.id.resulPrecoJogo);
        multiplataforma = findViewById(R.id.cbMultiPlataforma);
        suporteOnline = findViewById(R.id.cbSuporteOnline);

        Bundle recebeObjeto = getIntent().getExtras();
        loja = (Loja) recebeObjeto.getSerializable("objeto");

        nome.setText(loja.getNomeJogo());
        genero.setText(loja.getGenero());
        developer.setText(loja.getDeveloper());
        descricao.setText(loja.getDescricao());
        preco.setText("R$: " + String.valueOf(loja.getPreco()));

        if (loja.getMultiplataforma().equals("SIM")) multiplataforma.setChecked(true);
        if (loja.getSuporteOnline().equals("SIM")) suporteOnline.setChecked(true);

    }

    public void sairTelaDadosLoja(View view) {
        Intent intent = new Intent(getApplicationContext(), LojaHomeScreen.class);
        startActivity(intent);
        finish();
    }

    public void comprarJogo(View view) {
        //instanciando a classe
        Biblioteca biblioteca = new Biblioteca();
        //instanciando a classe DAO
        BibliotecaDAO bibliotecaDAO = new BibliotecaDAO(getApplicationContext());
        //setando o valor para a biblioteca
        biblioteca.setNomeJogoComprado(loja.getNomeJogo());
        //chamando o metodo inserir da biblioteca
        bibliotecaDAO.inserir(biblioteca);
        //trocando de tela
        Intent intent = new Intent(getApplicationContext(), SmartViewTabListAmigosListBiblioteca.class);
        startActivity(intent);

    }
}