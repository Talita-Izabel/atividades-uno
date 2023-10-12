package com.example.uno.db;

import androidx.room.TypeConverter;

import com.example.uno.cards.TypeAction;

public class TypeActionConverter {
    @TypeConverter
    public static TypeAction toTypeAction(String typeActionString) {
        if(typeActionString.equalsIgnoreCase("SKIP"))
            return TypeAction.SKIP;
        if(typeActionString.equalsIgnoreCase("WILD"))
            return TypeAction.WILD;
        if(typeActionString.equalsIgnoreCase("REVERSE"))
            return TypeAction.REVERSE;
        if(typeActionString.equalsIgnoreCase("DRAW_TWO"))
            return TypeAction.REVERSE;
        return TypeAction.WILD_DRAW_FOUR;
    }

    @TypeConverter
    public static String toStringTypeAction(TypeAction typeAction) {
        if(typeAction == TypeAction.SKIP)
            return  "SKIP";
        if(typeAction == TypeAction.WILD)
            return  "WILD";
        if(typeAction == TypeAction.REVERSE)
            return  "REVERSE";
        if(typeAction == TypeAction.REVERSE)
            return  "REVERSE";

        return "WILD_DRAW_FOUR";
    }

}
