package com.example.uno;

import android.graphics.Color;
import android.util.Log;

import com.example.uno.cards.Card;
import com.example.uno.cards.CommonCard;
import com.example.uno.cards.SpecialCard;
import com.example.uno.cards.TypeAction;
import com.example.uno.dao.CommonCardDao;
import com.example.uno.dao.SpecialCardDao;
import com.example.uno.db.AppDatabase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Deck {
    static public List<Card> cards = new ArrayList<>();
    private AppDatabase db;

    public Deck(AppDatabase db) {
        this.db = db;
        getPlayingCards();
    }

    private void getPlayingCards() {
        getCardsDB();

        Log.d("teste", String.format("%d", cards.size()));
        if (cards.size() == 0) {
            Log.d("teste", "gerando cartas");
            generateCards();
        } else {
            Log.d("teste", "banco possui cartas");
            cards = cards;
        }
    }

    private void getCardsDB() {
        CommonCardDao commonCardDao = db.commonCardDao();
        SpecialCardDao specialCardDao = db.specialCardDao();

        cards.addAll(commonCardDao.getAll());
        cards.addAll(specialCardDao.getAll());
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

    static public void addCard(Card card) {
        //CardDao cardDao = db.cardDao();

        //cardDao.insertCard(card);
        cards.add(card);
    }

    static public Card raffleCard(AppDatabase db) {
        Random random = new Random();
        int index = random.nextInt(cards.size());
        Card c = null;

        // Verifica se todas as cartas foram sorteadas
        Integer count = 0;
        for (Card card: cards) {
            if (card.getDrawn()) count += 1;
        }

        Log.d("teste", count.toString());

        if (count == 108) {
            Log.d("teste", "sorteou tudo limpando");
            clear(db);
            index = random.nextInt(cards.size());
            c = cards.get(index);
        } else {
            // Verifica se carta ja foi sorteada
            c = cards.get(index);
            while (c.getDrawn() == true) {
                Log.d("teste", "ja foi sorteada");
                index = random.nextInt(cards.size());
                c = cards.get(index);
            }
        }

        return c;
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    static public List<Card> getList() {
        return cards;
    }

    static public List<Card> getdrawnCards() {
        List<Card> drawnCards = new ArrayList<>();

        for(Card c: cards) {
            if (c.getDrawn()) drawnCards.add(c);
        }

        return drawnCards;
    }

    static public void clear(AppDatabase db) {
        for (Card c: cards) {
            if (c.getDrawn() == true) {
                c.setDrawn(false);
                //c.updateCard(db);

                if (c instanceof CommonCard) {
                    CommonCardDao commonCardDao = db.commonCardDao();
                    commonCardDao.updateCommonCard((CommonCard) c);
                } else {
                    SpecialCardDao specialCardDao = db.specialCardDao();
                    specialCardDao.updateSpecialCard((SpecialCard) c);
                }
            }
        }
    }

}
