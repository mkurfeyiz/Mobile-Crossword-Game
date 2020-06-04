package com.mkurfeyiz.kelimeoyunu.secondlevelactivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.mkurfeyiz.kelimeoyunu.R;

public class EndOf23Activity extends AppCompatActivity {

    Intent intent,toNext;
    TextView score,name,topScore,topScoreHolder;
    SharedPreferences highScore;
    int topScore23;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_of23);
        intent = getIntent();

        intent = getIntent();

        //SEVIYE SAYISINA GORE DEGISTIR
        highScore = this.getSharedPreferences("com.mkurfeyiz.kelimeoyunu.secondlevelactivities",MODE_PRIVATE);
        topScore23 = highScore.getInt("topScore23",0);
        if(intent.getIntExtra("2.3Score",0)>topScore23){
            highScore.edit().putInt("topScore23",intent.getIntExtra("2.3Score",0)).apply();
            highScore.edit().putString("username23",intent.getStringExtra("playerName")).apply();
            Toast.makeText(getApplicationContext(),"En Yüksek Skoru Elde Ettiniz!",Toast.LENGTH_SHORT).show();
        }

        score = findViewById(R.id.textViewScoreMessage23);
        name = findViewById(R.id.textViewNameMessage23);
        topScore = findViewById(R.id.textViewTopScoreMessage23);
        topScoreHolder = findViewById(R.id.textViewTopScoreHolder23);

        topScoreHolder.setText("Bölümün Skoreri : "+highScore.getString("username23",""));
        topScore.setText("En Yüksek Puan : "+highScore.getInt("topScore23",0));
        score.setText("Seviye Puanı : "+intent.getIntExtra("2.3Score",0));
        name.setText("Oyuncu Adı : "+intent.getStringExtra("playerName"));
    }

    public void startNextLvl(View view){
        toNext = new Intent(getApplicationContext(), SecondLevel4Activity.class);
        //Alta onceki aktiviteden gelen bilgileri ekleyerek ilerle
        toNext.putExtra("playerName",intent.getStringExtra("playerName"));
        //2.1 puan bilgisi
        toNext.putExtra("2.1Score",intent.getIntExtra("2.1Score",0));
        //2.2
        toNext.putExtra("2.2Score",intent.getIntExtra("2.2Score",0));
        //2.3
        toNext.putExtra("2.3Score",intent.getIntExtra("2.3Score",0));

        startActivity(toNext);
    }
}
