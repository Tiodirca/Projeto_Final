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

import com.example.projetofinal.DAO.UsuarioDAO;
import com.example.projetofinal.Model.Usuario;
import com.example.projetofinal.R;

public class AtualizarUsuario extends AppCompatActivity {
    //criando as variaveis
    private Usuario usuario;
    private EditText editUsuario;
    private EditText editSenha;
    private SeekBar idadeAtual;
    private RadioButton masculinoAtual;
    private RadioButton femininoAtual;
    private TextView resulIdadeAtual;
    private ProgressBar barra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atualizar_dados_usuario);

        //Recebendo o objeto
        Bundle recebeObjeto = getIntent().getExtras();
        usuario = (Usuario) recebeObjeto.getSerializable("objeto");

        //referenciando as variaveis com os componentes
        editUsuario = findViewById(R.id.EditUsuarioAtual);
        editSenha = findViewById(R.id.EditSenhaAtual);
        idadeAtual = findViewById(R.id.seekBarIdadeAtual);
        masculinoAtual = findViewById(R.id.masculinoAtual);
        femininoAtual = findViewById(R.id.femininoAtual);
        resulIdadeAtual = findViewById(R.id.ResultadoIdadeAtual);

        //pegando os dados do usuario
        editUsuario.setText(usuario.getUsuario());
        editSenha.setText(String.valueOf(usuario.getSenha()));
        idadeAtual.setProgress(Integer.parseInt(String.valueOf(usuario.getIdade())));
        resulIdadeAtual.setText("Idade: " + idadeAtual.getProgress() + " Anos");//setando a idade para aparecer a idade correta

        //verificando qual é o sexo selecionado
        if (usuario.getSexo().equals("Masculino")) {//pegando a string do banco e verificando se ela é Masculino
            masculinoAtual.setChecked(true);//setando true para que o radiobutton fique selecionado
        } else if (usuario.getSexo().equals("Feminino")) {//pegando a string do banco e verificando se ela é feminino
            femininoAtual.setChecked(true);//setando true para que o radiobutton fique selecionado
        }

        //Pegando o progresso do seekBar para coletar a idade do usuario
        idadeAtual.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                resulIdadeAtual.setText("Idade: " + idadeAtual.getProgress() + " Anos");//setando uma String para o usuario ver na interface qual é  a idade que o seek esta mostrando
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                resulIdadeAtual.setText("Idade: " + idadeAtual.getProgress() + " Anos");//setando uma String para o usuario ver na interface qual é  a idade que o seek esta mostrando
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                resulIdadeAtual.setText("Idade: " + idadeAtual.getProgress() + " Anos");//setando uma String para o usuario ver na interface qual é  a idade que o seek esta mostrando
            }
        });
    }

    //Metodo atualizar usuario
    public void atualizarUsuario(View view) {
        //verificacao se os campos estao vazios
        if (editUsuario.getText().toString().trim().equals("") || editSenha.getText().toString().trim().equals("")){//verificacao se os campos estao vazios
            Toast.makeText(getApplicationContext(), "Campos Vazios", Toast.LENGTH_SHORT).show();//Aviso sobre campos vazios
            editUsuario.setHintTextColor(Color.parseColor("#9400d3"));//violeta
            editSenha.setHintTextColor(Color.parseColor("#9400d3"));//violeta
    }else {
            if (idadeAtual.getProgress() <= 10)
                Toast.makeText(getApplicationContext(), "Idade menor que 18 Anos", Toast.LENGTH_SHORT).show();//verificacao se o campo é menor que a idade estipulada
            else {
                // pegando o que foi digitado pelo usuario nos campos
                String user = editUsuario.getText().toString();
                int Senha = Integer.parseInt(editSenha.getText().toString());
                int idade = idadeAtual.getProgress();

                //setando os dados
                usuario.setUsuario(user);
                usuario.setSenha(Senha);
                usuario.setIdade(idade);

                //verificando qual radioButton foi selecionado para gravar a string no banco
                if (masculinoAtual.isChecked()) {
                    //Setando uma string caso seja esse o radio button selecionado
                    usuario.setSexo("Masculino");
                } else if (femininoAtual.isChecked()) {
                    //Setando uma string caso seja esse o radio button selecionado
                    usuario.setSexo("Feminino");
                }
                //instanciando a classe DAO
                UsuarioDAO usuarioDAO = new UsuarioDAO(getApplicationContext());

                adicionarProgreso();//chamando metodo de progresso da barra
                if (barra.getMax() >= 500) {//verificando se a barra é maior que o maximo dela
                    //chamando o metodo atualizar
                    boolean retorno = usuarioDAO.atualizar(usuario);
                    if (retorno)
                        Toast.makeText(getApplicationContext(), "Usuario atualizado com sucesso", Toast.LENGTH_LONG).show();//aviso usuario atualizado
                    else
                        Toast.makeText(getApplicationContext(), "Erro ao atualizar usuario", Toast.LENGTH_LONG).show();//aviso de erro

                    //trocando de tela
                    Intent intent = new Intent(getApplicationContext(), DadosUsuario.class);
                    intent.putExtra("objeto", usuario);
                    startActivity(intent);
                    finish();//finalizando a tela anterior
                } else
                    Toast.makeText(getApplicationContext(), "Erro ao processar", Toast.LENGTH_LONG).show();//aviso de erro
            }
        }
    }

    //Metodo ao clicar no botao voltar
    public void sairDaTelaAtualizarUsuario(View view) {
        //criando um alerta para confirmnacao
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setTitle("Confirmação de cancelamento");
        alerta.setMessage("Deseja cancelar a atualização dos dados ?");
        //se a resposta for sim volta para a tela anterior
        alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //chamando outra tela
                Intent intent = new Intent(getApplicationContext(), DadosUsuario.class);
                intent.putExtra("objeto", usuario);
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

    //metodo de add progresso na barra
    public void adicionarProgreso() {
        barra = findViewById(R.id.progressBarAtual);
        barra.setMax(5000);
        int progresso = 0;
        for (int i = 0; i <= 5000; i++) {
            barra.setVisibility(View.VISIBLE);
            progresso = progresso + 1;
            barra.setProgress(progresso);
        }
    }
}
