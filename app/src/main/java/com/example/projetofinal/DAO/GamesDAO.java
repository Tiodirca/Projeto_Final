package com.example.projetofinal.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.projetofinal.Model.Games;

import java.util.ArrayList;

public class GamesDAO {
    //criando variaveis
    private SQLiteDatabase escrita;
    private SQLiteDatabase leitura;

    //definindo o que as variaveis farao
    public GamesDAO(Context context) {
        BancoDeDados bancoDeDados = new BancoDeDados(context);

        escrita = bancoDeDados.getWritableDatabase();
        leitura = bancoDeDados.getReadableDatabase();
    }

    //metodo inserir
    public boolean inserir(Games games) {
        //criando a contentValues para usar o insert.
        ContentValues values = new ContentValues();
        values.put("nome", games.getNome());
        values.put("genero", games.getGenero());
        values.put("desenvolvedora", games.getDesenvolvedora());
        values.put("preco", games.getPreco());
        values.put("online", games.getOnline());
        values.put("multiplataforma", games.getMultiplataforma());

        try {
            escrita.insert(BancoDeDados.JOGOS, null, values);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }


    //metodo de consulta
    public ArrayList<Games> consultar() {
        ArrayList<Games> listaGames = new ArrayList<Games>();
        //criando um cursor para a leitura dos dados
        Cursor cursor = leitura.query(
                BancoDeDados.JOGOS,
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
            String nome = cursor.getString(cursor.getColumnIndex("nome"));
            String desenvolvedora = cursor.getString(cursor.getColumnIndex("desenvolvedora"));
            String plataforma = cursor.getString(cursor.getColumnIndex("multiplataforma"));
            String online = cursor.getString(cursor.getColumnIndex("online"));
            String genero = cursor.getString(cursor.getColumnIndex("genero"));
            float preco = cursor.getFloat(cursor.getColumnIndex("preco"));
            //instanciando a classe
            Games games = new Games();
            //setando valores
            games.setId(id);
            games.setNome(nome);
            games.setDesenvolvedora(desenvolvedora);
            games.setMultiplataforma(plataforma);
            games.setOnline(online);
            games.setGenero(genero);
            games.setPreco(preco);
            //jogando os dados setados
            listaGames.add(games);
        }
        return listaGames;
    }
}
