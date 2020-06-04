package com.mkurfeyiz.kelimeoyunu.firstlevelactivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.mkurfeyiz.kelimeoyunu.R;

public class EndOf6Activity extends AppCompatActivity {
    Intent intent,to12Intent;
    TextView score,name,topScore,topScoreHolder;
    SharedPreferences highScore;
    int topScore11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_of6);

        intent = getIntent();

        score = findViewById(R.id.textViewScoreMessage11);
        name = findViewById(R.id.textViewNameMessage11);
        topScore = findViewById(R.id.textViewTopScoreMessage11);
        topScoreHolder = findViewById(R.id.textViewTopScoreHolder11);

        highScore = this.getSharedPreferences("com.mkurfeyiz.kelimeoyunu.firstlevelactivities",MODE_PRIVATE);
        topScore11 = highScore.getInt("topScore",0);
        if(intent.getIntExtra("1.1Score",0)>topScore11){
            highScore.edit().putInt("topScore",intent.getIntExtra("1.1Score",0)).apply();
            highScore.edit().putString("username",intent.getStringExtra("playerName")).apply();
            Toast.makeText(getApplicationContext(),"En Yüksek Skoru Elde Ettiniz!",Toast.LENGTH_SHORT).show();
        }

        topScore.setText("En Yüksek Puan : "+highScore.getInt("topScore",0));
        topScoreHolder.setText("Bölümün Skoreri : "+highScore.getString("username",""));
        score.setText("Seviye Puanı : "+intent.getIntExtra("1.1Score",0));
        name.setText("Oyuncu Adı : "+intent.getStringExtra("playerName"));
    }

    public void startLvl12(View view){
        to12Intent = new Intent(getApplicationContext(), FirstLevel2Activity.class);
        //Alta onceki aktiviteden gelen bilgileri ekleyerek ilerle
        to12Intent.putExtra("playerName",intent.getStringExtra("playerName"));
        //1.1 puan bilgisi
        to12Intent.putExtra("1.1Score",intent.getIntExtra("1.1Score",0));
        to12Intent.putExtra("1.1TopScore",highScore.getInt("topScore",0));

        startActivity(to12Intent);
    }
}
