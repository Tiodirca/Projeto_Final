package com.example.projetofinal.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projetofinal.Controller.Usuario.CadastroUsuarios;
import com.example.projetofinal.DAO.UsuarioDAO;
import com.example.projetofinal.Model.Usuario;
import com.example.projetofinal.R;

import java.util.ArrayList;

public class Login extends AppCompatActivity {
    private EditText usuario;
    private EditText senha;
    private TextView mensagem;
    private MediaPlayer Login;
    ArrayList<Usuario> arrayRetorno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void fazerLogin(View view) {
        //referenciando os componentes da activity com as variaveis
        usuario = findViewById(R.id.editUsuario);
        senha = findViewById(R.id.editSenha);
        mensagem = findViewById(R.id.msgErro);

        //chamando metodo de vericicacao
        if (verificaCampovazio(usuario) || verificaCampovazio(senha)) {
            //mensagem de erro
            mensagem.setText("Campos Vazios");
        } else {
            validaLogin();
        }
    }

    public void fazerCadastro(View view) {
        Intent intent = new Intent(getApplicationContext(), CadastroUsuarios.class);
        startActivity(intent);
        finish();
    }

    private boolean verificaCampovazio(EditText valor) {

        Boolean resultado = (TextUtils.isEmpty(valor.getText().toString()));
        if (resultado) {
            senha.setHintTextColor(Color.parseColor("#9400d3"));//violeta
            usuario.setHintTextColor(Color.parseColor("#9400d3"));//violeta
        }
        return resultado;
    }

    public void validaLogin() {
        arrayRetorno = new ArrayList<Usuario>();
        UsuarioDAO usuarioDAO = new UsuarioDAO(getApplicationContext());
        arrayRetorno = usuarioDAO.consultar();

        int tamanho = arrayRetorno.size();
        int contador = 0;
        boolean senhaC = false;

        while (contador < tamanho) {
            String login = arrayRetorno.get(contador).getUsuario();
            String password = String.valueOf(arrayRetorno.get(contador).getSenha());
            if (usuario.getText().toString().trim().equals(login) && senha.getText().toString().trim().equals(password)) {
                senhaC = true;
                Login = MediaPlayer.create(getApplicationContext(), R.raw.mario_coin_sound);
                if (Login != null) Login.start();
                Toast.makeText(getApplicationContext(), " Bem Vindo " + login, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), Home_screen.class);
                startActivity(intent);
                finish();
            }
            contador++;
        }
        if (!senhaC) {
            Login = MediaPlayer.create(getApplicationContext(), R.raw.music_falha_login_gta_death);
            if (Login != null) Login.start();
            Toast.makeText(getApplicationContext(), "Se Fudeu", Toast.LENGTH_LONG).show();
            mensagem.setText("Usuario ou Senha Incorretos");
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (Login != null) Login.stop();
    }
}
