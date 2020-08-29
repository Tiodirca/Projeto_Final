package com.example.projetofinal.Controller.Usuario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.projetofinal.Controller.Configuracoes;
import com.example.projetofinal.DAO.UsuarioDAO;
import com.example.projetofinal.Model.Usuario;
import com.example.projetofinal.R;

import java.util.ArrayList;

public class ListaUsuarios extends AppCompatActivity {
    //criando as variavies
    private ListView listaUsuario;
    private ArrayAdapter<String> adaptador;
    private ArrayList<String> arrayUsuario;
    ArrayList<Usuario> arrayRetorno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_usuarios);

        //refenciando as variaveis com os componentes da activity
        listaUsuario = findViewById(R.id.listaUsuario);
        arrayUsuario = new ArrayList<String>();
        //montando a lista
        arrayRetorno = new ArrayList<Usuario>();
        UsuarioDAO usuarioDAO = new UsuarioDAO(getApplicationContext());
        arrayRetorno = usuarioDAO.consultar();

        int tamanho = arrayRetorno.size();

        int contador = 0;
        //laco de repeticao para pegar o nome dos usuarios e add na lsita
        while (contador < tamanho) {
            String usuario = arrayRetorno.get(contador).getUsuario();
            arrayUsuario.add(usuario);
            contador++;
        }
        //fazendo o adaptador para jogar o array de usuario na lista
        adaptador = new ArrayAdapter<String>(
                getApplicationContext(),
                R.layout.lista_view,
                android.R.id.text1,
                arrayUsuario
        );
        listaUsuario.setAdapter(adaptador);

        //setando os dados quando clicar no usuario
        listaUsuario.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                //pegando a posicao do dado
                int Senha = arrayRetorno.get(position).getSenha();
                String Usuario = arrayRetorno.get(position).getUsuario();
                int resulIdade = arrayRetorno.get(position).getIdade();
                String resulSexo = arrayRetorno.get(position).getSexo();
                int idUsuario = arrayRetorno.get(position).getId();

                //setando os dados
                Usuario usuario = new Usuario();
                usuario.setSenha(Senha);
                usuario.setUsuario(Usuario);
                usuario.setIdade(resulIdade);
                usuario.setSexo(resulSexo);
                usuario.setId(idUsuario);

                //mudando de tela
                Intent intent = new Intent(getApplicationContext(), DadosUsuario.class);
                intent.putExtra("objeto", usuario);
                startActivity(intent);
                finish();
            }
        });
    }

    //metodo voltar tela
    public void sairDaTelaListaUsuarios(View view) {
        Intent intent = new Intent(getApplicationContext(), Configuracoes.class);
        startActivity(intent);
        finish();
    }

}