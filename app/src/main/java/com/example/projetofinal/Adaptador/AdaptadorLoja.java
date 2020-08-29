package com.example.projetofinal.Adaptador;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.projetofinal.Controller.Home_screen;
import com.example.projetofinal.Controller.Loja.LojaHomeScreen;
import com.example.projetofinal.Model.Loja;
import com.example.projetofinal.R;

import java.util.ArrayList;

public class AdaptadorLoja extends ArrayAdapter<Loja> {

    private Context context;
    private int resource;
    private ArrayList<Loja> listaLoja;
    private TextView nomeJogoCard;
    private TextView precoJogoCar;
    private TextView desenvolvedora;


    public AdaptadorLoja(@NonNull Context context, int resource, ArrayList<Loja> listaLoja) {
        super(context, resource, listaLoja);
        this.context = context;
        this.resource = resource;
        this.listaLoja = listaLoja;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(this.context).inflate(R.layout.lojalist, null);

        nomeJogoCard = convertView.findViewById(R.id.nomeJogoLoja1);
        precoJogoCar = convertView.findViewById(R.id.precoJogoLoja1);
        desenvolvedora = convertView.findViewById(R.id.desenvolvedoraGame);

            nomeJogoCard.setText(listaLoja.get(position).getNomeJogo());
            precoJogoCar.setText("R$: " + String.valueOf(listaLoja.get(position).getPreco()));
            desenvolvedora.setText(listaLoja.get(position).getDescricao());
        return convertView;
    }

    private boolean verificaCampovazio(TextView valor) {

        Boolean resultado = (TextUtils.isEmpty(valor.getText().toString()));
        if (resultado) {
            Toast.makeText(getContext(), "Campos Vazios", Toast.LENGTH_SHORT).show();//aviso erro
        }
        return resultado;
    }
}
