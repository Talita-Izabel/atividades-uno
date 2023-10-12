package com.example.uno;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.uno.cards.Card;

import java.util.List;

public class ListCard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_card);

        listCards();
    }

    public void listCards() {
        // Obtém a referência para a lista
        ListView listView = (ListView) findViewById(R.id.lista);
        List<Card> cards = Deck.getdrawnCards();

        CustomAdapter adapter = new CustomAdapter(this, cards);

        // Adiciona o ArrayAdapter à listView
        listView.setAdapter(adapter);
    }

    public void clear(View view) {
        Deck.clear(MainActivity.db);
        ListView listView = (ListView) findViewById(R.id.lista);

        List<Card> cards = Deck.getdrawnCards();
        CustomAdapter adapter = new CustomAdapter(this, cards);
        listView.setAdapter(adapter);
    }

}