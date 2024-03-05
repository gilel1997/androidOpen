package com.example.worknumone;

import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    private CharacterAdapter adapter;
    private ArrayList<CharacterTVShow> characterTVShows;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.ListOfCharacters);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        characterTVShows = new ArrayList<>();
        adapter = new CharacterAdapter(this, characterTVShows);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        CreatListData();

    }

    private void CreatListData() {
        CharacterTVShow oded = new CharacterTVShow("oded", "he is the drumer of the 'pijamot'" +
                " and he is the dummer one from the group(the tree friends that create the band)",R.drawable.oded);
        characterTVShows.add(oded);
        CharacterTVShow ilan = new CharacterTVShow("ilan", "he is the pianist of the 'pijamot'" +
                " and he is the melodramatic one and the song writer from the group(the tree friends that create the band)",R.drawable.ilan);
        characterTVShows.add(ilan);
        CharacterTVShow kobi = new CharacterTVShow("kobi", "he is the guitarist of the 'pijamot'" +
                " and he is the ont of the tree that eat the most and he have alot of personalitis ",R.drawable.kobi);
        characterTVShows.add(kobi);
        CharacterTVShow alona = new CharacterTVShow("alona", "she is the female charter in the show and she is the singer in the 'pijamot'" +
                " and she was just tree seasons",R.drawable.alona);
        characterTVShows.add(alona);
        CharacterTVShow dana = new CharacterTVShow("dana", "she is one of the female charter in the show and she is the singer in the 'pijamot'" +
                " and she was from ths 5 season to the end",R.drawable.dana);
        characterTVShows.add(dana);
        CharacterTVShow yamit = new CharacterTVShow("yamit", "she is one of the female charter in the show and she is a clinical psychology " +
                " and she was from ths 4 season to the end",R.drawable.yamit);
        characterTVShows.add(yamit);
        CharacterTVShow gery = new CharacterTVShow("gery", "he is the secondery character in the show " +
                " and he have the hambugery(hamburger restorant)",R.drawable.gery);
        characterTVShows.add(gery);
        CharacterTVShow natan = new CharacterTVShow("natan", "he is the DJ of the 'pijamot'" +
                " and he is cry all the time. natan joined the show at season 4",R.drawable.natan);
        characterTVShows.add(natan);
        CharacterTVShow roni = new CharacterTVShow("roni", "she is one of the female secondery charter in the show" +
                " and the daughter of gery(the hamurgery owner)",R.drawable.roni);
        characterTVShows.add(roni);
        CharacterTVShow braha = new CharacterTVShow("braha", "she is one of the female secondery charter in the show" +
                " and she is the house committee of the bullding that the tree main characters lives",R.drawable.braha);
        characterTVShows.add(braha);

        adapter.notifyDataSetChanged();
    }
}