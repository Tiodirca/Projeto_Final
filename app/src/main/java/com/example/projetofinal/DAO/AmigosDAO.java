package com.example.projetofinal.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.projetofinal.Model.Amigos;

import java.util.ArrayList;

public class AmigosDAO {

    private SQLiteDatabase escrita;
    private SQLiteDatabase leitura;

    public AmigosDAO(Context context) {
        BancoDeDados db = new BancoDeDados(context);
        escrita = db.getWritableDatabase();
        leitura = db.getReadableDatabase();
    }

    public boolean inserir(Amigos amigos) {
        //pasando valores para o contentValues para poder passar o objeto
        ContentValues values = new ContentValues();
        values.put("nickname", amigos.getNickname());
        values.put("jogaOnde", amigos.getJogaOnde());
        values.put("email", amigos.getEmail());
        values.put("telefone", amigos.getTelefone());
        try {
            escrita.insert(BancoDeDados.AMIGOS, null, values);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<Amigos> consultar() {

        ArrayList<Amigos> listaAmigos = new ArrayList<Amigos>();
        Cursor cursor = leitura.query(BancoDeDados.AMIGOS,
                null,
                null,
                null,
                null,
                null,
                null
        );

        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String nick = cursor.getString(cursor.getColumnIndex("nickname"));
            String email = cursor.getString(cursor.getColumnIndex("email"));
            String tel = cursor.getString(cursor.getColumnIndex("telefone"));
            String ondeJoga = cursor.getString(cursor.getColumnIndex("jogaOnde"));

            Amigos amigos = new Amigos();
            amigos.setId(id);
            amigos.setNickname(nick);
            amigos.setEmail(email);
            amigos.setTelefone(tel);
            amigos.setJogaOnde(ondeJoga);
            listaAmigos.add(amigos);
        }
        return listaAmigos;
    }

    public boolean deletar(int id) {
        String where = "id = ?";
        String idString = String.valueOf(id);
        String[] argumento = {idString};
        try {
            escrita.delete(BancoDeDados.AMIGOS, where, argumento);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean atualizar(Amigos amigos) {
        ContentValues values = new ContentValues();
        values.put("nickName", amigos.getNickname());
        values.put("email", amigos.getEmail());
        values.put("telefone", amigos.getTelefone());
        values.put("jogaOnde", amigos.getJogaOnde());

        String where = "id = ?";
        String idString = String.valueOf(amigos.getId());
        String[] argumento = {idString};
        try {
            escrita.update(BancoDeDados.AMIGOS, values, where, argumento);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
