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

public class EndOf5Activity extends AppCompatActivity {

    Intent intent,toNext;
    TextView score,name,topScore,topScoreHolder;
    SharedPreferences highScore;
    int topScore15;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_of5);
        intent = getIntent();

        //SEVIYE SAYISINA GORE DEGISTIR
        highScore = this.getSharedPreferences("com.mkurfeyiz.kelimeoyunu.firstlevelactivities",MODE_PRIVATE);
        topScore15 = highScore.getInt("topScore15",0);
        if(intent.getIntExtra("1.5Score",0)>topScore15){
            highScore.edit().putInt("topScore15",intent.getIntExtra("1.5Score",0)).apply();
            highScore.edit().putString("username15",intent.getStringExtra("playerName")).apply();
            Toast.makeText(getApplicationContext(),"En Yüksek Skoru Elde Ettiniz!",Toast.LENGTH_SHORT).show();
        }

        score = findViewById(R.id.textViewScoreMessage15);
        name = findViewById(R.id.textViewNameMessage15);
        topScore = findViewById(R.id.textViewTopScoreMessage15);
        topScoreHolder = findViewById(R.id.textViewTopScoreHolder15);

        topScore.setText("En Yüksek Puan : "+highScore.getInt("topScore15",0));
        topScoreHolder.setText("Bölümün Skoreri : "+highScore.getString("username15",""));
        score.setText("Seviye Puanı : "+intent.getIntExtra("1.5Score",0));
        name.setText("Oyuncu Adı : "+intent.getStringExtra("playerName"));
    }

    public void startNextLvl(View view){
        toNext = new Intent(getApplicationContext(), FirstLevel1Activity.class);
        //Alta onceki aktiviteden gelen bilgileri ekleyerek ilerle
        toNext.putExtra("playerName",intent.getStringExtra("playerName"));
        //1.1 puan bilgisi
        toNext.putExtra("1.1Score",intent.getIntExtra("1.1Score",0));
        //1.2 puan bilgisi / NOT : YALNIZCA EN SON BULUNDUGUN SEVİYEDEKI TOPSCORE U ALIRKEN sharedPref KULLAN.HARICI BILGILER ICIN INTENTI KULLAN
        toNext.putExtra("1.2Score",intent.getIntExtra("1.2Score",0));
        //1.3 puan bilgisi
        toNext.putExtra("1.3Score",intent.getIntExtra("1.3Score",0));
        //1.4 bilgisi
        toNext.putExtra("1.4Score",intent.getIntExtra("1.4Score",0));
        //1.5
        toNext.putExtra("1.5Score",intent.getIntExtra("1.5Score",0));

        startActivity(toNext);
    }
}
