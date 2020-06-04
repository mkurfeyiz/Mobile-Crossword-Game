package com.mkurfeyiz.kelimeoyunu.firstlevelactivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.mkurfeyiz.kelimeoyunu.MainActivity;
import com.mkurfeyiz.kelimeoyunu.R;
import com.mkurfeyiz.kelimeoyunu.secondlevelactivities.SecondLevel6Activity;

public class ScorePageActivity extends AppCompatActivity {

    TextView Score11,Score12,Score13,Score14,Score15,Score16;
    TextView topScore11,topScore12,topScore13,topScore14,topScore15,topScore16;
    TextView playerName;
    Intent intent;
    SharedPreferences topScores;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_page);

        topScores = this.getSharedPreferences("com.mkurfeyiz.kelimeoyunu.firstlevelactivities",MODE_PRIVATE);
        intent = getIntent();

        //Skorlar
        Score11 = findViewById(R.id.textViewLastScore11);
        Score12 = findViewById(R.id.textViewLastScore12);
        Score13 = findViewById(R.id.textViewLastScore13);
        Score14 = findViewById(R.id.textViewLastScore14);
        Score15 = findViewById(R.id.textViewLastScore15);
        Score16 = findViewById(R.id.textViewLastScore16);
        //Sureler
        topScore11 = findViewById(R.id.textViewLastTopScore11);
        topScore12 = findViewById(R.id.textViewLastTopScore12);
        topScore13 = findViewById(R.id.textViewLastTopScore13);
        topScore14 = findViewById(R.id.textViewLastTopScore14);
        topScore15 = findViewById(R.id.textViewLastTopScore15);
        topScore16 = findViewById(R.id.textViewLastTopScore16);
        //Oyuncu adi
        playerName = findViewById(R.id.textViewPlayerName3);

        //Burasi artik 1. seviyenin alt seviye istatisiklerini tasiyacak.Daha sonra duzenle.Ayrica diger seviyeler icin de aynisi yapilmali

        playerName.setText("Oyuncu Adı : "+intent.getStringExtra("playerName"));

        Score11.setText("1.1 : "+intent.getIntExtra("1.1Score",0));
        Score12.setText("1.2 : "+intent.getIntExtra("1.2Score",0));
        Score13.setText("1.3 : "+intent.getIntExtra("1.3Score",0));
        Score14.setText("1.4 : "+intent.getIntExtra("1.4Score",0));
        Score15.setText("1.5 : "+intent.getIntExtra("1.5Score",0));
        Score16.setText("1.6 : "+intent.getIntExtra("1.6Score",0));

        topScore11.setText("En Yüksek Skor : "+topScores.getInt("topScore",0));
        topScore12.setText("En Yüksek Skor : "+topScores.getInt("topScore12",0));
        topScore13.setText("En Yüksek Skor : "+topScores.getInt("topScore13",0));
        topScore14.setText("En Yüksek Skor : "+topScores.getInt("topScore14",0));
        topScore15.setText("En Yüksek Skor : "+topScores.getInt("topScore15",0));
        topScore16.setText("En Yüksek Skor : "+topScores.getInt("topScore16",0));

    }
    public void start2ndLvl(View view){
        //Alert ile uyar ve MainActivitye gec
        Intent secondLvl = new Intent(getApplicationContext(), SecondLevel6Activity.class);
        //Sonraki seviyeye adi gecirdik.
        secondLvl.putExtra("playerName",intent.getStringExtra("playerName"));
        startActivity(secondLvl);
    }
}
