package com.mkurfeyiz.kelimeoyunu.secondlevelactivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.mkurfeyiz.kelimeoyunu.R;
import com.mkurfeyiz.kelimeoyunu.thirdlevelactivities.ThirdLevelActivity;

public class EndOfSecondLvlActivity extends AppCompatActivity {
    Intent intent,toNext;
    TextView score,name,topScore,topScoreHolder;
    SharedPreferences highScore;
    int topScore26;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_of_second_lvl);
        intent = getIntent();

        //SEVIYE SAYISINA GORE DEGISTIR
        highScore = this.getSharedPreferences("com.mkurfeyiz.kelimeoyunu.secondlevelactivities",MODE_PRIVATE);
        topScore26 = highScore.getInt("topScore26",0);
        if(intent.getIntExtra("2.6Score",0)>topScore26){
            highScore.edit().putInt("topScore26",intent.getIntExtra("2.6Score",0)).apply();
            highScore.edit().putString("username26",intent.getStringExtra("playerName")).apply();
            Toast.makeText(getApplicationContext(),"En Yüksek Skoru Elde Ettiniz!",Toast.LENGTH_SHORT).show();
        }

        score = findViewById(R.id.textViewScoreMessage2);
        name = findViewById(R.id.textViewNameMessage2);
        topScore = findViewById(R.id.textViewTopScoreMessage2);
        topScoreHolder = findViewById(R.id.textViewTopScoreHolder26);

        topScoreHolder.setText("Bölümün Skoreri : "+highScore.getString("username26",""));
        topScore.setText("En Yüksek Puan : "+highScore.getInt("topScore26",0));
        //Kopyalama sirasinda kacinci seviye skoru olduguna dikkat et.
        score.setText("Seviye Puanı : "+intent.getIntExtra("2.6Score",0));
        name.setText("Oyuncu Adı : "+intent.getStringExtra("playerName"));
    }

    public void startThirdLevel(View view){
        toNext = new Intent(getApplicationContext(), ScorePage2Activity.class);
        //Alta onceki aktiviteden gelen bilgileri ekleyerek ilerle
        toNext.putExtra("playerName",intent.getStringExtra("playerName"));
        //2.1 puan bilgisi
        toNext.putExtra("2.1Score",intent.getIntExtra("2.1Score",0));
        //2.2 puan bilgisi / NOT : YALNIZCA EN SON BULUNDUGUN SEVİYEDEKI TOPSCORE U ALIRKEN sharedPref KULLAN.HARICI BILGILER ICIN INTENTI KULLAN
        toNext.putExtra("2.2Score",intent.getIntExtra("2.2Score",0));
        //2.3 puan bilgisi
        toNext.putExtra("2.3Score",intent.getIntExtra("2.3Score",0));
        //2.4 bilgisi
        toNext.putExtra("2.4Score",intent.getIntExtra("2.4Score",0));
        //2.5
        toNext.putExtra("2.5Score",intent.getIntExtra("2.5Score",0));
        //2.6
        toNext.putExtra("2.6Score",intent.getIntExtra("2.6Score",0));

        startActivity(toNext);
    }
}
