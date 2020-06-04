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

public class FirstLevel4Activity extends AppCompatActivity {

    Intent intent,toNext;
    int score,time,multiplier,correctCount;
    ArrayList<String> correctAnswers;
    TextView scoreText,timeText;
    Handler handler;
    Runnable runnable;
    EditText answer;
    String[] letters = {"A","L","M","F","T"};

    TextView A,A2,A3,A4,letterA;
    TextView M,T,F,letterM,letterT,letterF;
    TextView L,L2,letterL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_level4);

        score = 0;
        time = 0;
        multiplier = 5;
        correctCount = 0;
        correctAnswers = new ArrayList<>(6);

        intent = getIntent();

        scoreText = findViewById(R.id.textViewScore14);
        timeText = findViewById(R.id.textViewTime14);
        answer = findViewById(R.id.editTextAnswerEntry14);

        //A lar
        A = findViewById(R.id.textView14A);
        A2 = findViewById(R.id.textView14A2);
        A3 = findViewById(R.id.textView14A3);
        A4 = findViewById(R.id.textView14A4);
        letterA = findViewById(R.id.textViewLetterA14);
        //H,U ve R ler
        M = findViewById(R.id.textView14M);
        T = findViewById(R.id.textView14T);
        F = findViewById(R.id.textView14F);
        letterM = findViewById(R.id.textViewLetterM14);
        letterT = findViewById(R.id.textViewLetterT14);
        letterF = findViewById(R.id.textViewLetterF14);
        //K lar
        L = findViewById(R.id.textView14L);
        L2 = findViewById(R.id.textView14L2);
        letterL = findViewById(R.id.textViewLetterL14);

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
        if(correctCount == 4){
            //Intentle yeni aktiviteye gec ve oyuncuya ilk seviyeyi bitirdigini belirt.
            toNext = new Intent(getApplicationContext(),EndOf4Activity.class);
            toNext.putExtra("playerName",intent.getStringExtra("playerName"));
            //1.1 bilgisi
            toNext.putExtra("1.1Score",intent.getIntExtra("1.1Score",0));
            //1.2 bilgisi
            toNext.putExtra("1.2Score",intent.getIntExtra("1.2Score",0));
            //1.3 bilgisi
            toNext.putExtra("1.3Score",intent.getIntExtra("1.3Score",0));
            //1.4 bilgisi
            toNext.putExtra("1.4Score",score);
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
        letterA.setText(letters[0]);
        letterM.setText(letters[1]);
        letterL.setText(letters[2]);
        letterT.setText(letters[3]);
        letterF.setText(letters[4]);
    }

    public void answerCheck(String ans) {
        if(correctAnswers.contains(ans.toUpperCase())) {
            Toast.makeText(getApplicationContext(),"Bu kelimeyi daha önce buldunuz.",Toast.LENGTH_SHORT).show();
        } else {
            if(ans.toUpperCase().matches("MAT")){
                correctAnswers.add(ans.toUpperCase());
                Toast.makeText(getApplicationContext(),"MAT Doğru Cevap",Toast.LENGTH_SHORT).show();

                M.setText("M");
                M.setBackgroundColor(Color.WHITE);
                A.setText("A");
                A.setBackgroundColor(Color.WHITE);
                T.setText("T");
                T.setBackgroundColor(Color.WHITE);

                score += 4*multiplier;
                scoreText.setText("Puan : "+score);
                correctCount++;

            } else if(ans.toUpperCase().matches("MAL")){
                correctAnswers.add(ans.toUpperCase());
                Toast.makeText(getApplicationContext(),"MAL Doğru Cevap",Toast.LENGTH_SHORT).show();

                M.setText("M");
                M.setBackgroundColor(Color.WHITE);
                A2.setText("A");
                A2.setBackgroundColor(Color.WHITE);
                L.setText("L");
                L.setBackgroundColor(Color.WHITE);

                score += 4*multiplier;
                scoreText.setText("Puan : "+score);
                correctCount++;

            } else if(ans.toUpperCase().matches("LAF")){
                correctAnswers.add(ans.toUpperCase());
                Toast.makeText(getApplicationContext(),"LAF Doğru Cevap",Toast.LENGTH_SHORT).show();

                L.setText("L");
                L.setBackgroundColor(Color.WHITE);
                A3.setText("A");
                A3.setBackgroundColor(Color.WHITE);
                F.setText("F");
                F.setBackgroundColor(Color.WHITE);

                score += 4*multiplier;
                scoreText.setText("Puan : "+score);
                correctCount++;

            } else if(ans.toUpperCase().matches("FAL")){
                correctAnswers.add(ans.toUpperCase());
                Toast.makeText(getApplicationContext(),"FAL Doğru Cevap",Toast.LENGTH_SHORT).show();

                F.setText("F");
                F.setBackgroundColor(Color.WHITE);
                A4.setText("A");
                A4.setBackgroundColor(Color.WHITE);
                L2.setText("L");
                L2.setBackgroundColor(Color.WHITE);

                score += 4*multiplier;
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
            Intent menuIntent = new Intent(getApplicationContext(), ScorePageActivity.class);
            menuIntent.putExtra("playerName",intent.getStringExtra("playerName"));//nickname i aktariyoruz.
            //1.1 seviye puani
            menuIntent.putExtra("1.1Score",intent.getIntExtra("1.1Score",0));
            //1.2 seviye puani
            menuIntent.putExtra("1.2Score",intent.getIntExtra("1.2Score",0));
            //1.3 seviye bilgileri
            menuIntent.putExtra("1.3Score",intent.getIntExtra("1.3Score",0));
            //1.4 seviye bilgileri
            menuIntent.putExtra("1.4Score",score);
            startActivity(menuIntent);
        }
        return super.onOptionsItemSelected(item);
    }
}
