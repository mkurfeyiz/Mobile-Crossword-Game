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

public class ThirdLevel5Activity extends AppCompatActivity {

    Intent intent,toNext;
    int score,time,multiplier,correctCount;
    ArrayList<String> correctAnswers;
    TextView scoreText,timeText;
    Handler handler;
    Runnable runnable;
    EditText answer;
    String[] letters = {"B","K","T","İ"};

    TextView T,T2,letterT;
    TextView K,K2,K3,letterK;
    TextView B,B2,letterB;
    TextView I,I2,I3,I4,I5,I6,I7,letterI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_level5);

        score = 0;
        time = 0;
        multiplier = 10;
        correctCount = 0;
        correctAnswers = new ArrayList<>(6);

        intent = getIntent();

        scoreText = findViewById(R.id.textViewScore35);
        timeText = findViewById(R.id.textViewTime35);
        answer = findViewById(R.id.editTextAnswerEntry35);

        //A lar
        B = findViewById(R.id.textView35B);
        B2 = findViewById(R.id.textView35B2);
        letterB = findViewById(R.id.textViewLetterB35);
        //T ler
        T = findViewById(R.id.textView35T);
        T2 = findViewById(R.id.textView35T2);
        letterT = findViewById(R.id.textViewLetterT35);
        //L ler
        K = findViewById(R.id.textView35K);
        K2 = findViewById(R.id.textView35K2);
        K3 = findViewById(R.id.textView35K3);
        letterK = findViewById(R.id.textViewLetterK35);
        //I lar
        I = findViewById(R.id.textView35I);
        I2 = findViewById(R.id.textView35I2);
        I3 = findViewById(R.id.textView35I3);
        I4 = findViewById(R.id.textView35I4);
        I5 = findViewById(R.id.textView35I5);
        I6 = findViewById(R.id.textView35I6);
        I7 = findViewById(R.id.textView35I7);
        letterI = findViewById(R.id.textViewLetterI35);

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
            toNext = new Intent(getApplicationContext(), EndOf35Activity.class);
            toNext.putExtra("playerName",intent.getStringExtra("playerName"));
            //3.1
            toNext.putExtra("3.1Score",intent.getIntExtra("3.1Score",0));
            //3.2
            toNext.putExtra("3.2Score",intent.getIntExtra("3.2Score",0));
            //3.3
            toNext.putExtra("3.3Score",intent.getIntExtra("3.3Score",0));
            //3.4
            toNext.putExtra("3.4Score",intent.getIntExtra("3.4Score",0));
            //3.5
            toNext.putExtra("3.5Score",score);

            startActivity(toNext);
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
        letterB.setText(letters[0]);
        letterK.setText(letters[1]);
        letterT.setText(letters[2]);
        letterI.setText(letters[3]);
    }

    public void answerCheck(String ans) {
        if(correctAnswers.contains(ans.toUpperCase())) {
            Toast.makeText(getApplicationContext(),"Bu kelimeyi daha önce buldunuz.",Toast.LENGTH_SHORT).show();
        } else {
            if(ans.toUpperCase().matches("IBIK")){
                correctAnswers.add(ans.toUpperCase());
                Toast.makeText(getApplicationContext(),"İBİK Doğru Cevap",Toast.LENGTH_SHORT).show();

                I.setText("İ");
                I.setBackgroundColor(Color.WHITE);
                B.setText("B");
                B.setBackgroundColor(Color.WHITE);
                I2.setText("İ");
                I2.setBackgroundColor(Color.WHITE);
                K.setText("K");
                K.setBackgroundColor(Color.WHITE);

                score += 5*multiplier;
                scoreText.setText("Puan : "+score);
                correctCount++;

            } else if(ans.toUpperCase().matches("BITIK")){
                correctAnswers.add(ans.toUpperCase());
                Toast.makeText(getApplicationContext(),"BİTİK Doğru Cevap",Toast.LENGTH_SHORT).show();

                B.setText("B");
                B.setBackgroundColor(Color.WHITE);
                I3.setText("İ");
                I3.setBackgroundColor(Color.WHITE);
                T.setText("T");
                T.setBackgroundColor(Color.WHITE);
                I7.setText("İ");
                I7.setBackgroundColor(Color.WHITE);
                K3.setText("K");
                K3.setBackgroundColor(Color.WHITE);

                score += 5*multiplier;
                scoreText.setText("Puan : "+score);
                correctCount++;

            } else if(ans.toUpperCase().matches("BITKI")){
                correctAnswers.add(ans.toUpperCase());
                Toast.makeText(getApplicationContext(),"BİTKİ Doğru Cevap",Toast.LENGTH_SHORT).show();

                B2.setText("B");
                B2.setBackgroundColor(Color.WHITE);
                I5.setText("İ");
                I5.setBackgroundColor(Color.WHITE);
                T.setText("T");
                T.setBackgroundColor(Color.WHITE);
                K2.setText("K");
                K2.setBackgroundColor(Color.WHITE);
                I4.setText("İ");
                I4.setBackgroundColor(Color.WHITE);


                score += 5*multiplier;
                scoreText.setText("Puan : "+score);
                correctCount++;

            } else if(ans.toUpperCase().matches("BIT")){
                correctAnswers.add(ans.toUpperCase());
                Toast.makeText(getApplicationContext(),"BİT Doğru Cevap",Toast.LENGTH_SHORT).show();

                B2.setText("B");
                B2.setBackgroundColor(Color.WHITE);
                I6.setText("İ");
                I6.setBackgroundColor(Color.WHITE);
                T2.setText("T");
                T2.setBackgroundColor(Color.WHITE);


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
            menuIntent.putExtra("3.1Score",intent.getIntExtra("3.1Score",0));
            //3.2
            menuIntent.putExtra("3.2Score",intent.getIntExtra("3.2Score",0));
            //3.3
            menuIntent.putExtra("3.3Score",intent.getIntExtra("3.3Score",0));
            //3.4
            menuIntent.putExtra("3.4Score",intent.getIntExtra("3.4Score",0));
            //3.5
            menuIntent.putExtra("3.5Score",score);

            startActivity(menuIntent);
        }
        return super.onOptionsItemSelected(item);
    }
}
