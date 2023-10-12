package com.example.uno;

import android.graphics.Color;
import android.util.Log;

import com.example.uno.cards.ActionCard;
import com.example.uno.cards.Card;
import com.example.uno.cards.CommonCard;
import com.example.uno.cards.SpecialCard;
import com.example.uno.cards.TypeAction;
import com.example.uno.dao.CardDao;
import com.example.uno.dao.CommonCardDao;
import com.example.uno.dao.SpecialCardDao;
import com.example.uno.db.AppDatabase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Deck {
    private List<Card> cards;
    private final AppDatabase db;

    public Deck(AppDatabase db) {
        cards = new ArrayList<>();
        this.db = db;
        getPlayingCards();
    }

    private void getPlayingCards() {
        CardDao cardDao = db.cardDao();
        List<Card> cardsDB = cardDao.getAll();

        Log.d("teste", String.format("%d", cardsDB.size()));
        if (cardsDB.size() == 0) {
            Log.d("teste", "gerando cartas");
            generateCards();
        } else {
            Log.d("teste", "banco possui cartas");
            cards = cardsDB;
        }
    }

    private void generateCards() {
        boolean isFirst = true;

        Color[] colors = { Color.valueOf(Color.BLUE), Color.valueOf(Color.RED),
                Color.valueOf(Color.YELLOW), Color.valueOf(Color.GREEN) };
        String[] colorsString = { "blue", "red", "yellow", "green" };

        // Cria as cartas coloridas comuns
        CommonCard commonCard;
        String img;
        for (short i=0; i < 10; i++) {

            for (int j = 0; j < 4; j ++) {
                img = String.format("uno_card_%s%d", colorsString[j], i);
                commonCard = new CommonCard(i, colors[j], img);
                addCard(commonCard);

                CommonCardDao ccdao = db.commonCardDao();
                ccdao.insertCommonCard(commonCard);

            }

            if (i == 9 && isFirst) {
                i = 0;
                isFirst = false;
            }
        }

        // Cria cartas coringas
//        Wild wild;
//        WildDrawFour wildDrawFour;
        SpecialCard specialCard;
        SpecialCardDao specialCardDao = db.specialCardDao();
        for (int i = 0; i < 4; i ++) {
//            wild = new Wild(TypeAction.WILD, "uno_card_wildchange");
//            wildDrawFour = new WildDrawFour(TypeAction.WILD_DRAW_FOUR, "uno_card_wilddraw4");

            specialCard = new SpecialCard(TypeAction.WILD, Color.valueOf(Color.BLACK), "uno_card_wildchange");
            addCard(specialCard);
            specialCardDao.insertSpecialCard(specialCard);

            specialCard = new SpecialCard(TypeAction.WILD_DRAW_FOUR, Color.valueOf(Color.BLACK), "uno_card_wilddraw4");
            addCard(specialCard);
            specialCardDao.insertSpecialCard(specialCard);

        }

        // Cria as cartas de ação
        for (int i = 0; i < 2; i ++) {

            for (int j = 0; j < 4; j ++) {
                specialCard = new SpecialCard(TypeAction.DRAW_TWO, colors[j],
                        String.format("uno_card_%sdraw2", colorsString[j]));
                addCard(specialCard);
                specialCardDao.insertSpecialCard(specialCard);

                specialCard = new SpecialCard(TypeAction.REVERSE, colors[j],
                        String.format("uno_card_%sreverse", colorsString[j]));
                addCard(specialCard);
                specialCardDao.insertSpecialCard(specialCard);

                specialCard = new SpecialCard(TypeAction.SKIP, colors[j],
                        String.format("uno_card_%sskip", colorsString[j]));
                addCard(specialCard);
                specialCardDao.insertSpecialCard(specialCard);
            }

        }
    }

    private void addCard(Card card) {
        //CardDao cardDao = db.cardDao();

        //cardDao.insertCard(card);
        cards.add(card);
    }

    public void printList() {
        for(Card card: cards) {
            if (card instanceof ActionCard) {
                Log.d("test", ((ActionCard) card).getAction().toString() );
            }
        }

        Log.d("test", String.format("%d", cards.size()));
    }

    public Card raffleCard() {
        Random random = new Random();
        int index = random.nextInt(cards.size());

        return cards.get(index);
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

}
