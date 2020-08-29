package com.example.projetofinal.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.EditText;

import com.example.projetofinal.Model.Usuario;

import java.util.ArrayList;

public class UsuarioDAO {

    private SQLiteDatabase escrita;
    private SQLiteDatabase leitura;

    public UsuarioDAO(Context context) {
        BancoDeDados db = new BancoDeDados(context);
        escrita = db.getWritableDatabase();
        leitura = db.getReadableDatabase();
    }

    public boolean inserir(Usuario usuario) {
        //pasando valores para o contentValues para poder passar o objeto
        ContentValues valores = new ContentValues();
        valores.put("senha", usuario.getSenha());
        valores.put("usuario", usuario.getUsuario());
        valores.put("idade", usuario.getIdade());
        valores.put("sexo", usuario.getSexo());

        try {
            escrita.insert(BancoDeDados.USUARIOS, null, valores);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public ArrayList<Usuario> consultar() {
        //Array para poder pegar todos os dados

        Cursor cursor = leitura.query(
                BancoDeDados.USUARIOS,
                null,
                null,
                null,
                null,
                null,
                null
        );
        ArrayList<Usuario> listaUsuario = new ArrayList<Usuario>();
        //Percorendo cada linha para resgatar os dados e Passar o objeto
        // para outra activity
        while (cursor.moveToNext()) {

            //referenciando as variaveis a cada campo do Bando de cdados

            int senha = cursor.getInt(cursor.getColumnIndex("senha"));
            String usuario = cursor.getString(cursor.getColumnIndex("usuario"));
            int resulIdade = cursor.getInt(cursor.getColumnIndex("idade"));
            String sexo2 = cursor.getString(cursor.getColumnIndex("sexo"));
            int idUsuario = cursor.getInt(cursor.getColumnIndex("id"));

            Usuario user = new Usuario();

            //setando os valores da consulta
            user.setSenha(senha);
            user.setUsuario(usuario);
            user.setIdade(resulIdade);
            user.setSexo(sexo2);
            user.setId(idUsuario);
            listaUsuario.add(user);
        }
        return listaUsuario;
    }

    public boolean deletar(int id) {
        String where = "id = ?";
        String idString = String.valueOf(id);
        String[] argumentos = {idString};
        try {
            int linhas = escrita.delete(BancoDeDados.USUARIOS, where, argumentos);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean atualizar(Usuario usuario) {
        ContentValues valores = new ContentValues();
        valores.put("usuario", usuario.getUsuario());
        valores.put("senha", usuario.getSenha());
        valores.put("idade", usuario.getIdade());
        valores.put("sexo", usuario.getSexo());

        String where = "id = ?";
        String idString = String.valueOf(usuario.getId());
        String[] argumento = {idString};

        try {
            escrita.update(
                    BancoDeDados.USUARIOS,
                    valores,
                    where,
                    argumento
            );
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

