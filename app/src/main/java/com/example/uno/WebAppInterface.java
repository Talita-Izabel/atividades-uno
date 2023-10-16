package com.example.uno;

import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Toast;

import com.example.uno.cards.Card;
import com.example.uno.cards.CommonCard;
import com.example.uno.cards.SpecialCard;
import com.example.uno.dao.CommonCardDao;
import com.example.uno.dao.SpecialCardDao;
import com.example.uno.db.AppDatabase;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

public class WebAppInterface extends MainActivity {
    MainActivity mainActivity;
    WebView webView = MainActivity.webView;
    public WebAppInterface(MainActivity activity) {
        this.mainActivity = activity;
    }

    // Possibilita o uso do botão voltar
    @Override
    public void onBackPressed() {
        if(webView.canGoBack()) {
            // webView.goBack();
            // Carregar a página inicial (index.html)
            webView.loadUrl("file:///android_asset/index.html");
        } else {
            //super.onBackPressed();
            webView.loadUrl("file:///android_asset/index.html");
        }
    }

    @JavascriptInterface
    public void raffle() {
        AppDatabase db = MainActivity.db;

        Card card = Deck.raffleCard(db);

        card.setDrawn(true);

        // Atualiza a carta
        //card.updateCard(db);
        if (card instanceof CommonCard) {
            CommonCardDao commonCardDao = db.commonCardDao();
            commonCardDao.updateCommonCard((CommonCard) card);
        } else {
            SpecialCardDao specialCardDao = db.specialCardDao();
            specialCardDao.updateSpecialCard((SpecialCard) card);
        }

        Log.d("lista", "sorteada"+card.toString());
        raffleJs("raffleJS", card.toString(), card.getUrlImage());
    }

    @JavascriptInterface
    public void showList() {
        getCards();
    }

    @JavascriptInterface
    public void clearList() {
        Deck.clear(MainActivity.db);
        getCards();
    }



    public void sendListJS(final String jsCode, String list) {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                String javascriptCode = jsCode + "(" + list + ");";
                webView.loadUrl("javascript:" + javascriptCode.toString());
            }
        });
    }

    public void getCards() {
        List<Card> cards = Deck.getdrawnCards();


        List<JsonObject> list = new ArrayList<>();
        for(Card c: cards) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("color", c.getConvertedColor());
            jsonObject.addProperty("name", c.toString());
            jsonObject.addProperty("image", c.getUrlImage());

            list.add(jsonObject);
        }

        Gson gson = new Gson();

        String listCardJSON = gson.toJson(list);
        Log.d("teste", listCardJSON);

        sendListJS("list", listCardJSON);
    }


    public void raffleJs(final String jsCode, String ...strings) {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                StringBuilder javascriptCode = new StringBuilder();
                javascriptCode.append(jsCode);
                javascriptCode.append("(");

                for(String s: strings) {
                    javascriptCode.append(String.format("'%s'",s));
                    javascriptCode.append(",");
                }

                javascriptCode.deleteCharAt(javascriptCode.length()-1);

                javascriptCode.append(");");

                Log.d("teste", javascriptCode.toString());
                webView.loadUrl("javascript:" + javascriptCode.toString());
            }
        });
    }
}
