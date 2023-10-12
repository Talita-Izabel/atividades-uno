package com.example.uno.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.uno.cards.Card;

import java.util.List;

@Dao
public interface CardDao {
    @Query("SELECT * from card")
    List<Card> getAll();

    @Insert
    void insertCard(Card card);
}
