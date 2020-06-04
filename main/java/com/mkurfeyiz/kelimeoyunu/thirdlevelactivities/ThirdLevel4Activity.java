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

public class ThirdLevel4Activity extends AppCompatActivity {

    Intent intent,toNext;
    int score,time,multiplier,correctCount;
    ArrayList<String> correctAnswers;
    TextView scoreText,timeText;
    Handler handler;
    Runnable runnable;
    EditText answer;
    String[] letters = {"A","L","T","I"};

    TextView T,T2,T3,T4,letterT;
    TextView L,L2,L3,letterL;
    TextView A,A2,A3,letterA;
    TextView I,I2,letterI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_level4);

        score = 0;
        time = 0;
        multiplier = 10;
        correctCount = 0;
        correctAnswers = new ArrayList<>(6);

        intent = getIntent();

        scoreText = findViewById(R.id.textViewScore34);
        timeText = findViewById(R.id.textViewTime34);
        answer = findViewById(R.id.editTextAnswerEntry34);

        //A lar
        A = findViewById(R.id.textView34A);
        A2 = findViewById(R.id.textView34A2);
        A3 = findViewById(R.id.textView34A3);
        letterA = findViewById(R.id.textViewLetterA34);
        //T ler
        T = findViewById(R.id.textView34T);
        T2 = findViewById(R.id.textView34T2);
        T3 = findViewById(R.id.textView34T3);
        T4 = findViewById(R.id.textView34T4);
        letterT = findViewById(R.id.textViewLetterT34);
        //L ler
        L = findViewById(R.id.textView34L);
        L2 = findViewById(R.id.textView34L2);
        L3 = findViewById(R.id.textView34L3);
        letterL = findViewById(R.id.textViewLetterL34);
        //I lar
        I = findViewById(R.id.textView34I);
        I2 = findViewById(R.id.textView34I2);
        letterI = findViewById(R.id.textViewLetterI34);

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
            toNext = new Intent(getApplicationContext(), EndOf34Activity.class);
            toNext.putExtra("playerName",intent.getStringExtra("playerName"));
            //3.1
            toNext.putExtra("3.1Score",intent.getIntExtra("3.1Score",0));
            //3.2
            toNext.putExtra("3.2Score",intent.getIntExtra("3.2Score",0));
            //3.3
            toNext.putExtra("3.3Score",intent.getIntExtra("3.3Score",0));
            //3.4
            toNext.putExtra("3.4Score",score);

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
        letterA.setText(letters[0]);
        letterL.setText(letters[1]);
        letterT.setText(letters[2]);
        letterI.setText(letters[3]);
    }

    public void answerCheck(String ans) {
        if(correctAnswers.contains(ans.toUpperCase())) {
            Toast.makeText(getApplicationContext(),"Bu kelimeyi daha önce buldunuz.",Toast.LENGTH_SHORT).show();
        } else {
            if(ans.toUpperCase().matches("TAT")){
                correctAnswers.add(ans.toUpperCase());
                Toast.makeText(getApplicationContext(),"TAT Doğru Cevap",Toast.LENGTH_SHORT).show();

                T.setText("T");
                T.setBackgroundColor(Color.WHITE);
                A.setText("A");
                A.setBackgroundColor(Color.WHITE);
                T2.setText("T");
                T2.setBackgroundColor(Color.WHITE);

                score += 5*multiplier;
                scoreText.setText("Puan : "+score);
                correctCount++;

            } else if(ans.toUpperCase().matches("TATLI")){
                correctAnswers.add(ans.toUpperCase());
                Toast.makeText(getApplicationContext(),"TATLI Doğru Cevap",Toast.LENGTH_SHORT).show();

                T2.setText("T");
                T2.setBackgroundColor(Color.WHITE);
                A2.setText("A");
                A2.setBackgroundColor(Color.WHITE);
                T4.setText("T");
                T4.setBackgroundColor(Color.WHITE);
                L3.setText("L");
                L3.setBackgroundColor(Color.WHITE);
                I2.setText("I");
                I2.setBackgroundColor(Color.WHITE);

                score += 5*multiplier;
                scoreText.setText("Puan : "+score);
                correctCount++;

            } else if(ans.toUpperCase().matches("ALT")){
                correctAnswers.add(ans.toUpperCase());
                Toast.makeText(getApplicationContext(),"ALT Doğru Cevap",Toast.LENGTH_SHORT).show();

                A3.setText("A");
                A3.setBackgroundColor(Color.WHITE);
                L2.setText("L");
                L2.setBackgroundColor(Color.WHITE);
                T4.setText("T");
                T4.setBackgroundColor(Color.WHITE);


                score += 5*multiplier;
                scoreText.setText("Puan : "+score);
                correctCount++;

            } else if(ans.toUpperCase().matches("ALTI")){
                correctAnswers.add(ans.toUpperCase());
                Toast.makeText(getApplicationContext(),"ALTI Doğru Cevap",Toast.LENGTH_SHORT).show();

                A2.setText("A");
                A2.setBackgroundColor(Color.WHITE);
                L.setText("L");
                L.setBackgroundColor(Color.WHITE);
                T3.setText("T");
                T3.setBackgroundColor(Color.WHITE);
                I.setText("I");
                I.setBackgroundColor(Color.WHITE);


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
            menuIntent.putExtra("3.4Score",score);

            startActivity(menuIntent);
        }
        return super.onOptionsItemSelected(item);
    }
}
