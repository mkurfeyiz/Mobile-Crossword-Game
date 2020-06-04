package com.mkurfeyiz.kelimeoyunu.firstlevelactivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.mkurfeyiz.kelimeoyunu.R;
import com.mkurfeyiz.kelimeoyunu.secondlevelactivities.SecondLevelActivity;

public class EndOfFirstLvlActivity extends AppCompatActivity {

    Intent intent,toNext;
    TextView score,name,topScore,topScoreHolder;
    SharedPreferences highScore;
    int topScore16;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_of_first_lvl);

        intent = getIntent();

        //SEVIYE SAYISINA GORE DEGISTIR
        highScore = this.getSharedPreferences("com.mkurfeyiz.kelimeoyunu.firstlevelactivities",MODE_PRIVATE);
        topScore16 = highScore.getInt("topScore16",0);
        if(intent.getIntExtra("1.6Score",0)>topScore16){
            highScore.edit().putInt("topScore16",intent.getIntExtra("1.6Score",0)).apply();
            highScore.edit().putString("username16",intent.getStringExtra("playerName")).apply();
            Toast.makeText(getApplicationContext(),"En Yüksek Skoru Elde Ettiniz!",Toast.LENGTH_SHORT).show();
        }

        score = findViewById(R.id.textViewScoreMessage16);
        name = findViewById(R.id.textViewNameMessage16);
        topScore = findViewById(R.id.textViewTopScoreMessage16);
        topScoreHolder = findViewById(R.id.textViewTopScoreHolder16);

        topScoreHolder.setText("Bölümün Skoreri : "+highScore.getString("username16",""));
        topScore.setText("En Yüksek Puan : "+highScore.getInt("topScore16",0));
        score.setText("Seviye Puanı : "+intent.getIntExtra("1.6Score",0));
        name.setText("Oyuncu Adı : "+intent.getStringExtra("playerName"));
    }

    public void startSecondLevel(View view){
        toNext = new Intent(getApplicationContext(), ScorePageActivity.class);
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
        //1.6
        toNext.putExtra("1.6Score",intent.getIntExtra("1.6Score",0));

        startActivity(toNext);
    }
}
