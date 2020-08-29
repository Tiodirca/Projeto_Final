package com.example.projetofinal.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.projetofinal.Model.Amigos;
import com.example.projetofinal.Model.Loja;

import java.util.ArrayList;

public class LojaDAO {
    private SQLiteDatabase escrita;
    private SQLiteDatabase leitura;

    public LojaDAO(Context context) {
        BancoDeDados db = new BancoDeDados(context);
        escrita = db.getWritableDatabase();
        leitura = db.getReadableDatabase();
    }

    public boolean inserir(Loja loja) {
        //pegando os valores
        ContentValues values = new ContentValues();
        values.put("nomeJogo", loja.getNomeJogo());
        values.put("preco", loja.getPreco());
        values.put("descricao", loja.getDescricao());
        values.put("desenvolvedora",loja.getDeveloper());
        values.put("multiplataforma", loja.getMultiplataforma());
        values.put("suporteOnline", loja.getSuporteOnline());
        values.put("genero", loja.getGenero());
        try {
         escrita.insert(BancoDeDados.LOJAJOGOS, null, values);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<Loja> consultar() {

        ArrayList<Loja> listaJogosLoja = new ArrayList<Loja>();
        Cursor cursor = leitura.query(BancoDeDados.LOJAJOGOS,
                null,
                null,
                null,
                null,
                null,
                null
        );

        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String nomeJogo = cursor.getString(cursor.getColumnIndex("nomeJogo"));
            int precoJogo = cursor.getInt(cursor.getColumnIndex("preco"));
            String descricao = cursor.getString(cursor.getColumnIndex("descricao"));
            String multiplataforma = cursor.getString(cursor.getColumnIndex("multiplataforma"));
            String suporteOnline = cursor.getString(cursor.getColumnIndex("suporteOnline"));
            String developer = cursor.getString(cursor.getColumnIndex("desenvolvedora"));
            String generoJogo = cursor.getString(cursor.getColumnIndex("genero"));

            Loja loja = new Loja();
            loja.setId(id);
            loja.setNomeJogo(nomeJogo);
            loja.setPreco(precoJogo);
            loja.setDeveloper(developer);
            loja.setDescricao(descricao);
            loja.setMultiplataforma(multiplataforma);
            loja.setSuporteOnline(suporteOnline);
            loja.setGenero(generoJogo);

            listaJogosLoja.add(loja);
        }
        return listaJogosLoja;
    }

}
