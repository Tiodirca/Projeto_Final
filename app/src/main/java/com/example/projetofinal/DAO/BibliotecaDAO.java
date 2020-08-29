package com.example.projetofinal.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.projetofinal.Model.Biblioteca;
import com.example.projetofinal.Model.Games;

import java.util.ArrayList;

public class BibliotecaDAO {

    private SQLiteDatabase escrita;
    private SQLiteDatabase leitura;

    public BibliotecaDAO(Context context) {
        BancoDeDados bancoDeDados = new BancoDeDados(context);
        escrita = bancoDeDados.getWritableDatabase();
       leitura = bancoDeDados.getReadableDatabase();
    }

    public boolean inserir(Biblioteca biblioteca) {
        //criando a contentValues para usar o insert.
        ContentValues values = new ContentValues();
        values.put("nomeJogoComprado", biblioteca.getNomeJogoComprado());

        try {
           escrita.insert(BancoDeDados.BIBLIOTECA, null, values);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
    public ArrayList<Biblioteca> consultar() {
        ArrayList<Biblioteca> listaJogosComprados = new ArrayList<Biblioteca>();
        //criando um cursor para a leitura dos dados
        Cursor cursor = leitura.query(
                BancoDeDados.BIBLIOTECA,
                null,
                null,
                null,
                null,
                null,
                null
        );

        //laco de repeticao para percorrer o cursor e pegar os dados
        while (cursor.moveToNext()) {
            //recuperando cada item
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String nome = cursor.getString(cursor.getColumnIndex("nomeJogoComprado"));
            //instanciando a classe
            Biblioteca biblioteca = new Biblioteca();
            //setando valores
            biblioteca.setId(id);
            biblioteca.setNomeJogoComprado(nome);
            //jogando os dados setados
            listaJogosComprados.add(biblioteca);
        }
        return listaJogosComprados;
    }
}
