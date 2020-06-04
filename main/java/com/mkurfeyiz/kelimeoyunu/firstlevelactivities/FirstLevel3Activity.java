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

public class FirstLevel3Activity extends AppCompatActivity {
    Intent intent,toNext;
    int score,time,multiplier,correctCount;
    ArrayList<String> correctAnswers;
    TextView scoreText,timeText;
    Handler handler;
    Runnable runnable;
    EditText answer;
    String[] letters = {"H","K","U","A","R"};

    TextView A,A2,letterA;
    TextView H,U,r,letterH,letterU,letterR;
    TextView K,K2,letterK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_level3);

        score = 0;
        time = 0;
        multiplier = 5;
        correctCount = 0;
        correctAnswers = new ArrayList<>(6);

        intent = getIntent();

        scoreText = findViewById(R.id.textViewScore13);
        timeText = findViewById(R.id.textViewTime13);
        answer = findViewById(R.id.editTextAnswerEntry13);

        //A lar
        A = findViewById(R.id.textView13A);
        A2 = findViewById(R.id.textView13A2);
        letterA = findViewById(R.id.textViewLetterA13);
        //H,U ve R ler
        H = findViewById(R.id.textView13H);
        U = findViewById(R.id.textView13U);
        r = findViewById(R.id.textView13R);
        letterH = findViewById(R.id.textViewLetterH13);
        letterU = findViewById(R.id.textViewLetterU13);
        letterR = findViewById(R.id.textViewLetterR13);
        //K lar
        K = findViewById(R.id.textView13K);
        K2 = findViewById(R.id.textView13K2);
        letterK = findViewById(R.id.textViewLetterK13);

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
            toNext = new Intent(getApplicationContext(),EndOf3Activity.class);
            toNext.putExtra("playerName",intent.getStringExtra("playerName"));
            //1.1 bilgisi
            toNext.putExtra("1.1TopScore",intent.getIntExtra("1.1TopScore",0));
            toNext.putExtra("1.1Score",intent.getIntExtra("1.1Score",0));
            //1.2 bilgisi
            toNext.putExtra("1.2TopScore",intent.getIntExtra("1.2TopScore",0));
            toNext.putExtra("1.2Score",intent.getIntExtra("1.2Score",0));
            //1.3 bilgisi
            toNext.putExtra("1.3Score",score);
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
        letterH.setText(letters[1]);
        letterK.setText(letters[2]);
        letterR.setText(letters[3]);
        letterU.setText(letters[4]);
    }

    public void answerCheck(String ans) {
        if(correctAnswers.contains(ans.toUpperCase())) {
            Toast.makeText(getApplicationContext(),"Bu kelimeyi daha önce buldunuz.",Toast.LENGTH_SHORT).show();
        } else {
            if(ans.toUpperCase().matches("HAK")){
                correctAnswers.add(ans.toUpperCase());
                Toast.makeText(getApplicationContext(),"HAK Doğru Cevap",Toast.LENGTH_SHORT).show();

                H.setText("H");
                H.setBackgroundColor(Color.WHITE);
                A.setText("A");
                A.setBackgroundColor(Color.WHITE);
                K.setText("K");
                K.setBackgroundColor(Color.WHITE);

                score += 3*multiplier;
                scoreText.setText("Puan : "+score);
                correctCount++;

            } else if(ans.toUpperCase().matches("KUR")){
                correctAnswers.add(ans.toUpperCase());
                Toast.makeText(getApplicationContext(),"KUR Doğru Cevap",Toast.LENGTH_SHORT).show();

                K.setText("K");
                K.setBackgroundColor(Color.WHITE);
                U.setText("U");
                U.setBackgroundColor(Color.WHITE);
                r.setText("R");
                r.setBackgroundColor(Color.WHITE);

                score += 3*multiplier;
                scoreText.setText("Puan : "+score);
                correctCount++;

            } else if(ans.toUpperCase().matches("KAR")){
                correctAnswers.add(ans.toUpperCase());
                Toast.makeText(getApplicationContext(),"KAR Doğru Cevap",Toast.LENGTH_SHORT).show();

                K2.setText("K");
                K2.setBackgroundColor(Color.WHITE);
                A2.setText("A");
                A2.setBackgroundColor(Color.WHITE);
                r.setText("R");
                r.setBackgroundColor(Color.WHITE);

                score += 3*multiplier;
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
            menuIntent.putExtra("1.3Score",score);
            startActivity(menuIntent);
        }
        return super.onOptionsItemSelected(item);
    }
}
