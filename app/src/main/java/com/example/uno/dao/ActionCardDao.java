package com.example.uno.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.uno.cards.ActionCard;

import java.util.List;

@Dao
public interface ActionCardDao {
    @Query("SELECT * from actionCard")
    List<ActionCard> getAll();
    @Insert
    void insertCommonCard(ActionCard actionCard);
}
