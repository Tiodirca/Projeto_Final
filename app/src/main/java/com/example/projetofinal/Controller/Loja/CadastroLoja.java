package com.example.projetofinal.Controller.Loja;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projetofinal.Controller.Configuracoes;
import com.example.projetofinal.Controller.Home_screen;
import com.example.projetofinal.Controller.SmartViewTabListas.SmartViewTabListAmigosListBiblioteca;
import com.example.projetofinal.DAO.LojaDAO;
import com.example.projetofinal.Model.Loja;
import com.example.projetofinal.R;

public class CadastroLoja extends AppCompatActivity {

    private EditText nomeJogo;
    private EditText generoJogo;
    private EditText developerJogo;
    private EditText descricaoJogo;
    private EditText precoJogo;
    private Switch multiplataforma;
    private Switch suporteOnline;
    private TextView resultadoMultiPlataforma;
    private TextView resultadoOnline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_loja);
        multiplataforma = findViewById(R.id.switchMultiplataforma);
        suporteOnline = findViewById(R.id.switchSuporteOnline);
        resultadoMultiPlataforma = findViewById(R.id.resulMultiplataforma);
        resultadoOnline = findViewById(R.id.resulSuporteOnline);

        multiplataforma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (multiplataforma.isChecked())
                    resultadoMultiPlataforma.setText("SIM");
                else resultadoMultiPlataforma.setText("NAO");
            }
        });
        suporteOnline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (suporteOnline.isChecked())
                    resultadoOnline.setText("SIM");
                else resultadoOnline.setText("NAO");
            }
        });
    }

    public void cadastroJogosLoja(View view) {
        nomeJogo = findViewById(R.id.editCadNomeJogo);
        precoJogo = findViewById(R.id.editCadPrecoJogo);
        generoJogo = findViewById(R.id.ediCadGeneroJogo);
        developerJogo = findViewById(R.id.editCadDeveloperJogo);
        descricaoJogo = findViewById(R.id.editCadDescricaoJogo);
        multiplataforma = findViewById(R.id.switchMultiplataforma);
        suporteOnline = findViewById(R.id.switchSuporteOnline);

        Loja loja = new Loja();

        String nome = nomeJogo.getText().toString();
        String genero = generoJogo.getText().toString();
        String developer = developerJogo.getText().toString();
        String descricao = descricaoJogo.getText().toString();

        loja.setNomeJogo(nome);
        loja.setGenero(genero);
        loja.setDescricao(descricao);
        loja.setDeveloper(developer);


        if (multiplataforma.isChecked())
            loja.setMultiplataforma("SIM");
        else
            loja.setMultiplataforma("NAO");

        if (suporteOnline.isChecked()) {
            loja.setSuporteOnline("SIM");
        } else {
            resultadoOnline.setText("NAO");
            loja.setSuporteOnline("NAO");

        }
        LojaDAO lojaDAO = new LojaDAO(getApplicationContext());
        if (verificaCampovazio(nomeJogo) || verificaCampovazio(generoJogo) || verificaCampovazio(precoJogo)
                || verificaCampovazio(developerJogo) || verificaCampovazio(descricaoJogo)) {
            nomeJogo.setHintTextColor(Color.parseColor("#9400d3"));//violeta
            generoJogo.setHintTextColor(Color.parseColor("#9400d3"));//violeta
            precoJogo.setHintTextColor(Color.parseColor("#9400d3"));//violeta
            developerJogo.setHintTextColor(Color.parseColor("#9400d3"));//violeta
            descricaoJogo.setHintTextColor(Color.parseColor("#9400d3"));//violeta

        } else {
            int preco = Integer.parseInt(precoJogo.getText().toString());
            loja.setPreco(preco);
            boolean retorno = lojaDAO.inserir(loja);
            //Toast de Aviso de inserção
            if (retorno)
                Toast.makeText(getApplicationContext(), "Jogo inserido com sucesso", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(getApplicationContext(), "Erro ao inserir jogo", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), Home_screen.class);
            startActivity(intent);
            finish();
        }
    }

    public void sairTelaCadastroJogosLoja(View view) {
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setTitle("Confirmação de cancelamento");
        alerta.setMessage("Deseja cancelar o cadastro ?");
        //se a resposta for sim volta para a tela anterior
        alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //chamando outra tela
                Intent intent = new Intent(getApplicationContext(), Configuracoes.class);
                startActivity(intent);
                finish();
            }
        });
        //se a resposta for essa nao faz nada
        alerta.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        alerta.create();
        alerta.show();
    }

    private boolean verificaCampovazio(EditText valor) {

        Boolean resultado = (TextUtils.isEmpty(valor.getText().toString()));
        if (resultado) {
            Toast.makeText(getApplicationContext(), "Campos Vazios", Toast.LENGTH_SHORT).show();//aviso erro
        }
        return resultado;
    }
}