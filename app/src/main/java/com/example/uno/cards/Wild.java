package com.example.uno.cards;

import android.graphics.Color;

public class Wild extends ActionCard {

    public Wild(TypeAction typeAction, String urlImage) {
        super(typeAction, Color.valueOf(Color.BLACK), urlImage);
    }

    @Override
    public void execute() {}
}
