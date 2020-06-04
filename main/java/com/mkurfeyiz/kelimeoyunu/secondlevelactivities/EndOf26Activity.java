package com.mkurfeyiz.kelimeoyunu.secondlevelactivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.mkurfeyiz.kelimeoyunu.R;

public class EndOf26Activity extends AppCompatActivity {

    Intent intent,toNext;
    TextView score,name,topScore,topScoreHolder;
    SharedPreferences highScore;
    int topScore21;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_of26);
        intent = getIntent();

        intent = getIntent();

        //SEVIYE SAYISINA GORE DEGISTIR
        highScore = this.getSharedPreferences("com.mkurfeyiz.kelimeoyunu.secondlevelactivities",MODE_PRIVATE);
        topScore21 = highScore.getInt("topScore21",0);
        if(intent.getIntExtra("2.1Score",0)>topScore21){
            highScore.edit().putInt("topScore21",intent.getIntExtra("2.1Score",0)).apply();
            highScore.edit().putString("username21",intent.getStringExtra("playerName")).apply();
            Toast.makeText(getApplicationContext(),"En Yüksek Skoru Elde Ettiniz!",Toast.LENGTH_SHORT).show();
        }

        score = findViewById(R.id.textViewScoreMessage21);
        name = findViewById(R.id.textViewNameMessage21);
        topScore = findViewById(R.id.textViewTopScoreMessage21);
        topScoreHolder = findViewById(R.id.textViewTopScoreHolder21);

        topScoreHolder.setText("Bölümün Skoreri : "+highScore.getString("username21",""));
        topScore.setText("En Yüksek Puan : "+highScore.getInt("topScore21",0));
        //Kopyalama sirasinda kacinci seviye skoru olduguna dikkat et.
        score.setText("Seviye Puanı : "+intent.getIntExtra("2.1Score",0));
        name.setText("Oyuncu Adı : "+intent.getStringExtra("playerName"));
    }

    public void startNextLvl(View view){
        toNext = new Intent(getApplicationContext(), SecondLevel2Activity.class);
        //Alta onceki aktiviteden gelen bilgileri ekleyerek ilerle
        toNext.putExtra("playerName",intent.getStringExtra("playerName"));
        //1.1 puan bilgisi
        toNext.putExtra("2.1Score",intent.getIntExtra("2.1Score",0));

        startActivity(toNext);
    }
}
