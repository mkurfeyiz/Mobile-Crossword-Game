package com.mkurfeyiz.kelimeoyunu.secondlevelactivities;

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

public class SecondLevel4Activity extends AppCompatActivity {

    Intent intent,toNext;
    int score,time,multiplier,correctCount;
    ArrayList<String> correctAnswers;
    TextView scoreText,timeText;
    Handler handler;
    Runnable runnable;
    EditText answer;
    String[] letters = {"B","Y","A","O"};

    TextView B,B2,letterB;
    TextView Y,Y2,letterY;
    TextView A,A2,A3,letterA;
    TextView O,O2,O3,letterO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_level4);

        score = 0;
        time = 0;
        multiplier = 7;
        correctCount = 0;
        correctAnswers = new ArrayList<>(6);

        intent = getIntent();

        scoreText = findViewById(R.id.textViewScore24);
        timeText = findViewById(R.id.textViewTime24);
        answer = findViewById(R.id.editTextAnswerEntry24);

        //B ler
        B = findViewById(R.id.textView24B);
        B2 = findViewById(R.id.textView24B2);
        letterB = findViewById(R.id.textViewLetterB24);
        //Y ler
        Y = findViewById(R.id.textView24Y);
        Y2 = findViewById(R.id.textView24Y2);
        letterY = findViewById(R.id.textViewLetterY24);
        //A lar
        A = findViewById(R.id.textView24A);
        A2 = findViewById(R.id.textView24A2);
        A3 = findViewById(R.id.textView24A3);
        letterA = findViewById(R.id.textViewLetterA24);
        //O lar
        O = findViewById(R.id.textView24O);
        O2 = findViewById(R.id.textView24O2);
        O3 = findViewById(R.id.textView24O3);
        letterO = findViewById(R.id.textViewLetterO24);

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
            toNext = new Intent(getApplicationContext(), EndOf24Activity.class);
            toNext.putExtra("playerName",intent.getStringExtra("playerName"));
            //2.1 bilgisi
            toNext.putExtra("2.1Score",intent.getIntExtra("2.1Score",0));
            //2.2 bilgisi
            toNext.putExtra("2.2Score",intent.getIntExtra("2.2Score",0));
            //2.3
            toNext.putExtra("2.3Score",intent.getIntExtra("2.3Score",0));
            //2.4
            toNext.putExtra("2.4Score",score);

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
        letterO.setText(letters[0]);
        letterY.setText(letters[1]);
        letterA.setText(letters[2]);
        letterB.setText(letters[3]);
    }

    public void answerCheck(String ans) {
        if(correctAnswers.contains(ans.toUpperCase())) {
            Toast.makeText(getApplicationContext(),"Bu kelimeyi daha önce buldunuz.",Toast.LENGTH_SHORT).show();
        } else {
            if(ans.toUpperCase().matches("BOY")){
                correctAnswers.add(ans.toUpperCase());
                Toast.makeText(getApplicationContext(),"BOY Doğru Cevap",Toast.LENGTH_SHORT).show();

                B.setText("B");
                B.setBackgroundColor(Color.WHITE);
                O.setText("O");
                O.setBackgroundColor(Color.WHITE);
                Y.setText("Y");
                Y.setBackgroundColor(Color.WHITE);

                score += 5*multiplier;
                scoreText.setText("Puan : "+score);
                correctCount++;

            } else if(ans.toUpperCase().matches("BOYA")){
                correctAnswers.add(ans.toUpperCase());
                Toast.makeText(getApplicationContext(),"BOYA Doğru Cevap",Toast.LENGTH_SHORT).show();

                B2.setText("B");
                B2.setBackgroundColor(Color.WHITE);
                O2.setText("O");
                O2.setBackgroundColor(Color.WHITE);
                Y.setText("Y");
                Y.setBackgroundColor(Color.WHITE);
                A.setText("A");
                A.setBackgroundColor(Color.WHITE);

                score += 5*multiplier;
                scoreText.setText("Puan : "+score);
                correctCount++;

            } else if(ans.toUpperCase().matches("BAY")){
                correctAnswers.add(ans.toUpperCase());
                Toast.makeText(getApplicationContext(),"BAY Doğru Cevap",Toast.LENGTH_SHORT).show();

                B2.setText("B");
                B2.setBackgroundColor(Color.WHITE);
                A2.setText("A");
                A2.setBackgroundColor(Color.WHITE);
                Y2.setText("Y");
                Y2.setBackgroundColor(Color.WHITE);

                score += 5*multiplier;
                scoreText.setText("Puan : "+score);
                correctCount++;

            } else if(ans.toUpperCase().matches("OYA")){
                correctAnswers.add(ans.toUpperCase());
                Toast.makeText(getApplicationContext(),"OYA Doğru Cevap",Toast.LENGTH_SHORT).show();

                O3.setText("O");
                O3.setBackgroundColor(Color.WHITE);
                Y2.setText("Y");
                Y2.setBackgroundColor(Color.WHITE);
                A3.setText("A");
                A3.setBackgroundColor(Color.WHITE);


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
            Intent menuIntent = new Intent(getApplicationContext(), ScorePage2Activity.class);
            menuIntent.putExtra("playerName",intent.getStringExtra("playerName"));//nickname i aktariyoruz.
            //2.1 seviye puani
            menuIntent.putExtra("2.1Score",intent.getIntExtra("2.1Score",0));
            //2.2
            menuIntent.putExtra("2.2Score",intent.getIntExtra("2.2Score",0));
            //2.3
            menuIntent.putExtra("2.3Score",intent.getIntExtra("2.3Score",0));
            //2.4
            menuIntent.putExtra("2.4Score",score);

            startActivity(menuIntent);
        }
        return super.onOptionsItemSelected(item);
    }
}
