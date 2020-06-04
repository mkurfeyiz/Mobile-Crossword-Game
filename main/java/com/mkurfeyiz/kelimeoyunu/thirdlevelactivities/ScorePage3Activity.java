package com.mkurfeyiz.kelimeoyunu.thirdlevelactivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.mkurfeyiz.kelimeoyunu.MainActivity;
import com.mkurfeyiz.kelimeoyunu.R;

public class ScorePage3Activity extends AppCompatActivity {

    TextView Score31,Score32,Score33,Score34,Score35,Score36;
    TextView topScore31,topScore32,topScore33,topScore34,topScore35,topScore36;
    TextView playerName;
    Intent intent;
    SharedPreferences topScores;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_page3);

        topScores = this.getSharedPreferences("com.mkurfeyiz.kelimeoyunu.thirdlevelactivities",MODE_PRIVATE);
        intent = getIntent();

        //Skorlar
        Score31 = findViewById(R.id.textViewLastScore31);
        Score32 = findViewById(R.id.textViewLastScore32);
        Score33 = findViewById(R.id.textViewLastScore33);
        Score34 = findViewById(R.id.textViewLastScore34);
        Score35 = findViewById(R.id.textViewLastScore35);
        Score36 = findViewById(R.id.textViewLastScore36);
        //Top Score
        topScore31 = findViewById(R.id.textViewLastTopScore31);
        topScore32 = findViewById(R.id.textViewLastTopScore32);
        topScore33 = findViewById(R.id.textViewLastTopScore33);
        topScore34 = findViewById(R.id.textViewLastTopScore34);
        topScore35 = findViewById(R.id.textViewLastTopScore35);
        topScore36 = findViewById(R.id.textViewLastTopScore36);
        //Oyuncu adi
        playerName = findViewById(R.id.textViewPlayerName4);

        //Burasi artik 2. seviyenin alt seviye istatisiklerini tasiyacak.Daha sonra duzenle.Ayrica diger seviyeler icin de aynisi yapilmali

        playerName.setText("Oyuncu Adı : "+intent.getStringExtra("playerName"));

        Score31.setText("3.1 : "+intent.getIntExtra("3.1Score",0));
        Score32.setText("3.2 : "+intent.getIntExtra("3.2Score",0));
        Score33.setText("3.3 : "+intent.getIntExtra("3.3Score",0));
        Score34.setText("3.4 : "+intent.getIntExtra("3.4Score",0));
        Score35.setText("3.5 : "+intent.getIntExtra("3.5Score",0));
        Score36.setText("3.6 : "+intent.getIntExtra("3.6Score",0));

        topScore31.setText("En Yüksek Skor : "+topScores.getInt("topScore31",0));
        topScore32.setText("En Yüksek Skor : "+topScores.getInt("topScore32",0));
        topScore33.setText("En Yüksek Skor : "+topScores.getInt("topScore33",0));
        topScore34.setText("En Yüksek Skor : "+topScores.getInt("topScore34",0));
        topScore35.setText("En Yüksek Skor : "+topScores.getInt("topScore35",0));
        topScore36.setText("En Yüksek Skor : "+topScores.getInt("topScore36",0));

    }
    public void restart(View view){
        Intent restart = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(restart);
    }
}
