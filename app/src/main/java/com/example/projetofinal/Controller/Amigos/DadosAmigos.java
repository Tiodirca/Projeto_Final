package com.example.projetofinal.Controller.Amigos;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projetofinal.Controller.SmartViewTabListas.SmartViewTabListAmigosListBiblioteca;
import com.example.projetofinal.DAO.AmigosDAO;
import com.example.projetofinal.Model.Amigos;
import com.example.projetofinal.R;

public class DadosAmigos extends AppCompatActivity {

    private TextView nick;
    private TextView email;
    private TextView tel;
    private CheckBox cbPCD;
    private CheckBox cbConsoleD;
    private CheckBox cbMobileD;
    private  Amigos amigos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_amigos);

        nick = findViewById(R.id.resulNick);
        email = findViewById(R.id.resulEmail);
        tel = findViewById(R.id.resulTel);
        cbPCD = findViewById(R.id.cbPCD);
        cbConsoleD = findViewById(R.id.cbConsoleD);
        cbMobileD = findViewById(R.id.cbMobileD);
        //recebendo o objeto

        Bundle recebeObjeto = getIntent().getExtras();
        amigos = (Amigos) recebeObjeto.getSerializable("objeto");

        //steando os valores
        nick.setText(amigos.getNickname());
        email.setText(amigos.getEmail());
        tel.setText(amigos.getTelefone());


        //verificando qual checkbox foi setado e tanando marcado para ele
        //usando i INDEXOF para verificar se existe a String
        if (amigos.getJogaOnde().indexOf("PC") >= 0) cbPCD.setChecked(true);

        if (amigos.getJogaOnde().indexOf("Console") >= 0) cbConsoleD.setChecked(true);

        if (amigos.getJogaOnde().indexOf("Mobile") >= 0) cbMobileD.setChecked(true);
    }
    public void sairDaTelaDadosAmigos(View view) {
        Intent intent = new Intent(getApplicationContext(), SmartViewTabListAmigosListBiblioteca.class);
        startActivity(intent);
        finish();
    }
    public void deletarAmigo(View view){
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setTitle("Confirmação de Exclusão");
        alerta.setMessage("Deseja remover da Lista ?");

        alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                AmigosDAO amigosDAO = new AmigosDAO(getApplicationContext());
                int id = amigos.getId();
                boolean retorno = amigosDAO.deletar(id);

                if(retorno)
                    Toast.makeText(getApplicationContext(),"Excluido com sucesso",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getApplicationContext(),"Erro ao excluir",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), SmartViewTabListAmigosListBiblioteca.class);
                startActivity(intent);
                finish();
            }
        });

        alerta.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        alerta.create();
        alerta.show();
    }
    public void atualizarDados(View view){
        Intent intent = new Intent(getApplicationContext(),AtualizarAmigos.class);
        intent.putExtra("objeto",amigos);
        startActivity(intent);
        finish();
    }
}