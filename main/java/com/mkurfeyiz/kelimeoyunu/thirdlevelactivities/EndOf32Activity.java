package com.mkurfeyiz.kelimeoyunu.thirdlevelactivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.mkurfeyiz.kelimeoyunu.R;

public class EndOf32Activity extends AppCompatActivity {

    Intent intent,toNext;
    TextView score,name,topScore,topScoreHolder;
    SharedPreferences highScore;
    int topScore32;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_of32);

        intent = getIntent();

        //SEVIYE SAYISINA GORE DEGISTIR
        highScore = this.getSharedPreferences("com.mkurfeyiz.kelimeoyunu.thirdlevelactivities",MODE_PRIVATE);
        topScore32 = highScore.getInt("topScore32",0);
        if(intent.getIntExtra("3.2Score",0)>topScore32){
            highScore.edit().putInt("topScore32",intent.getIntExtra("3.2Score",0)).apply();
            highScore.edit().putString("username32",intent.getStringExtra("playerName")).apply();
            Toast.makeText(getApplicationContext(),"En Yüksek Skoru Elde Ettiniz!",Toast.LENGTH_SHORT).show();
        }

        score = findViewById(R.id.textViewScoreMessage32);
        name = findViewById(R.id.textViewNameMessage32);
        topScore = findViewById(R.id.textViewTopScoreMessage32);
        topScoreHolder = findViewById(R.id.textViewTopScoreHolder32);

        topScoreHolder.setText("Bölümün Skoreri : "+highScore.getString("username32",""));
        topScore.setText("En Yüksek Puan : "+highScore.getInt("topScore32",0));
        //Kopyalama sirasinda kacinci seviye skoru olduguna dikkat et.
        score.setText("Seviye Puanı : "+intent.getIntExtra("3.2Score",0));
        name.setText("Oyuncu Adı : "+intent.getStringExtra("playerName"));
    }

    public void startNextLvl(View view){
        toNext = new Intent(getApplicationContext(), ThirdLevel3Activity.class);
        //Alta onceki aktiviteden gelen bilgileri ekleyerek ilerle
        toNext.putExtra("playerName",intent.getStringExtra("playerName"));
        //3.1 puan bilgisi
        toNext.putExtra("3.1Score",intent.getIntExtra("3.1Score",0));
        //3.2
        toNext.putExtra("3.2Score",intent.getIntExtra("3.2Score",0));

        startActivity(toNext);
    }
}
