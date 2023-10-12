package com.example.uno;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.uno.cards.ActionCard;
import com.example.uno.cards.Card;
import com.example.uno.cards.CommonCard;
import com.example.uno.db.AppDatabase;
import com.example.uno.db.DatabaseManager;

public class MainActivity extends AppCompatActivity {
    AppDatabase db;
    private ImageView imageView;
    private Deck deck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = DatabaseManager.getDatabase(this);

        deck = new Deck(db);
    }

    public void raffle(View view){
        Card card = deck.raffleCard();

        imageView = findViewById(R.id.imageView);
        @DrawableRes
        int teste;

        teste = getResources().getIdentifier(card.getUrlImage(), "drawable", getPackageName());
        imageView.setImageResource(teste);

        TextView textView = findViewById(R.id.textView);
        textView.setText(card.toString());
    }
}