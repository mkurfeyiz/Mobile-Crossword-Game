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

public class FirstLevel5Activity extends AppCompatActivity {

    Intent intent,toNext;
    int score,time,multiplier,correctCount;
    ArrayList<String> correctAnswers;
    TextView scoreText,timeText;
    Handler handler;
    Runnable runnable;
    EditText answer;
    String[] letters = {"A","İ","K","B","T"};

    TextView A,A2,letterA;
    TextView T,T2,letterT;
    TextView I,I2,letterI;
    TextView K,K2,letterK;
    TextView B,letterB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_level5);

        score = 0;
        time = 0;
        multiplier = 5;
        correctCount = 0;
        correctAnswers = new ArrayList<>(6);

        intent = getIntent();

        scoreText = findViewById(R.id.textViewScore15);
        timeText = findViewById(R.id.textViewTime15);
        answer = findViewById(R.id.editTextAnswerEntry15);

        //A lar
        A = findViewById(R.id.textView15A);
        A2 = findViewById(R.id.textView15A2);
        letterA = findViewById(R.id.textViewLetterA15);
        //T ler
        T = findViewById(R.id.textView15T);
        T2 = findViewById(R.id.textView15T2);
        letterT = findViewById(R.id.textViewLetterT15);
        //K lar
        K = findViewById(R.id.textView15K);
        K2 = findViewById(R.id.textView15K2);
        letterK = findViewById(R.id.textViewLetterK15);
        //I ler
        I = findViewById(R.id.textView15I);
        I2 = findViewById(R.id.textView15I2);
        letterI = findViewById(R.id.textViewLetterI15);
        //B ler
        B = findViewById(R.id.textView15B);
        letterB = findViewById(R.id.textViewLetterB15);

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
            toNext = new Intent(getApplicationContext(),EndOf5Activity.class);
            toNext.putExtra("playerName",intent.getStringExtra("playerName"));
            //1.1 bilgisi
            toNext.putExtra("1.1Score",intent.getIntExtra("1.1Score",0));
            //1.2 bilgisi
            toNext.putExtra("1.2Score",intent.getIntExtra("1.2Score",0));
            //1.3 bilgisi
            toNext.putExtra("1.3Score",intent.getIntExtra("1.3Score",0));
            //1.4 bilgisi
            toNext.putExtra("1.4Score",intent.getIntExtra("1.4Score",0));
            //1.5
            toNext.putExtra("1.5Score",score);
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
        letterK.setText(letters[1]);
        letterB.setText(letters[2]);
        letterT.setText(letters[3]);
        letterI.setText(letters[4]);
    }

    public void answerCheck(String ans) {
        if(correctAnswers.contains(ans.toUpperCase())) {
            Toast.makeText(getApplicationContext(),"Bu kelimeyi daha önce buldunuz.",Toast.LENGTH_SHORT).show();
        } else {
            if(ans.toUpperCase().matches("TAK")){
                correctAnswers.add(ans.toUpperCase());
                Toast.makeText(getApplicationContext(),"TAK Doğru Cevap",Toast.LENGTH_SHORT).show();

                T.setText("T");
                T.setBackgroundColor(Color.WHITE);
                A.setText("A");
                A.setBackgroundColor(Color.WHITE);
                K.setText("K");
                K.setBackgroundColor(Color.WHITE);

                score += 4*multiplier;
                scoreText.setText("Puan : "+score);
                correctCount++;

            } else if(ans.toUpperCase().matches("BAK")){
                correctAnswers.add(ans.toUpperCase());
                Toast.makeText(getApplicationContext(),"BAK Doğru Cevap",Toast.LENGTH_SHORT).show();

                B.setText("B");
                B.setBackgroundColor(Color.WHITE);
                A2.setText("A");
                A2.setBackgroundColor(Color.WHITE);
                K.setText("K");
                K.setBackgroundColor(Color.WHITE);

                score += 4*multiplier;
                scoreText.setText("Puan : "+score);
                correctCount++;

            } else if(ans.toUpperCase().matches("BIT")){
                correctAnswers.add(ans.toUpperCase());
                Toast.makeText(getApplicationContext(),"BİT Doğru Cevap",Toast.LENGTH_SHORT).show();

                B.setText("B");
                B.setBackgroundColor(Color.WHITE);
                I.setText("İ");
                I.setBackgroundColor(Color.WHITE);
                T2.setText("T");
                T2.setBackgroundColor(Color.WHITE);

                score += 4*multiplier;
                scoreText.setText("Puan : "+score);
                correctCount++;

            } else if(ans.toUpperCase().matches("TIK")){
                correctAnswers.add(ans.toUpperCase());
                Toast.makeText(getApplicationContext(),"TİK Doğru Cevap",Toast.LENGTH_SHORT).show();

                T2.setText("T");
                T2.setBackgroundColor(Color.WHITE);
                I2.setText("İ");
                I2.setBackgroundColor(Color.WHITE);
                K2.setText("K");
                K2.setBackgroundColor(Color.WHITE);

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
            menuIntent.putExtra("1.4Score",intent.getIntExtra("1.4Score",0));
            //1.5
            menuIntent.putExtra("1.5Score",score);
            startActivity(menuIntent);
        }
        return super.onOptionsItemSelected(item);
    }
}
