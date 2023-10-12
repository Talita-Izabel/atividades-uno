package com.example.uno.cards;

import android.graphics.Color;

import androidx.room.Entity;

import com.example.uno.cards.Card;
import com.example.uno.dao.CommonCardDao;
import com.example.uno.db.AppDatabase;

@Entity
public class CommonCard extends Card {
    private Short number;

    public Short getNumber() {
        return number;
    }

    public CommonCard(Short number, Color color, String urlImage) {
        super(urlImage, color);
        this.number = number;
    }

    public void setNumber(Short number) {
        this.number = number;
    }

//    @Override
//    public void updateCard(AppDatabase db) {
//        CommonCardDao commonCardDao = db.commonCardDao();
//        commonCardDao.updateCommonCard((CommonCard) this);
//    }

    @Override
    public String toString() {
        return String.format("%s - %d", super.toString(), number);
    }

//    @Override
//    public void updateCard(AppDatabase db) {
//        CommonCardDao commonCardDao = db.commonCardDao();
//        commonCardDao.updateCommonCard((CommonCard) this);
//    }
}
