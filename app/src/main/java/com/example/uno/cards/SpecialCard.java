package com.example.uno.cards;

import android.graphics.Color;

import androidx.room.Entity;
import androidx.room.Ignore;

import com.example.uno.dao.SpecialCardDao;
import com.example.uno.db.AppDatabase;

@Entity
public class SpecialCard extends Card {

    private TypeAction action;

    public SpecialCard() {
        super("teste", Color.valueOf(Color.BLACK));
        this.action = TypeAction.SKIP;
    }

    public TypeAction getAction() {
        return action;
    }

    public void setAction(TypeAction action) {
        this.action = action;
    }
//    @Override
//    public void updateCard(AppDatabase db) {
//        SpecialCardDao specialCardDao = db.specialCardDao();
//        specialCardDao.updateSpecialCard((SpecialCard) this);
//    }

    @Ignore
    public SpecialCard(TypeAction typeAction, Color color, String urlImage) {
        super(urlImage, color);
        this.action = typeAction;
    }

//    @Override
//    public void updateCard(AppDatabase db) {
//        SpecialCardDao specialCardDao = db.specialCardDao();
//        specialCardDao.updateSpecialCard((SpecialCard) this);
//    }
}
