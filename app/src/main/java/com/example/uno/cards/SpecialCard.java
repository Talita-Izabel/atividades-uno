package com.example.uno.cards;

import android.graphics.Color;

import androidx.room.Entity;
import androidx.room.Ignore;

@Entity
public class SpecialCard extends ActionCard {

//    public SpecialCard(TypeAction typeAction, String urlImage) {
//        super(typeAction, Color.valueOf(Color.BLACK), urlImage);
//    }

    public SpecialCard() {
        super(TypeAction.SKIP, Color.valueOf(Color.BLACK), "teste");
    }

    @Ignore
    public SpecialCard(TypeAction typeAction, Color color, String urlImage) {
        super(typeAction, color, urlImage);
    }

    @Override
    public void execute() {

    }
}
