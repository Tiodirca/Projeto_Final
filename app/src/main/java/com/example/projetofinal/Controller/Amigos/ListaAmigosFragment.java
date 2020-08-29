package com.example.projetofinal.Controller.Amigos;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projetofinal.DAO.AmigosDAO;
import com.example.projetofinal.Model.Amigos;
import com.example.projetofinal.R;

import java.util.ArrayList;

public class ListaAmigosFragment extends Fragment {

    private ListView listAmigos;
    private ArrayAdapter<String> adaptador;
    private ArrayList<String> arrayNomeAmigo;
    ArrayList<Amigos> arrayRetorno;
    private TextView msgSemAmigos;
    private Button btnCad;

    public ListaAmigosFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //refenciando o componete com a variavel
        View adaptadorView = inflater.inflate(R.layout.fragment_list_amigos, container, false);
        listAmigos = adaptadorView.findViewById(R.id.listaAmigosFragment);
        btnCad = adaptadorView.findViewById(R.id.btnCadAmigo);
        msgSemAmigos = adaptadorView.findViewById(R.id.msgSemAmigos);

        arrayRetorno = new ArrayList<Amigos>();
        arrayNomeAmigo = new ArrayList<String>();
        //instanciando a class DAO
        AmigosDAO amigosDAO = new AmigosDAO(getContext());
        //chamando o metodo consultar
        arrayRetorno = amigosDAO.consultar();

        int tamanho = arrayRetorno.size();
        int contador = 0;

        while (contador<tamanho){
            String nick = arrayRetorno.get(contador).getNickname();
            arrayNomeAmigo.add(nick);
            contador++;
        }
        adaptador = new ArrayAdapter<String>(getContext(),R.layout.lista_view,android.R.id.text1,arrayNomeAmigo);
        if(arrayRetorno.isEmpty()) msgSemAmigos.setText("Sem Amigos Cadastrados");
        else listAmigos.setAdapter(adaptador);

        //montando usuario
        listAmigos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicao, long l) {
                int idAmigo = arrayRetorno.get(posicao).getId();
                String nick = arrayRetorno.get(posicao).getNickname();
                String tel = arrayRetorno.get(posicao).getTelefone();
                String email = arrayRetorno.get(posicao).getEmail();
                String ondeJoga = arrayRetorno.get(posicao).getJogaOnde();

                Amigos amigos = new Amigos();
                amigos.setId(idAmigo);
                amigos.setNickname(nick);
                amigos.setTelefone(tel);
                amigos.setEmail(email);
                amigos.setJogaOnde(ondeJoga);

                Intent intent = new Intent(getContext(), DadosAmigos.class);
                intent.putExtra("objeto",amigos);
                startActivity(intent);
            }
        });

        btnCad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), CadastroAmigos.class);
                startActivity(intent);
            }
        });
        return adaptadorView;
    }
}