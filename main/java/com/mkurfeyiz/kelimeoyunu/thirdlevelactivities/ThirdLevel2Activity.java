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

public class ThirdLevel2Activity extends AppCompatActivity {

    Intent intent,toNext;
    int score,time,multiplier,correctCount;
    ArrayList<String> correctAnswers;
    TextView scoreText,timeText;
    Handler handler;
    Runnable runnable;
    EditText answer;
    String[] letters = {"B","L","K","I","A"};

    TextView K,K2,letterK;
    TextView I,I2,letterI;
    TextView L,L2,letterL;
    TextView A,A2,A3,letterA;
    TextView B,letterB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_level2);

        score = 0;
        time = 0;
        multiplier = 10;
        correctCount = 0;
        correctAnswers = new ArrayList<>(6);

        intent = getIntent();

        scoreText = findViewById(R.id.textViewScore32);
        timeText = findViewById(R.id.textViewTime32);
        answer = findViewById(R.id.editTextAnswerEntry32);

        //K lar
        K = findViewById(R.id.textView32K);
        K2 = findViewById(R.id.textView32K2);
        letterK = findViewById(R.id.textViewLetterK32);
        //R ler
        I = findViewById(R.id.textView32I);
        I2 = findViewById(R.id.textView32I2);
        letterI = findViewById(R.id.textViewLetterI32);
        //L ler
        L = findViewById(R.id.textView32L);
        L2 = findViewById(R.id.textView32L2);
        letterL = findViewById(R.id.textViewLetterL32);
        //A lar
        A = findViewById(R.id.textView32A);
        A2 = findViewById(R.id.textView32A2);
        A3 = findViewById(R.id.textView32A3);
        letterA = findViewById(R.id.textViewLetterA32);
        //B ler
        B = findViewById(R.id.textView32B);
        letterB = findViewById(R.id.textViewLetterB32);

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
            toNext = new Intent(getApplicationContext(), EndOf32Activity.class);
            toNext.putExtra("playerName",intent.getStringExtra("playerName"));
            //3.1
            toNext.putExtra("3.1Score",intent.getIntExtra("3.1Score",0));
            //3.2
            toNext.putExtra("3.2Score",score);

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
        letterB.setText(letters[0]);
        letterL.setText(letters[1]);
        letterK.setText(letters[2]);
        letterI.setText(letters[3]);
        letterA.setText(letters[4]);
    }

    public void answerCheck(String ans) {
        if(correctAnswers.contains(ans.toUpperCase())) {
            Toast.makeText(getApplicationContext(),"Bu kelimeyi daha önce buldunuz.",Toast.LENGTH_SHORT).show();
        } else {
            if(ans.toUpperCase().matches("BALIK")){
                correctAnswers.add(ans.toUpperCase());
                Toast.makeText(getApplicationContext(),"BALIK Doğru Cevap",Toast.LENGTH_SHORT).show();

                B.setText("B");
                B.setBackgroundColor(Color.WHITE);
                A.setText("A");
                A.setBackgroundColor(Color.WHITE);
                L.setText("L");
                L.setBackgroundColor(Color.WHITE);
                I.setText("I");
                I.setBackgroundColor(Color.WHITE);
                K.setText("K");
                K.setBackgroundColor(Color.WHITE);

                score += 5*multiplier;
                scoreText.setText("Puan : "+score);
                correctCount++;

            } else if(ans.toUpperCase().matches("AKIL")){
                correctAnswers.add(ans.toUpperCase());
                Toast.makeText(getApplicationContext(),"AKIL Doğru Cevap",Toast.LENGTH_SHORT).show();

                A2.setText("A");
                A2.setBackgroundColor(Color.WHITE);
                K.setText("K");
                K.setBackgroundColor(Color.WHITE);
                I2.setText("I");
                I2.setBackgroundColor(Color.WHITE);
                L2.setText("L");
                L2.setBackgroundColor(Color.WHITE);

                score += 5*multiplier;
                scoreText.setText("Puan : "+score);
                correctCount++;

            } else if(ans.toUpperCase().matches("KAL")){
                correctAnswers.add(ans.toUpperCase());
                Toast.makeText(getApplicationContext(),"KAL Doğru Cevap",Toast.LENGTH_SHORT).show();

                K2.setText("K");
                K2.setBackgroundColor(Color.WHITE);
                A3.setText("A");
                A3.setBackgroundColor(Color.WHITE);
                L2.setText("L");
                L2.setBackgroundColor(Color.WHITE);

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
            menuIntent.putExtra("3.2Score",score);

            startActivity(menuIntent);
        }
        return super.onOptionsItemSelected(item);
    }
}
