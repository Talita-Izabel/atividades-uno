package com.example.uno.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.uno.cards.ActionCard;
import com.example.uno.cards.SpecialCard;

import java.util.List;

@Dao
public interface SpecialCardDao {
    @Query("SELECT * from specialCard")
    List<SpecialCard> getAll();

    @Insert
    void insertSpecialCard(SpecialCard specialCard);
}
