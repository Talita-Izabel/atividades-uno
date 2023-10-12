package com.example.uno.cards;

import android.graphics.Color;

public class WildDrawFour extends ActionCard {

    public WildDrawFour(TypeAction typeAction, String urlImage) {
        super(typeAction, Color.valueOf(Color.BLACK), urlImage);
    }

    @Override
    public void execute() {}
}
