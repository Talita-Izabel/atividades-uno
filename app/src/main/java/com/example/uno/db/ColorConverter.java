package com.example.uno.db;

import android.graphics.Color;

import androidx.room.TypeConverter;

public class ColorConverter {
    @TypeConverter
    public static Color toColor(String colorString) {
        if(colorString.equalsIgnoreCase("blue")) {
            return Color.valueOf(Color.BLUE);
        } else if(colorString.equalsIgnoreCase("red")) {
            return Color.valueOf(Color.RED);
        } else if(colorString.equalsIgnoreCase("yellow")) {
            return Color.valueOf(Color.YELLOW);
        } else if(colorString.equalsIgnoreCase("green")) {
            return Color.valueOf(Color.GREEN);
        }

        return Color.valueOf(Color.BLACK);
    }

    @TypeConverter
    public static String toStringColor(Color color) {
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

}
