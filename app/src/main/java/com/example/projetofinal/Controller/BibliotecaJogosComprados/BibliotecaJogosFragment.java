package com.example.projetofinal.Controller.BibliotecaJogosComprados;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.projetofinal.Controller.Loja.LojaHomeScreen;
import com.example.projetofinal.DAO.AmigosDAO;
import com.example.projetofinal.DAO.BibliotecaDAO;
import com.example.projetofinal.Model.Amigos;
import com.example.projetofinal.Model.Biblioteca;
import com.example.projetofinal.R;

import java.util.ArrayList;


public class BibliotecaJogosFragment extends Fragment {
    private ListView listaJogosComprados;
    private ArrayAdapter<String> adaptador;
    private ArrayList<String> arrayNomeJogoComprado;
    ArrayList<Biblioteca> arrayRetorno;
    private TextView msgSemJogos;
    private Button btnComprar;

    public BibliotecaJogosFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View adaptadorView = inflater.inflate(R.layout.fragment_biblioteca_list_jogos_comprados, container, false);
        listaJogosComprados = adaptadorView.findViewById(R.id.listaJogosComprados);
        msgSemJogos = adaptadorView.findViewById(R.id.msgSemJogosComprados);
        btnComprar = adaptadorView.findViewById(R.id.btnLoja);

        arrayRetorno = new ArrayList<Biblioteca>();
        arrayNomeJogoComprado = new ArrayList<String>();
        //instanciando a class DAO
        BibliotecaDAO bibliotecaDAO = new BibliotecaDAO(getContext());
        //chamando o metodo consultar
        arrayRetorno = bibliotecaDAO.consultar();

        int tamanho = arrayRetorno.size();
        int contador = 0;

        while (contador < tamanho) {
            String nick = arrayRetorno.get(contador).getNomeJogoComprado();
            arrayNomeJogoComprado.add(nick);
            contador++;
        }
        adaptador = new ArrayAdapter<String>(getContext(), R.layout.lista_view, android.R.id.text1, arrayNomeJogoComprado);
        if (arrayRetorno.isEmpty()) msgSemJogos.setText("Sem Jogos");
        else listaJogosComprados.setAdapter(adaptador);

        btnComprar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), LojaHomeScreen.class);
                startActivity(intent);
            }
        });


        return adaptadorView;
    }
}