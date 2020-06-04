package com.mkurfeyiz.kelimeoyunu.firstlevelactivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.mkurfeyiz.kelimeoyunu.R;

public class EndOf3Activity extends AppCompatActivity {
    Intent intent,toNext;
    TextView score,name,topScore,topScoreHolder;
    SharedPreferences highScore;
    int topScore13;

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_of3);
        intent = getIntent();

        highScore = this.getSharedPreferences("com.mkurfeyiz.kelimeoyunu.firstlevelactivities",MODE_PRIVATE);
        topScore13 = highScore.getInt("topScore13",0);
        if(intent.getIntExtra("1.3Score",0)>topScore13){
            highScore.edit().putInt("topScore13",intent.getIntExtra("1.3Score",0)).apply();
            highScore.edit().putString("username13",intent.getStringExtra("playerName")).apply();
            Toast.makeText(getApplicationContext(),"En Yüksek Skoru Elde Ettiniz!",Toast.LENGTH_SHORT).show();
        }

        score = findViewById(R.id.textViewScoreMessage13);
        name = findViewById(R.id.textViewNameMessage13);
        topScore = findViewById(R.id.textViewTopScoreMessage13);
        topScoreHolder =findViewById(R.id.textViewTopScoreHolder13);

        topScore.setText("En Yüksek Puan : "+highScore.getInt("topScore13",0));
        topScoreHolder.setText("Bölümün Skoreri : "+highScore.getString("username13",""));
        score.setText("Seviye Puanı : "+intent.getIntExtra("1.3Score",0));
        name.setText("Oyuncu Adı : "+intent.getStringExtra("playerName"));
    }

    public void startNextLvl(View view){
        toNext = new Intent(getApplicationContext(), FirstLevel4Activity.class);
        //Alta onceki aktiviteden gelen bilgileri ekleyerek ilerle
        toNext.putExtra("playerName",intent.getStringExtra("playerName"));
        //1.1 puan bilgisi
        toNext.putExtra("1.1Score",intent.getIntExtra("1.1Score",0));
        //1.2 puan bilgisi / NOT : YALNIZCA EN SON BULUNDUGUN SEVİYEDEKI TOPSCORE U ALIRKEN sharedPref KULLAN.HARICI BILGILER ICIN INTENTI KULLAN
        toNext.putExtra("1.2Score",intent.getIntExtra("1.2Score",0));
        //1.3 puan bilgisi
        toNext.putExtra("1.3Score",intent.getIntExtra("1.3Score",0));

        startActivity(toNext);
    }
}
