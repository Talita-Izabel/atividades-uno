package com.example.uno;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.uno.cards.Card;
import com.example.uno.cards.CommonCard;
import com.example.uno.cards.SpecialCard;
import com.example.uno.dao.CommonCardDao;
import com.example.uno.dao.SpecialCardDao;
import com.example.uno.db.AppDatabase;
import com.example.uno.db.DatabaseManager;

public class MainActivity extends AppCompatActivity {
    static AppDatabase db;
    private ImageView imageView;
    private Deck deck;

    @SuppressLint("WrongThread")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = DatabaseManager.getDatabase(this);

        deck = new Deck(db);
    }

    public void raffle(View view){
        Card card = deck.raffleCard(db);

        Log.d("teste", card.toString());
        imageView = findViewById(R.id.imageView);
        @DrawableRes
        int teste;

        teste = getResources().getIdentifier(card.getUrlImage(), "drawable", getPackageName());

        card.setIdImage(teste);
        card.setDrawn(true);

        // Atualiza a carta
        Log.d("teste", "passou 1");
        //card.updateCard(db);
        if (card instanceof CommonCard) {
            CommonCardDao commonCardDao = db.commonCardDao();
            commonCardDao.updateCommonCard((CommonCard) card);
        } else {
            SpecialCardDao specialCardDao = db.specialCardDao();
            specialCardDao.updateSpecialCard((SpecialCard) card);
        }

        imageView.setImageResource(teste);

        TextView textView = findViewById(R.id.textView);
        textView.setText(card.toString());
        Log.d("teste", "passou 2");
    }

    public void listCards(View view) {
        Intent intent = new Intent(this, ListCard.class);
        startActivity(intent);
    }

}