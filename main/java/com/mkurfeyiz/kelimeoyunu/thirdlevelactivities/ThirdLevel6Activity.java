package com.mkurfeyiz.kelimeoyunu.thirdlevelactivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mkurfeyiz.kelimeoyunu.R;

import java.util.ArrayList;
import java.util.Random;

public class ThirdLevel6Activity extends AppCompatActivity {

    Intent intent,toNext;
    int score,time,multiplier,correctCount;
    ArrayList<String> correctAnswers;
    TextView scoreText,timeText;
    Handler handler;
    Runnable runnable;
    EditText answer;
    String[] letters = {"S","E","P","R","Ü"};

    TextView E,E2,letterE;
    TextView r,r2,letterR;
    TextView U,U2,U3,letterU;
    TextView S,P,letterS,letterP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_level6);

        score = 0;
        time = 0;
        multiplier = 10;
        correctCount = 0;
        correctAnswers = new ArrayList<>(6);

        intent = getIntent();

        scoreText = findViewById(R.id.textViewScore31);
        timeText = findViewById(R.id.textViewTime31);
        answer = findViewById(R.id.editTextAnswerEntry31);

        //E ler
        E = findViewById(R.id.textView31E);
        E2 = findViewById(R.id.textView31E2);
        letterE = findViewById(R.id.textViewLetterE31);
        //R ler
        r = findViewById(R.id.textView31R);
        r2 = findViewById(R.id.textView31R2);
        letterR = findViewById(R.id.textViewLetterR31);
        //U lar
        U = findViewById(R.id.textView31U);
        U2 = findViewById(R.id.textView31U2);
        U3 = findViewById(R.id.textView31U3);
        letterU = findViewById(R.id.textViewLetterU31);
        //S ve P ler
        S = findViewById(R.id.textView31S);
        P = findViewById(R.id.textView31P);
        letterS = findViewById(R.id.textViewLetterS31);
        letterP = findViewById(R.id.textViewLetterP31);

        time();
    }

    public void time() {
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                timeText.setText("Geçen Süre : "+time);
                time++;
                timeText.setText("Geçen Süre : "+time);
                if(time%60 == 0 && multiplier>0)
                {
                    multiplier--;  //puan azaltma kontrolu
                }
                if(multiplier<0)
                {
                    multiplier = 1; // negatif olursa 1 e esitle
                }
                handler.postDelayed(runnable,1000);
            }
        };
        handler.post(runnable);
    }

    public void search(View view) {
        String input=answer.getText().toString();
        answerCheck(input);
        if(correctCount == 3){
            //Intentle yeni aktiviteye gec ve oyuncuya ilk seviyeyi bitirdigini belirt.
            toNext = new Intent(getApplicationContext(), EndOf36Activity.class);
            toNext.putExtra("playerName",intent.getStringExtra("playerName"));
            //3.1
            toNext.putExtra("3.1Score",score);

            startActivity(toNext);
        }
    }

    public void help(View view) {
        String temp;
        Random random =new Random();
        int index1,index2;
        for (int i=0;i<letters.length;i++){
            index1 = random.nextInt(5);
            index2 = random.nextInt(5);
            temp = letters[index1];
            letters[index1] = letters[index2];
            letters[index2] = temp;
        }
        letterR.setText(letters[0]);
        letterP.setText(letters[1]);
        letterS.setText(letters[2]);
        letterE.setText(letters[3]);
        letterU.setText(letters[4]);
    }

    public void answerCheck(String ans) {
        if(correctAnswers.contains(ans.toUpperCase())) {
            Toast.makeText(getApplicationContext(),"Bu kelimeyi daha önce buldunuz.",Toast.LENGTH_SHORT).show();
        } else {
            if(ans.toUpperCase().matches("SÜPER")){
                correctAnswers.add(ans.toUpperCase());
                Toast.makeText(getApplicationContext(),"SÜPER Doğru Cevap",Toast.LENGTH_SHORT).show();

                S.setText("S");
                S.setBackgroundColor(Color.WHITE);
                U2.setText("Ü");
                U2.setBackgroundColor(Color.WHITE);
                P.setText("P");
                P.setBackgroundColor(Color.WHITE);
                E.setText("E");
                E.setBackgroundColor(Color.WHITE);
                r.setText("R");
                r.setBackgroundColor(Color.WHITE);

                score += 5*multiplier;
                scoreText.setText("Puan : "+score);
                correctCount++;

            } else if(ans.toUpperCase().matches("PÜR")){
                correctAnswers.add(ans.toUpperCase());
                Toast.makeText(getApplicationContext(),"PÜR Doğru Cevap",Toast.LENGTH_SHORT).show();

                P.setText("P");
                P.setBackgroundColor(Color.WHITE);
                U.setText("Ü");
                U.setBackgroundColor(Color.WHITE);
                r2.setText("R");
                r2.setBackgroundColor(Color.WHITE);

                score += 5*multiplier;
                scoreText.setText("Puan : "+score);
                correctCount++;

            } else if(ans.toUpperCase().matches("ÜRE")){
                correctAnswers.add(ans.toUpperCase());
                Toast.makeText(getApplicationContext(),"ÜRE Doğru Cevap",Toast.LENGTH_SHORT).show();

                U3.setText("Ü");
                U3.setBackgroundColor(Color.WHITE);
                r2.setText("R");
                r2.setBackgroundColor(Color.WHITE);
                E2.setText("E");
                E2.setBackgroundColor(Color.WHITE);

                score += 5*multiplier;
                scoreText.setText("Puan : "+score);
                correctCount++;

            } else {
                Toast.makeText(getApplicationContext(),"Aradığınız kelime bulmacada bulunmamaktadır.",Toast.LENGTH_LONG).show();
                multiplier -= 1;
                if(multiplier<0)
                {
                    multiplier = 1; // negatif olursa 1 e esitle
                }
            }
        }
        answer.setText("");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.get_to_scorepage,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.scorepage){
            Intent menuIntent = new Intent(getApplicationContext(), ScorePage3Activity.class);
            menuIntent.putExtra("playerName",intent.getStringExtra("playerName"));//nickname i aktariyoruz.
            //3.1
            menuIntent.putExtra("3.1Score",score);

            startActivity(menuIntent);
        }
        return super.onOptionsItemSelected(item);
    }
}
