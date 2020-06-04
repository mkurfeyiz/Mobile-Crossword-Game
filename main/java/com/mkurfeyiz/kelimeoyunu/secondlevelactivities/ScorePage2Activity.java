package com.mkurfeyiz.kelimeoyunu.secondlevelactivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.mkurfeyiz.kelimeoyunu.R;
import com.mkurfeyiz.kelimeoyunu.thirdlevelactivities.ThirdLevel6Activity;

public class ScorePage2Activity extends AppCompatActivity {

    TextView Score21,Score22,Score23,Score24,Score25,Score26;
    TextView topScore21,topScore22,topScore23,topScore24,topScore25,topScore26;
    TextView playerName;
    Intent intent;
    SharedPreferences topScores;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_page2);

        topScores = this.getSharedPreferences("com.mkurfeyiz.kelimeoyunu.secondlevelactivities",MODE_PRIVATE);
        intent = getIntent();

        //Skorlar
        Score21 = findViewById(R.id.textViewLastScore21);
        Score22 = findViewById(R.id.textViewLastScore22);
        Score23 = findViewById(R.id.textViewLastScore23);
        Score24 = findViewById(R.id.textViewLastScore24);
        Score25 = findViewById(R.id.textViewLastScore25);
        Score26 = findViewById(R.id.textViewLastScore26);
        //Top Score
        topScore21 = findViewById(R.id.textViewLastTopScore21);
        topScore22 = findViewById(R.id.textViewLastTopScore22);
        topScore23 = findViewById(R.id.textViewLastTopScore23);
        topScore24 = findViewById(R.id.textViewLastTopScore24);
        topScore25 = findViewById(R.id.textViewLastTopScore25);
        topScore26 = findViewById(R.id.textViewLastTopScore26);
        //Oyuncu adi
        playerName = findViewById(R.id.textViewPlayerName2);

        //Burasi artik 2. seviyenin alt seviye istatisiklerini tasiyacak.Daha sonra duzenle.Ayrica diger seviyeler icin de aynisi yapilmali

        playerName.setText("Oyuncu Adı : "+intent.getStringExtra("playerName"));

        Score21.setText("2.1 : "+intent.getIntExtra("2.1Score",0));
        Score22.setText("2.2 : "+intent.getIntExtra("2.2Score",0));
        Score23.setText("2.3 : "+intent.getIntExtra("2.3Score",0));
        Score24.setText("2.4 : "+intent.getIntExtra("2.4Score",0));
        Score25.setText("2.5 : "+intent.getIntExtra("2.5Score",0));
        Score26.setText("2.6 : "+intent.getIntExtra("2.6Score",0));

        topScore21.setText("En Yüksek Skor : "+topScores.getInt("topScore21",0));
        topScore22.setText("En Yüksek Skor : "+topScores.getInt("topScore22",0));
        topScore23.setText("En Yüksek Skor : "+topScores.getInt("topScore23",0));
        topScore24.setText("En Yüksek Skor : "+topScores.getInt("topScore24",0));
        topScore25.setText("En Yüksek Skor : "+topScores.getInt("topScore25",0));
        topScore26.setText("En Yüksek Skor : "+topScores.getInt("topScore26",0));

    }
    public void start3rdLvl(View view){
        //Alert ile uyar ve MainActivitye gec
        Intent thirdLvl = new Intent(getApplicationContext(), ThirdLevel6Activity.class);
        //Sonraki seviyeye adi gecirdik.
        thirdLvl.putExtra("playerName",intent.getStringExtra("playerName"));
        startActivity(thirdLvl);
    }
}
