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

import com.example.projetofinal.Controller.Usuario.DadosUsuario;
import com.example.projetofinal.DAO.AmigosDAO;
import com.example.projetofinal.Model.Amigos;
import com.example.projetofinal.R;

import java.util.regex.Pattern;

public class AtualizarAmigos extends AppCompatActivity {

    private EditText nickA;
    private EditText emailA;
    private EditText telA;
    private CheckBox cbPCA;
    private CheckBox cbConsoleA;
    private CheckBox cbMobileA;
    private Amigos amigos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atualizar_amigos);

        Bundle recebeObjeto = getIntent().getExtras();
        amigos = (Amigos) recebeObjeto.getSerializable("objeto");

        nickA = findViewById(R.id.editNickNameAtt);
        emailA = findViewById(R.id.editEmailAtt);
        telA = findViewById(R.id.editTelefoneAtt);
        cbPCA = findViewById(R.id.cbPcAtt);
        cbConsoleA = findViewById(R.id.cbConsoleAtt);
        cbMobileA = findViewById(R.id.cbMobileAtt);

        nickA.setText(amigos.getNickname());
        emailA.setText(amigos.getEmail());
        telA.setText(String.valueOf(amigos.getTelefone()));

        if (amigos.getJogaOnde().indexOf("PC") >= 0) {
            cbPCA.setChecked(true);
        }
        if (amigos.getJogaOnde().indexOf("Console") >= 0) {
            cbConsoleA.setChecked(true);
        }
        if (amigos.getJogaOnde().indexOf("Mobile") >= 0) {
            cbMobileA.setChecked(true);
        }
    }

    public void atualizarAmigo(View view) {
        String nickName = nickA.getText().toString();
        String email = emailA.getText().toString();

        String cbselecionado = "";
        if (cbPCA.isChecked()) {
            cbselecionado += "PC";
        }
        if (cbConsoleA.isChecked()) {
            cbselecionado += "Console";
        }
        if (cbMobileA.isChecked()) {
            cbselecionado += "Mobile";
        }

        amigos.setNickname(nickName);
        amigos.setEmail(email);

        amigos.setJogaOnde(cbselecionado);

        AmigosDAO amigosDAO = new AmigosDAO(getApplicationContext());

        if (verificaCampovazio(nickA) || verificaCampovazio(emailA)
                || verificaCampovazio(telA)) {
            nickA.setHintTextColor(Color.parseColor("#9400d3"));//violeta
            emailA.setHintTextColor(Color.parseColor("#9400d3"));//violeta
            telA.setHintTextColor(Color.parseColor("#9400d3"));//violeta
        } else {
            if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                String telefone = telA.getText().toString();
                amigos.setTelefone(telefone);
                boolean retorno = amigosDAO.atualizar(amigos);
                if (retorno)
                    Toast.makeText(getApplicationContext(), "Atualizado com sucesso", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getApplicationContext(), "Erro ao Atualizar", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(), DadosAmigos.class);
                intent.putExtra("objeto", amigos);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(getApplicationContext(), "Erro no Email", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void sairDaTelaAtualizarAmigo(View view) {
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setTitle("Confirmação de cancelamento");
        alerta.setMessage("Deseja cancelar a atualização dos dados ?");
        //se a resposta for sim volta para a tela anterior
        alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //chamando outra tela
                Intent intent = new Intent(getApplicationContext(), DadosAmigos.class);
                intent.putExtra("objeto", amigos);
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