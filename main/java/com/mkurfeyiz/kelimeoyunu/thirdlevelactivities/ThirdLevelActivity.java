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
import com.mkurfeyiz.kelimeoyunu.firstlevelactivities.ScorePageActivity;

import java.util.ArrayList;
import java.util.Random;

public class ThirdLevelActivity extends AppCompatActivity {
    Intent intent,toNext;
    int score,time,multiplier,correctCount;
    ArrayList<String> correctAnswers;
    TextView scoreText,timeText;
    Handler handler;
    Runnable runnable;
    EditText answer;
    String[] letters = {"A","K","L","U","M","N","S"};

    TextView S,S2,letterS;
    TextView U,M,N,N2,letterU,letterM,letterN;
    TextView K,K2,K3,K4,K5,letterK;
    TextView L,L2,L3,letterL;
    TextView A,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,letterA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_level);

        score = 0;
        time = 0;
        multiplier = 10;
        correctCount = 0;
        correctAnswers = new ArrayList<>(6);

        intent = getIntent();

        scoreText = findViewById(R.id.textViewScore3);
        timeText = findViewById(R.id.textViewTime3);
        answer = findViewById(R.id.editText3AnswerEntry);

        //U ve M ler
        U = findViewById(R.id.textView3U);
        M = findViewById(R.id.textView3M);
        letterU = findViewById(R.id.textViewLetter3U);
        letterM = findViewById(R.id.textViewLetter3M);
        //N ler
        N = findViewById(R.id.textView3N);
        N2 = findViewById(R.id.textView3N2);
        letterN = findViewById(R.id.textViewLetter3N);
        //S ler
        S = findViewById(R.id.textView3S);
        S2 = findViewById(R.id.textView3S2);
        letterS = findViewById(R.id.textViewLetter3S);
        //K lar
        K = findViewById(R.id.textView3K);
        K2 = findViewById(R.id.textView3K2);
        K3 = findViewById(R.id.textView3K3);
        K4 = findViewById(R.id.textView3K4);
        K5 = findViewById(R.id.textView3K5);
        letterK = findViewById(R.id.textViewLetter3K);
        //A lar
        A = findViewById(R.id.textView3A);
        A2 = findViewById(R.id.textView3A2);
        A3 = findViewById(R.id.textView3A3);
        A4 = findViewById(R.id.textView3A4);
        A5 = findViewById(R.id.textView3A5);
        A6 = findViewById(R.id.textView3A6);
        A7 = findViewById(R.id.textView3A7);
        A8 = findViewById(R.id.textView3A8);
        A9 = findViewById(R.id.textView3A9);
        A10 = findViewById(R.id.textView3A10);
        A11 = findViewById(R.id.textView3A11);
        letterA = findViewById(R.id.textViewLetter3A);
        //L ler
        L = findViewById(R.id.textView3L);
        L2 = findViewById(R.id.textView3L2);
        L3 = findViewById(R.id.textView3L3);
        letterL = findViewById(R.id.textViewLetter3L);

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
        if(correctCount == 6){
            //Intentle yeni aktiviteye gec ve oyuncuya ilk seviyeyi bitirdigini belirt.
            toNext = new Intent(getApplicationContext(), EndOfThirdLvlActivity.class);
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
            toNext.putExtra("3.5Score",intent.getIntExtra("3.5Score",0));
            //3.6
            toNext.putExtra("3.6Score",score);

            startActivity(toNext);
        }
    }

    public void help(View view) {
        String temp;
        Random random =new Random();
        int index1,index2;
        for (int i=0;i<letters.length;i++){
            index1 = random.nextInt(7);
            index2 = random.nextInt(7);
            temp = letters[index1];
            letters[index1] = letters[index2];
            letters[index2] = temp;
        }
        letterA.setText(letters[0]);
        letterK.setText(letters[1]);
        letterL.setText(letters[2]);
        letterN.setText(letters[3]);
        letterS.setText(letters[4]);
        letterM.setText(letters[5]);
        letterU.setText(letters[6]);
    }

    public void answerCheck(String ans) {
        if(correctAnswers.contains(ans.toUpperCase())) {
            Toast.makeText(getApplicationContext(),"Bu kelimeyi daha önce buldunuz.",Toast.LENGTH_SHORT).show();
        } else {
            if(ans.toUpperCase().matches("AKALA")){
                correctAnswers.add(ans.toUpperCase());
                Toast.makeText(getApplicationContext(),"AKALA Doğru Cevap",Toast.LENGTH_SHORT).show();

                A.setText("A");
                A.setBackgroundColor(Color.WHITE);
                K.setText("K");
                K.setBackgroundColor(Color.WHITE);
                A2.setText("A");
                A2.setBackgroundColor(Color.WHITE);
                L.setText("L");
                L.setBackgroundColor(Color.WHITE);
                A3.setText("A");
                A3.setBackgroundColor(Color.WHITE);

                score += 5*multiplier;
                scoreText.setText("Puan : "+score);
                correctCount++;

            } else if(ans.toUpperCase().matches("UKALA")){
                correctAnswers.add(ans.toUpperCase());
                Toast.makeText(getApplicationContext(),"UKALA Doğru Cevap",Toast.LENGTH_SHORT).show();

                U.setText("U");
                U.setBackgroundColor(Color.WHITE);
                K.setText("K");
                K.setBackgroundColor(Color.WHITE);
                A4.setText("A");
                A4.setBackgroundColor(Color.WHITE);
                L2.setText("L");
                L2.setBackgroundColor(Color.WHITE);
                A5.setText("A");
                A5.setBackgroundColor(Color.WHITE);

                score += 5*multiplier;
                scoreText.setText("Puan : "+score);
                correctCount++;

            } else if(ans.toUpperCase().matches("AKMAK")){
                correctAnswers.add(ans.toUpperCase());
                Toast.makeText(getApplicationContext(),"AKMAK Doğru Cevap",Toast.LENGTH_SHORT).show();

                A5.setText("A");
                A5.setBackgroundColor(Color.WHITE);
                K2.setText("K");
                K2.setBackgroundColor(Color.WHITE);
                M.setText("M");
                M.setBackgroundColor(Color.WHITE);
                A6.setText("A");
                A6.setBackgroundColor(Color.WHITE);
                K3.setText("K");
                K3.setBackgroundColor(Color.WHITE);


                score += 5*multiplier;
                scoreText.setText("Puan : "+score);
                correctCount++;

            } else if(ans.toUpperCase().matches("AKSAK")){
                correctAnswers.add(ans.toUpperCase());
                Toast.makeText(getApplicationContext(),"AKSAK Doğru Cevap",Toast.LENGTH_SHORT).show();

                A7.setText("A");
                A7.setBackgroundColor(Color.WHITE);
                K3.setText("K");
                K3.setBackgroundColor(Color.WHITE);
                S.setText("S");
                S.setBackgroundColor(Color.WHITE);
                A9.setText("A");
                A9.setBackgroundColor(Color.WHITE);
                K5.setText("K");
                K5.setBackgroundColor(Color.WHITE);


                score += 5*multiplier;
                scoreText.setText("Puan : "+score);
                correctCount++;

            } else if(ans.toUpperCase().matches("AKSAN")){
                correctAnswers.add(ans.toUpperCase());
                Toast.makeText(getApplicationContext(),"AKSAN Doğru Cevap",Toast.LENGTH_SHORT).show();

                A10.setText("A");
                A10.setBackgroundColor(Color.WHITE);
                K5.setText("K");
                K5.setBackgroundColor(Color.WHITE);
                S2.setText("S");
                S2.setBackgroundColor(Color.WHITE);
                A11.setText("A");
                A11.setBackgroundColor(Color.WHITE);
                N2.setText("N");
                N2.setBackgroundColor(Color.WHITE);


                score += 5*multiplier;
                scoreText.setText("Puan : "+score);
                correctCount++;

            } else if(ans.toUpperCase().matches("AKLAN")){
                correctAnswers.add(ans.toUpperCase());
                Toast.makeText(getApplicationContext(),"AKLAN Doğru Cevap",Toast.LENGTH_SHORT).show();

                A7.setText("A");
                A7.setBackgroundColor(Color.WHITE);
                K4.setText("K");
                K4.setBackgroundColor(Color.WHITE);
                L3.setText("L");
                L3.setBackgroundColor(Color.WHITE);
                A8.setText("A");
                A8.setBackgroundColor(Color.WHITE);
                N.setText("N");
                N.setBackgroundColor(Color.WHITE);


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
            menuIntent.putExtra("3.5Score",intent.getIntExtra("3.5Score",0));
            //3.6
            menuIntent.putExtra("3.6Score",score);

            startActivity(menuIntent);
        }
        return super.onOptionsItemSelected(item);
    }

}
