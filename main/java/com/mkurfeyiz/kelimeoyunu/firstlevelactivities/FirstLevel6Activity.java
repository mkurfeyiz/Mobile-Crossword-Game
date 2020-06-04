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

public class FirstLevel6Activity extends AppCompatActivity {
    //Ortak degiskenler
    Intent intent,to12Intent;
    int score,time,multiplier,correctCount;
    ArrayList<String> correctAnswers;
    TextView scoreText,timeText;
    Handler handler;
    Runnable runnable;
    EditText answer;
    String[] letters = {"S","T","K","E"};
    //Ortak olmayanlar
    TextView E,E2,E3,letterE;
    TextView T,T2,letterT;
    TextView S,K,letterS,letterK;
    ArrayList<TextView> eTexts = new ArrayList<>();
    ArrayList<TextView> tTexts = new ArrayList<>();
    ArrayList<TextView> kTexts = new ArrayList<>();
    ArrayList<TextView> sTexts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_level6);


        //Ortak degiskenler
        multiplier = 5;
        score = 0;
        time = 0;
        correctCount = 0;
        correctAnswers = new ArrayList<>(6);

        scoreText = findViewById(R.id.textViewScore11);
        timeText = findViewById(R.id.textViewTime11);
        answer = findViewById(R.id.editTextAnswerEntry11);

        intent = getIntent();
        //buraya kadar

        //E ler
        E = findViewById(R.id.textView11E);
        E2 = findViewById(R.id.textView11E2);
        E3 = findViewById(R.id.textView11E3);
        letterE = findViewById(R.id.textViewLetterE11);
        //T ler
        T = findViewById(R.id.textView11T);
        T2 = findViewById(R.id.textView11T2);
        letterT = findViewById(R.id.textViewLetterT11);
        //K ve S
        K = findViewById(R.id.textView11K);
        S = findViewById(R.id.textView11S);
        letterK = findViewById(R.id.textViewLetterK11);
        letterS = findViewById(R.id.textViewLetterS11);

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
            to12Intent = new Intent(getApplicationContext(),EndOf6Activity.class);
            to12Intent.putExtra("playerName",intent.getStringExtra("playerName"));
            to12Intent.putExtra("1.1Score",score);
            startActivity(to12Intent);
        }
    }

    public void help(View view) {
        String temp;
        Random random =new Random();
        int index1,index2;
        for (int i=0;i<letters.length;i++){
            index1 = random.nextInt(4);
            index2 = random.nextInt(4);
            temp = letters[index1];
            letters[index1] = letters[index2];
            letters[index2] = temp;
        }
        letterS.setText(letters[0]);
        letterT.setText(letters[1]);
        letterK.setText(letters[2]);
        letterE.setText(letters[3]);
    }

    public void answerCheck(String ans) {
        if(correctAnswers.contains(ans.toUpperCase())) {
            Toast.makeText(getApplicationContext(),"Bu kelimeyi daha önce buldunuz.",Toast.LENGTH_SHORT).show();
        } else {
            if(ans.toUpperCase().matches("TEK")){
                correctAnswers.add(ans.toUpperCase());
                Toast.makeText(getApplicationContext(),"TEK Doğru Cevap",Toast.LENGTH_SHORT).show();

                T.setText("T");
                T.setBackgroundColor(Color.WHITE);
                E.setText("E");
                E.setBackgroundColor(Color.WHITE);
                K.setText("K");
                K.setBackgroundColor(Color.WHITE);

                score += 2*multiplier;
                scoreText.setText("Puan : "+score);
                correctCount++;

            } else if(ans.toUpperCase().matches("SEK")){
                correctAnswers.add(ans.toUpperCase());
                Toast.makeText(getApplicationContext(),"SEK Doğru Cevap",Toast.LENGTH_SHORT).show();

                S.setText("S");
                S.setBackgroundColor(Color.WHITE);
                E2.setText("E");
                E2.setBackgroundColor(Color.WHITE);
                K.setText("K");
                K.setBackgroundColor(Color.WHITE);

                score += 2*multiplier;
                scoreText.setText("Puan : "+score);
                correctCount++;

            } else if(ans.toUpperCase().matches("SET")) {
                correctAnswers.add(ans.toUpperCase());
                Toast.makeText(getApplicationContext(),"SET Doğru Cevap",Toast.LENGTH_SHORT).show();

                S.setText("S");
                S.setBackgroundColor(Color.WHITE);
                E3.setText("E");
                E3.setBackgroundColor(Color.WHITE);
                T2.setText("T");
                T2.setBackgroundColor(Color.WHITE);

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
            Intent menuIntent = new Intent(getApplicationContext(), ScorePageActivity.class);
            menuIntent.putExtra("playerName",intent.getStringExtra("playerName"));//nickname i aktariyoruz.
            //1. seviye bilgileri
            menuIntent.putExtra("1.1Score",score);
            startActivity(menuIntent);
        }
        return super.onOptionsItemSelected(item);
    }
}
