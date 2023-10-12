package com.example.uno.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.uno.cards.CommonCard;

import java.util.List;

@Dao
public interface CommonCardDao {
    @Query("SELECT * from commonCard")
    List<CommonCard> getAll();
    @Insert
    void insertCommonCard(CommonCard commonCard);

    @Update
    void updateCommonCard(CommonCard commonCard);
}
