package com.example.uno.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.uno.cards.CommonCard;
import com.example.uno.cards.SpecialCard;
import com.example.uno.dao.CommonCardDao;
import com.example.uno.dao.SpecialCardDao;

@Database(entities = {CommonCard.class, SpecialCard.class}, version = 6)
@TypeConverters({ColorConverter.class, TypeActionConverter.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract CommonCardDao commonCardDao();
    public abstract SpecialCardDao specialCardDao();
}