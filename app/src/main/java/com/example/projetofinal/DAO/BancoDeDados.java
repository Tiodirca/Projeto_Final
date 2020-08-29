package com.example.projetofinal.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class BancoDeDados extends SQLiteOpenHelper {

    //criando o banco de dados
    private static String BANCO_PROJETO = "db_projeto";
    public static String USUARIOS = "usuarios";
    public static String JOGOS = "jogos";
    public static String AMIGOS = "amigos";
    public static String LOJAJOGOS = "loja";
    public static String BIBLIOTECA = "biblioteca";
    private static int VERSAO = 1;

    public BancoDeDados(@Nullable Context context) {
        super(context, BANCO_PROJETO, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase banco_dados) {
        //criando a tabela sql
        String sql_user = "CREATE TABLE IF NOT EXISTS " + USUARIOS +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "usuario VARCHAR(50),senha int(10),idade int(3),sexo VARCHAR(10))";
        String sql_Amigos = "CREATE TABLE IF NOT EXISTS " + AMIGOS +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nickname VARCHAR(50),jogaOnde VARCHAR(20),telefone VARCHAR(50),email VARCHAR(50))";

        String sql_Loja = "CREATE TABLE IF NOT EXISTS " + LOJAJOGOS +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nomeJogo VARCHAR(500),preco int(100),descricao VARCHAR(500),multiplataforma VARCHAR(10),suporteOnline VARCHAR(10),genero VARCHAR(50),desenvolvedora VARCHAR(50))";
        String sql_Biblioteca = "CREATE TABLE IF NOT EXISTS " + BIBLIOTECA +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nomeJogoComprado VARCHAR(500))";

        try {
            banco_dados.execSQL(sql_user);
            banco_dados.execSQL(sql_Amigos);
            banco_dados.execSQL(sql_Loja);
            banco_dados.execSQL(sql_Biblioteca);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

}
