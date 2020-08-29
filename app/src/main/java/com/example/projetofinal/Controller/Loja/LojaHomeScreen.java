package com.example.projetofinal.Controller.Loja;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.projetofinal.Adaptador.AdaptadorLoja;
import com.example.projetofinal.Controller.Home_screen;
import com.example.projetofinal.Controller.Login;
import com.example.projetofinal.DAO.LojaDAO;
import com.example.projetofinal.Model.Loja;
import com.example.projetofinal.R;

import java.util.ArrayList;

public class LojaHomeScreen extends AppCompatActivity {
    private ListView lista;
    private AdaptadorLoja adaptadorLoja;
    private ArrayList<String> arrayNomeAmigo;
    ArrayList<Loja> arrayRetorno;
    private TextView msgSemJogos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loja_home_screen);

        lista = findViewById(R.id.listaJogosLoja);
        msgSemJogos = findViewById(R.id.msgSemJogos);

        LojaDAO lojaDAO = new LojaDAO(getApplicationContext());
        //LojaDAO lojaDAO = new LojaDAO(getApplicationContext());

        arrayRetorno = new ArrayList<Loja>();
        arrayNomeAmigo = new ArrayList<String>();
        //instanciando a class DAO

        //chamando o metodo consultar
        arrayRetorno = lojaDAO.consultar();

        int tamanho = arrayRetorno.size();
        int contador = 0;

        while (contador < tamanho) {
            String nome = arrayRetorno.get(contador).getNomeJogo();
            arrayNomeAmigo.add(nome);
            contador++;
        }

        adaptadorLoja = new AdaptadorLoja(getApplicationContext(), R.layout.lojalist, arrayRetorno);

        if(arrayRetorno.isEmpty()) msgSemJogos.setText("Sem Jogos Cadastrados");
            else lista.setAdapter(adaptadorLoja);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicao, long l) {
                String nome = arrayRetorno.get(posicao).getNomeJogo();
                String genero = arrayRetorno.get(posicao).getGenero();
                String developer = arrayRetorno.get(posicao).getDeveloper();
                String descricao = arrayRetorno.get(posicao).getDescricao();
                int id = arrayRetorno.get(posicao).getId();
                int preco = arrayRetorno.get(posicao).getPreco();
                String suporteOnline = arrayRetorno.get(posicao).getSuporteOnline();
                String multiplataforma =arrayRetorno.get(posicao).getMultiplataforma();

                Loja loja = new Loja();
                loja.setNomeJogo(nome);
                loja.setGenero(genero);
                loja.setDeveloper(developer);
                loja.setDescricao(descricao);
                loja.setId(id);
                loja.setPreco(preco);
                loja.setSuporteOnline(suporteOnline);
                loja.setMultiplataforma(multiplataforma);

                Intent intent = new Intent(getApplicationContext(), DadosLoja.class);
                intent.putExtra("objeto",loja);
                startActivity(intent);
            }
        });
    }
    public void sairDaTelaLoja(View view) {
        Intent intent = new Intent(getApplicationContext(), Home_screen.class);
        startActivity(intent);
        finish();
    }



}