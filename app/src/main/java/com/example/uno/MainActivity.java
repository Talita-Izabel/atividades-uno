package com.example.uno;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.uno.cards.Card;
import com.example.uno.cards.CommonCard;
import com.example.uno.cards.SpecialCard;
import com.example.uno.dao.CommonCardDao;
import com.example.uno.dao.SpecialCardDao;
import com.example.uno.db.AppDatabase;
import com.example.uno.db.DatabaseManager;

public class MainActivity extends AppCompatActivity {
    static AppDatabase db;
    private ImageView imageView;
    private Deck deck;
    static WebView webView;

    @SuppressLint("WrongThread")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (db == null) {
            db = DatabaseManager.getDatabase(this);
            deck = new Deck(db);
        }

        configureWebView();
    }

    private void configureWebView() {
        // Configura a WebView
        webView = (WebView) findViewById(R.id.webView);
        webView.setVisibility(View.INVISIBLE);
        webView.setWebChromeClient(new WebChromeClient());

        // Habilita o JS
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Garante que usará a WebView e não o navegador padrão
        webView.setWebViewClient(new WebViewClient(){
            // Callback que determina quando terminou de ser carregada a
            // WebView, para trocarmos a imagem de carregamento por ela
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                ImageView imageView = (ImageView)
                        findViewById(R.id.imageView);
                imageView.setVisibility(View.INVISIBLE);
                webView.setVisibility(View.VISIBLE);
            }
        });

        // Associa a interface (a ser definida abaixo) e carrega o HTML
        webView.addJavascriptInterface(new WebAppInterface(this),"Android");
        webView.loadUrl("file:///android_asset/index.html");
    }


    public void raffle(View view){
        Card card = deck.raffleCard(db);

        Log.d("teste", card.toString());
        imageView = findViewById(R.id.imageView);
        @DrawableRes
        int teste;

        teste = getResources().getIdentifier(card.getUrlImage(), "drawable", getPackageName());

        card.setIdImage(teste);
        card.setDrawn(true);

        // Atualiza a carta
        Log.d("teste", "passou 1");
        //card.updateCard(db);
        if (card instanceof CommonCard) {
            CommonCardDao commonCardDao = db.commonCardDao();
            commonCardDao.updateCommonCard((CommonCard) card);
        } else {
            SpecialCardDao specialCardDao = db.specialCardDao();
            specialCardDao.updateSpecialCard((SpecialCard) card);
        }

        imageView.setImageResource(teste);

        TextView textView = findViewById(R.id.textView);
        textView.setText(card.toString());
        Log.d("teste", "passou 2");
    }

    public void listCards(View view) {
        Intent intent = new Intent(this, ListCard.class);
        startActivity(intent);
    }

}