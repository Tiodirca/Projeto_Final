package com.example.projetofinal.Controller.SmartViewTabListas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.projetofinal.Controller.BibliotecaJogosComprados.BibliotecaJogosFragment;
import com.example.projetofinal.Controller.Amigos.CadastroAmigos;
import com.example.projetofinal.Controller.Amigos.ListaAmigosFragment;
import com.example.projetofinal.Controller.Home_screen;
import com.example.projetofinal.R;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

public class SmartViewTabListAmigosListBiblioteca extends AppCompatActivity {

    private SmartTabLayout abas;
    private MediaPlayer musica;
    private ViewPager conteudo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smartviewtab_list_biblioteca_list_amigos);
        abas = findViewById(R.id.viewpagertab);
        conteudo = findViewById(R.id.viewpager);

        //chamando os fragments
        FragmentPagerItemAdapter adaptador = new FragmentPagerItemAdapter(
                getSupportFragmentManager(),
                FragmentPagerItems.with(this)
                        .add(R.string.txtListaAmigos, ListaAmigosFragment.class)
                        .add(R.string.txtListaBiblioteca, BibliotecaJogosFragment.class)
                .create()
        );
        conteudo.setAdapter(adaptador);
        abas.setViewPager(conteudo);
        musica = MediaPlayer.create(getApplicationContext(),R.raw.roses_manbek_remix_saint_jhn);
        if(musica != null) musica.start();
        Toast.makeText(getApplicationContext(), "Tocando SAINt JHN - Roses (Imanbek Remix)", Toast.LENGTH_SHORT).show();
    }
    //voltando tela
    public void sairDaTelaListas(View view){
        Intent intent = new Intent(getApplicationContext(), Home_screen.class);
        startActivity(intent);
        finish();
    }
    public void adicionarAmigoALista(View view){
        Intent intent = new Intent(getApplicationContext(), CadastroAmigos.class);
        startActivity(intent);
        finish();
    }
    protected void onPause() {
        super.onPause();
        musica.stop();
    }
}
