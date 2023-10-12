package com.example.uno.cards;

import android.graphics.Color;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.auto.value.AutoValue;

import java.lang.annotation.Inherited;


@AutoValue
@Entity
public abstract class Card {
    @PrimaryKey(autoGenerate = true)
    public long id;
    private String urlImage;
    private Color color;

    public Card() {
        urlImage = "";
    }

    // Room uses this factory method to create User objects.
    public static Card create(long id, String urlImage, Color color) {
        return new AutoValue_Card();
    }

    @Ignore
    public Card(String urlImage, Color color) {
        this.urlImage = urlImage;
        this.color = color;
    }


    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public Color getColor() {
        return color;
    }

    public String getConvertedColor( ) {

        if (color.equals(Color.valueOf(Color.BLUE))) {
            return "BLUE";
        } else if (color.equals(Color.valueOf(Color.RED))) {
            return "RED";
        } else if (color.equals(Color.valueOf(Color.YELLOW))) {
            return "YELLOW";
        } else if (color.equals(Color.valueOf(Color.GREEN))) {
            return "GREEN";
        }
        else {
            return "BLACK";
        }
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return String.format("%s", getConvertedColor());
    }
}
