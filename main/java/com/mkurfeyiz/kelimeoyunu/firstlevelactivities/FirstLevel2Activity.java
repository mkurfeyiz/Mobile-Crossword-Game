package com.mkurfeyiz.kelimeoyunu.firstlevelactivities;

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

public class FirstLevel2Activity extends AppCompatActivity {
    Intent intent,to12Intent;
    int score,time,multiplier,correctCount,totalScore;
    ArrayList<String> correctAnswers;
    TextView scoreText,timeText;
    Handler handler;
    Runnable runnable;
    EditText answer;
    String[] letters = {"A","S","L","N","K"};

    TextView A,A2,A3,letterA;
    TextView L,N,letterL,letterN;
    TextView S,K,letterS,letterK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_level2);

        score = 0;
        multiplier = 5;
        time = 0;
        correctCount = 0;
        correctAnswers = new ArrayList<>(6);

        scoreText = findViewById(R.id.textViewScore12);
        timeText = findViewById(R.id.textViewTime12);
        answer = findViewById(R.id.editTextAnswerEntry12);

        intent = getIntent();

        //A lar
        A = findViewById(R.id.textView12A);
        A2 = findViewById(R.id.textView12A2);
        A3 = findViewById(R.id.textView12A3);
        letterA = findViewById(R.id.textViewLetterA12);
        //L ve N ler
        L = findViewById(R.id.textView12L);
        N = findViewById(R.id.textView12N);
        letterL = findViewById(R.id.textViewLetterL12);
        letterN = findViewById(R.id.textViewLetterN12);
        //K ve S
        K = findViewById(R.id.textView12K);
        S = findViewById(R.id.textView12S);
        letterK = findViewById(R.id.textViewLetterK12);
        letterS = findViewById(R.id.textViewLetterS12);

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
            to12Intent = new Intent(getApplicationContext(),EndOf2Activity.class);
            to12Intent.putExtra("playerName",intent.getStringExtra("playerName"));
            //1.1 bilgisi
            to12Intent.putExtra("1.1TopScore",intent.getIntExtra("1.1TopScore",0));
            to12Intent.putExtra("1.1Score",intent.getIntExtra("1.1Score",0));
            //1.2 bilgisi
            to12Intent.putExtra("1.2Score",score);
            startActivity(to12Intent);
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
        letterA.setText(letters[0]);
        letterS.setText(letters[1]);
        letterK.setText(letters[2]);
        letterL.setText(letters[3]);
        letterN.setText(letters[4]);
    }

    public void answerCheck(String ans) {
        if(correctAnswers.contains(ans.toUpperCase())) {
            Toast.makeText(getApplicationContext(),"Bu kelimeyi daha önce buldunuz.",Toast.LENGTH_SHORT).show();
        } else {
            if(ans.toUpperCase().matches("SAL")){
                correctAnswers.add(ans.toUpperCase());
                Toast.makeText(getApplicationContext(),"SAL Doğru Cevap",Toast.LENGTH_SHORT).show();

                S.setText("S");
                S.setBackgroundColor(Color.WHITE);
                A.setText("A");
                A.setBackgroundColor(Color.WHITE);
                L.setText("L");
                L.setBackgroundColor(Color.WHITE);

                score += 2*multiplier;
                scoreText.setText("Puan : "+score);
                correctCount++;

            } else if(ans.toUpperCase().matches("KAL")){
                correctAnswers.add(ans.toUpperCase());
                Toast.makeText(getApplicationContext(),"KAL Doğru Cevap",Toast.LENGTH_SHORT).show();

                K.setText("K");
                K.setBackgroundColor(Color.WHITE);
                A2.setText("A");
                A2.setBackgroundColor(Color.WHITE);
                L.setText("L");
                L.setBackgroundColor(Color.WHITE);

                score += 2*multiplier;
                scoreText.setText("Puan : "+score);
                correctCount++;

            } else if(ans.toUpperCase().matches("KAN")) {
                correctAnswers.add(ans.toUpperCase());
                Toast.makeText(getApplicationContext(),"KAN Doğru Cevap",Toast.LENGTH_SHORT).show();

                K.setText("K");
                K.setBackgroundColor(Color.WHITE);
                A3.setText("A");
                A3.setBackgroundColor(Color.WHITE);
                N.setText("N");
                N.setBackgroundColor(Color.WHITE);

                score += 2*multiplier;
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
            totalScore += score;
            Intent menuIntent = new Intent(getApplicationContext(), ScorePageActivity.class);
            menuIntent.putExtra("playerName",intent.getStringExtra("playerName"));//nickname i aktariyoruz.
            //1.1 seviye puani
            menuIntent.putExtra("1.1Score",intent.getIntExtra("1.1Score",0));
            //1.2 seviye puani
            menuIntent.putExtra("1.2Score",score);
            startActivity(menuIntent);
        }
        return super.onOptionsItemSelected(item);
    }
}
