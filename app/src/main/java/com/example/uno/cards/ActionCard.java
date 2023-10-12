package com.example.uno.cards;

import android.graphics.Color;

import androidx.room.Entity;
import androidx.room.Ignore;

import com.google.auto.value.AutoValue;

public abstract class ActionCard extends Card implements PerformAction {
    private TypeAction action;

    public ActionCard() {
        this.action = TypeAction.SKIP;
    }

    @Ignore
    public ActionCard(TypeAction action, Color color, String urlImage) {
        super(urlImage, color);
        this.action = action;
    }
    public TypeAction getAction() {
        return action;
    }

    public void setAction(TypeAction action) {
        this.action = action;
    }

    @Override
    public String toString() {
        String colorBlack = super.toString();

        if (colorBlack.equalsIgnoreCase("BLACK")) {
            return String.format("%s", action.toString());
        }
        return String.format("%s - %s", super.toString(), action.toString());
    }
}
