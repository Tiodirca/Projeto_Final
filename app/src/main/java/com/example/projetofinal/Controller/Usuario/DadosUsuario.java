package com.example.projetofinal.Controller.Usuario;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.TextView;
import android.widget.Toast;

import com.example.projetofinal.DAO.UsuarioDAO;
import com.example.projetofinal.Model.Usuario;
import com.example.projetofinal.R;

import static java.lang.String.valueOf;

public class DadosUsuario extends AppCompatActivity {
    //criando as variavies
    private TextView txSenha;
    private TextView txUsuario;
    private TextView resulIdade;
    private TextView resulSexo;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_usuario);

        //referenciando os componentes da activity com as variaveis criaddas
        txUsuario = findViewById(R.id.resultadoNomeUsuario);
        txSenha = findViewById(R.id.resultadoSenha);
        resulIdade = findViewById(R.id.ResultadoIdade);
        resulSexo = findViewById(R.id.ResultadoSexo);
        //recebendo o objeto
        Bundle recebeObjeto = getIntent().getExtras();
        usuario = (Usuario) recebeObjeto.getSerializable("objeto");

        //setando os dados do usuario para serem mostrados
        txUsuario.setText(usuario.getUsuario());
        txSenha.setText(valueOf(usuario.getSenha()));
        resulIdade.setText(valueOf(usuario.getIdade()));
        resulSexo.setText(usuario.getSexo());

    }

    //Metodo para voltar para a lista de usuarios
    public void sairDaTelaDadosUsuario(View view) {
        Intent intent = new Intent(getApplicationContext(), ListaUsuarios.class);
        startActivity(intent);
        finish();
    }

    //Metodo de exclusao de registro
    public void detetarRegistro(View view) {
        //criando alerta para confirmacao de exclusao do registro
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setTitle("Confirmação de exclusão");
        alerta.setMessage("Você deseja excluir o Usuario ?");
        alerta.setPositiveButton(
                "Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        UsuarioDAO usuarioDAO = new UsuarioDAO(getApplicationContext());
                        int id = usuario.getId();
                        boolean retorno = usuarioDAO.deletar(id);
                        if (retorno) {
                            Toast.makeText(getApplicationContext(), "Usuário excluido com sucesso!", Toast.LENGTH_LONG).show();
                            Log.i("msg", "ID: " + usuario.getId());
                        } else {
                            Toast.makeText(getApplicationContext(), "Erro ao excluir usuário!", Toast.LENGTH_LONG).show();
                        }
                        //trocando de tela
                        Intent intent = new Intent(getApplicationContext(), ListaUsuarios.class);
                        startActivity(intent);
                        finish();
                    }
                });
        alerta.setNegativeButton(
                "Não", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
        alerta.create();
        alerta.show();

    }

    //Chamando o metodo atualizar para trocar de tela passando o  objeto
    public void atualizar(View view) {
        Intent intent = new Intent(getApplicationContext(), AtualizarUsuario.class);
        intent.putExtra("objeto", usuario);//passando o objeto
        startActivity(intent);
        finish();//finalizando a tela
    }

    //metodo voltar tela para a lista de usuarios
    public void voltarTela(View view) {
        Intent intent = new Intent(getApplicationContext(), ListaUsuarios.class);
        startActivity(intent);
        finish();
    }


}