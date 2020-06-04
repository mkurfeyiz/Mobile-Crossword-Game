package com.mkurfeyiz.kelimeoyunu.firstlevelactivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.mkurfeyiz.kelimeoyunu.R;

public class EndOf2Activity extends AppCompatActivity {
    Intent intent,to13Intent;
    TextView score,name,topScore,topScoreHolder;
    SharedPreferences highScore;
    int topScore12;
    //NOT : PROJE PLANIN TAMAMLANMIS DURUMDA.GERIYE KALAN TEK SEY DUZENI BOZMADAN DEVAM ETMEK
    //6 ALT SEVIYE OLMAK UZERE 3 SEVIYE OLACAK VE HER BIRININ SONUNDA ScorePageActivity1-2-3 OLACAK.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_of2);

        intent = getIntent();

        highScore = this.getSharedPreferences("com.mkurfeyiz.kelimeoyunu.firstlevelactivities",MODE_PRIVATE);
        topScore12 = highScore.getInt("topScore12",0);
        if(intent.getIntExtra("1.2Score",0)>topScore12){
            highScore.edit().putInt("topScore12",intent.getIntExtra("1.2Score",0)).apply();
            highScore.edit().putString("username12",intent.getStringExtra("playerName")).apply();
            Toast.makeText(getApplicationContext(),"En Yüksek Skoru Elde Ettiniz!",Toast.LENGTH_SHORT).show();
        }

        score = findViewById(R.id.textViewScoreMessage12);
        name = findViewById(R.id.textViewNameMessage12);
        topScore = findViewById(R.id.textViewTopScoreMessage12);
        topScoreHolder = findViewById(R.id.textViewTopScoreHolder12);

        topScore.setText("En Yüksek Puan : "+highScore.getInt("topScore12",0));
        topScoreHolder.setText("Bölümün Skoreri : "+highScore.getString("username12",""));
        score.setText("Seviye Puanı : "+intent.getIntExtra("1.2Score",0));
        name.setText("Oyuncu Adı : "+intent.getStringExtra("playerName"));
    }

    public void startLvl13(View view){
        to13Intent = new Intent(getApplicationContext(), FirstLevel3Activity.class);
        //Alta onceki aktiviteden gelen bilgileri ekleyerek ilerle
        to13Intent.putExtra("playerName",intent.getStringExtra("playerName"));
        //1.1 puan bilgisi
        to13Intent.putExtra("1.1Score",intent.getIntExtra("1.1Score",0));
        to13Intent.putExtra("1.1TopScore",intent.getIntExtra("1.1TopScore",0));
        //1.2 puan bilgisi / NOT : YALNIZCA EN SON BULUNDUGUN SEVİYEDEKI TOPSCORE U ALIRKEN sharedPref KULLAN.HARICI BILGILER ICIN INTENTI KULLAN
        to13Intent.putExtra("1.2Score",intent.getIntExtra("1.2Score",0));
        to13Intent.putExtra("1.2TopScore",highScore.getInt("topScore12",0));

        startActivity(to13Intent);
    }
}
