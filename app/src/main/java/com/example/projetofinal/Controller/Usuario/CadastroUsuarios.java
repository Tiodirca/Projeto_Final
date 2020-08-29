package com.example.projetofinal.Controller.Usuario;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projetofinal.Controller.Login;
import com.example.projetofinal.DAO.UsuarioDAO;
import com.example.projetofinal.Model.Usuario;
import com.example.projetofinal.R;

public class CadastroUsuarios extends AppCompatActivity {
    //criando as variaveis
    private EditText editUsuario;
    private EditText editSenha;
    private SeekBar sbIdade;
    private TextView resulIdade;
    private RadioButton rbmasculino;
    private RadioButton rbfeminino;
    private ProgressBar barra;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuarios);
        //referenciando os componentes da activity com as variaveis
        sbIdade = findViewById(R.id.seekBarIdade);
        resulIdade = findViewById(R.id.ResultadoIdade);
        //refenciando a progressBar
        barra = findViewById(R.id.progressBar);
        barra.setVisibility(View.GONE);
        resulIdade.setText("Idade: ");//setando mensagem indicando a idade
        //Setando mensagens para quando o seekbar for ativado,clicado e solto
        sbIdade.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                resulIdade.setText("Idade: " + sbIdade.getProgress() + " Anos");//setando texto
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                resulIdade.setText("Idade: " + sbIdade.getProgress() + " Anos");//setando texto
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                resulIdade.setText("Idade: " + sbIdade.getProgress() + " Anos");//setando texto
            }
        });
    }

    //metodo de cadastro
    public void cadastrar(View view) {
        //refenciando as variaveis com os componentes
        editUsuario = findViewById(R.id.EditUsuarioCad);
        editSenha = findViewById(R.id.EditSenhaCad);
        sbIdade = findViewById(R.id.seekBarIdade);
        rbmasculino = findViewById(R.id.masculino);
        rbfeminino = findViewById(R.id.feminino);
        barra = findViewById(R.id.progressBar);

        //verificando se os campos nao estao vazios
        if (editUsuario.getText().toString().trim().equals("") || editSenha.getText().toString().trim().equals("")) {
            Toast.makeText(getApplicationContext(), "Campos Vazios", Toast.LENGTH_SHORT).show();
            editUsuario.setHintTextColor(Color.parseColor("#9400d3"));//violeta
            editSenha.setHintTextColor(Color.parseColor("#9400d3"));//violeta
        }else {
            if (!rbmasculino.isChecked() && !rbfeminino.isChecked())//verificando se NAO ESTAO SELECIONADOS
                Toast.makeText(getApplicationContext(), "Selecione um SEXO", Toast.LENGTH_SHORT).show();//verificando se a algum radioButton NAO selecionado
            else {
                if (sbIdade.getProgress() < 18)
                    Toast.makeText(getApplicationContext(), "Idade Menor que 18 Anos", Toast.LENGTH_SHORT).show();//verificando se a idade selecionada é maior do que a permitida
                else {
                    //pegando o que o usuario digitou
                    String usuario = editUsuario.getText().toString();
                    int senha = Integer.parseInt(editSenha.getText().toString());
                    int idade = sbIdade.getProgress();
                    //instanciando a class
                    Usuario user = new Usuario();
                    //setando valores
                    user.setUsuario(usuario);
                    user.setSenha(senha);
                    user.setIdade(idade);

                    // Verificando qual Radio Button foi selecionado
                    if (rbmasculino.isChecked()) {
                        //Setando uma string caso seja esse o radio button selecionado
                        user.setSexo("Masculino");
                    } else if (rbfeminino.isChecked()) {
                        //Setando uma string caso seja esse o radio button selecionado
                        user.setSexo("Feminino");
                    }

                    //chamando metodo de progresso da barra
                    adicionarProgreso();
                    if (barra.getMax() >= 500) {
                        //instanciando a classe
                        UsuarioDAO usuarioDAO = new UsuarioDAO(getApplicationContext());
                        //chamando o metodo inserir
                        boolean retorno = usuarioDAO.inserir(user);
                        if (retorno) Toast.makeText(getApplicationContext(), "Usuario inserido com sucesso", Toast.LENGTH_SHORT).show();
                        else Toast.makeText(getApplicationContext(), "erro ao inserir usuario", Toast.LENGTH_LONG).show();

                        //chamando outra tela
                        Intent intent = new Intent(getApplicationContext(), Login.class);
                        startActivity(intent);
                        finish();//finalizando a tela
                    } else Toast.makeText(getApplicationContext(), "Erro ao processo", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
    //cancelar cadastro ao clicar no botao votar
    public void sairDaTelaCadastrarUsuario(View view) {
        //alerta confirmando o cancelamento do cadastro
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setTitle("Confirmação de cancelamento");
        alerta.setMessage("Deseja cancelar o cadastro ?");
        alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
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
    //Metodo de progresso para a barra
    public void adicionarProgreso() {
        barra = findViewById(R.id.progressBar);
        barra.setMax(5000);
        int progresso = 0;
        for (int i = 0; i <= 5000; i++) {
            barra.setVisibility(View.VISIBLE);
            progresso = progresso + 1;
            barra.setProgress(progresso);
        }
    }

}
