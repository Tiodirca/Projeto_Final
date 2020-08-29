package com.example.projetofinal.Controller.Amigos;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.projetofinal.Controller.SmartViewTabListas.SmartViewTabListAmigosListBiblioteca;
import com.example.projetofinal.DAO.AmigosDAO;
import com.example.projetofinal.Model.Amigos;
import com.example.projetofinal.R;

public class CadastroAmigos extends AppCompatActivity {

    private EditText nick;
    private EditText emailA;
    private EditText telefone;
    private CheckBox PC;
    private CheckBox console;
    private CheckBox mobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_amigos);
    }

    public void cadastrarAmigo(View view) {
        //referenciando as variaveis com os componentes
        nick = findViewById(R.id.editNickName);
        telefone = findViewById(R.id.editTelefone);
        PC = findViewById(R.id.cbPc);
        console = findViewById(R.id.cbConsole);
        mobile = findViewById(R.id.cbMobile);
        emailA = findViewById(R.id.editEmail);
        //pegando so valores digitados
        String nickname = nick.getText().toString();
        String email = emailA.getText().toString();
        String tel = telefone.getText().toString();

        //instanciando a class
        Amigos amigos = new Amigos();

        //setando os valores
        amigos.setNickname(nickname);
        amigos.setEmail(email);
        amigos.setTelefone(tel);

        //criando uma string para setar valor acaso o checkbox for selecionado
        String cbselecionado = "";

        if (PC.isChecked()) {
            cbselecionado += "PC";
        }
        if (console.isChecked()) {
            cbselecionado += "Console";
        }
        if (mobile.isChecked()) {
            cbselecionado += "Mobile";
        }
        //setando o valor da string
        amigos.setJogaOnde(cbselecionado);

        AmigosDAO amigosDAO = new AmigosDAO(getApplicationContext());
        //verificando se os campos estao vazios
        if (verificaCampovazio(nick) || verificaCampovazio(telefone) || verificaCampovazio(emailA)) {
            nick.setHintTextColor(Color.parseColor("#9400d3"));//violeta
            telefone.setHintTextColor(Color.parseColor("#9400d3"));//violeta
            emailA.setHintTextColor(Color.parseColor("#9400d3"));//violeta
        } else {
            if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                //chamando metodo inserir
                boolean retorno = amigosDAO.inserir(amigos);
                if (retorno)
                    Toast.makeText(getApplicationContext(), "Adicionado com sucesso", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getApplicationContext(), "Erro ao adicionar Registro", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(), SmartViewTabListAmigosListBiblioteca.class);
                startActivity(intent);
                finish();
            } else
                Toast.makeText(getApplicationContext(), "Erro no Email", Toast.LENGTH_SHORT).show();
        }
    }

    public void sairDaTelaCadastroAmigos(View view) {
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setTitle("Confirmação de cancelamento");
        alerta.setMessage("Deseja cancelar o cadastro ?");
        //se a resposta for sim volta para a tela anterior
        alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //chamando outra tela
                Intent intent = new Intent(getApplicationContext(), SmartViewTabListAmigosListBiblioteca.class);
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
        if (resultado)
            Toast.makeText(getApplicationContext(), "Campos Vazios", Toast.LENGTH_SHORT).show();//aviso erro
        return resultado;
    }
}