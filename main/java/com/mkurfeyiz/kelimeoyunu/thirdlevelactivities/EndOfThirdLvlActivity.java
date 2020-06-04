package com.mkurfeyiz.kelimeoyunu.thirdlevelactivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.mkurfeyiz.kelimeoyunu.R;
import com.mkurfeyiz.kelimeoyunu.firstlevelactivities.ScorePageActivity;

public class EndOfThirdLvlActivity extends AppCompatActivity {
    Intent intent,toNext;
    TextView score,name,topScore,topScoreHolder;
    SharedPreferences highScore;
    int topScore36;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_of_third_lvl);

        intent = getIntent();

        //SEVIYE SAYISINA GORE DEGISTIR
        highScore = this.getSharedPreferences("com.mkurfeyiz.kelimeoyunu.thirdlevelactivities",MODE_PRIVATE);
        topScore36 = highScore.getInt("topScore36",0);
        if(intent.getIntExtra("3.6Score",0)>topScore36){
            highScore.edit().putInt("topScore36",intent.getIntExtra("3.6Score",0)).apply();
            highScore.edit().putString("username36",intent.getStringExtra("playerName")).apply();
            Toast.makeText(getApplicationContext(),"En Yüksek Skoru Elde Ettiniz!",Toast.LENGTH_SHORT).show();
        }

        score = findViewById(R.id.textViewScoreMessage36);
        name = findViewById(R.id.textViewNameMessage36);
        topScore = findViewById(R.id.textViewTopScoreMessage36);
        topScoreHolder = findViewById(R.id.textViewTopScoreHolder36);

        topScoreHolder.setText("Bölümün Skoreri : "+highScore.getString("username36",""));
        topScore.setText("En Yüksek Puan : "+highScore.getInt("topScore36",0));
        //Kopyalama sirasinda kacinci seviye skoru olduguna dikkat et.
        score.setText("Seviye Puanı : "+intent.getIntExtra("3.6Score",0));
        name.setText("Oyuncu Adı : "+intent.getStringExtra("playerName"));
    }

    public void startNextLvl(View view){
        toNext = new Intent(getApplicationContext(), ScorePage3Activity.class);
        //Alta onceki aktiviteden gelen bilgileri ekleyerek ilerle
        toNext.putExtra("playerName",intent.getStringExtra("playerName"));
        //3.1 puan bilgisi
        toNext.putExtra("3.1Score",intent.getIntExtra("3.1Score",0));
        //3.2
        toNext.putExtra("3.2Score",intent.getIntExtra("3.2Score",0));
        //3.3
        toNext.putExtra("3.3Score",intent.getIntExtra("3.3Score",0));
        //3.4 puan bilgisi
        toNext.putExtra("3.4Score",intent.getIntExtra("3.4Score",0));
        //3.5
        toNext.putExtra("3.5Score",intent.getIntExtra("3.5Score",0));
        //3.6
        toNext.putExtra("3.6Score",intent.getIntExtra("3.6Score",0));

        startActivity(toNext);
    }
}
