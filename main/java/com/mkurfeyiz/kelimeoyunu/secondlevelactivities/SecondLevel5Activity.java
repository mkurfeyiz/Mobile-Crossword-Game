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

public class SecondLevel5Activity extends AppCompatActivity {

    Intent intent,toNext;
    int score,time,multiplier,correctCount;
    ArrayList<String> correctAnswers;
    TextView scoreText,timeText;
    Handler handler;
    Runnable runnable;
    EditText answer;
    String[] letters = {"C","Z","A","E"};

    TextView E,E2,letterE;
    TextView C,C2,C3,letterC;
    TextView A,A2,A3,letterA;
    TextView Z,Z2,Z3,letterZ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_level5);

        score = 0;
        time = 0;
        multiplier = 7;
        correctCount = 0;
        correctAnswers = new ArrayList<>(6);

        intent = getIntent();

        scoreText = findViewById(R.id.textViewScore25);
        timeText = findViewById(R.id.textViewTime25);
        answer = findViewById(R.id.editTextAnswerEntry25);

        //E ler
        E = findViewById(R.id.textView25E);
        E2 = findViewById(R.id.textView25E2);
        letterE = findViewById(R.id.textViewLetterE25);
        //C ler
        C = findViewById(R.id.textView25C);
        C2 = findViewById(R.id.textView25C2);
        C3 = findViewById(R.id.textView25C3);
        letterC = findViewById(R.id.textViewLetterC25);
        //A lar
        A = findViewById(R.id.textView25A);
        A2 = findViewById(R.id.textView25A2);
        A3 = findViewById(R.id.textView25A3);
        letterA = findViewById(R.id.textViewLetterA25);
        //Z ler
        Z = findViewById(R.id.textView25Z);
        Z2 = findViewById(R.id.textView25Z2);
        Z3 = findViewById(R.id.textView25Z3);
        letterZ = findViewById(R.id.textViewLetterZ25);

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
            toNext = new Intent(getApplicationContext(), EndOf25Activity.class);
            toNext.putExtra("playerName",intent.getStringExtra("playerName"));
            //2.1 bilgisi
            toNext.putExtra("2.1Score",intent.getIntExtra("2.1Score",0));
            //2.2 bilgisi
            toNext.putExtra("2.2Score",intent.getIntExtra("2.2Score",0));
            //2.3
            toNext.putExtra("2.3Score",intent.getIntExtra("2.3Score",0));
            //2.4
            toNext.putExtra("2.4Score",intent.getIntExtra("2.4Score",0));
            //2.5
            toNext.putExtra("2.5Score",score);

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
        letterC.setText(letters[0]);
        letterZ.setText(letters[1]);
        letterA.setText(letters[2]);
        letterE.setText(letters[3]);
    }

    public void answerCheck(String ans) {
        if(correctAnswers.contains(ans.toUpperCase())) {
            Toast.makeText(getApplicationContext(),"Bu kelimeyi daha önce buldunuz.",Toast.LENGTH_SHORT).show();
        } else {
            if(ans.toUpperCase().matches("EZA")){
                correctAnswers.add(ans.toUpperCase());
                Toast.makeText(getApplicationContext(),"EZA Doğru Cevap",Toast.LENGTH_SHORT).show();

                E.setText("E");
                E.setBackgroundColor(Color.WHITE);
                Z.setText("Z");
                Z.setBackgroundColor(Color.WHITE);
                A2.setText("A");
                A2.setBackgroundColor(Color.WHITE);

                score += 5*multiplier;
                scoreText.setText("Puan : "+score);
                correctCount++;

            } else if(ans.toUpperCase().matches("ECZA")){
                correctAnswers.add(ans.toUpperCase());
                Toast.makeText(getApplicationContext(),"ECZA Doğru Cevap",Toast.LENGTH_SHORT).show();

                E2.setText("E");
                E2.setBackgroundColor(Color.WHITE);
                C.setText("C");
                C.setBackgroundColor(Color.WHITE);
                Z.setText("Z");
                Z.setBackgroundColor(Color.WHITE);
                A.setText("A");
                A.setBackgroundColor(Color.WHITE);

                score += 5*multiplier;
                scoreText.setText("Puan : "+score);
                correctCount++;

            } else if(ans.toUpperCase().matches("CEZA")){
                correctAnswers.add(ans.toUpperCase());
                Toast.makeText(getApplicationContext(),"CEZA Doğru Cevap",Toast.LENGTH_SHORT).show();

                C2.setText("C");
                C2.setBackgroundColor(Color.WHITE);
                E2.setText("E");
                E2.setBackgroundColor(Color.WHITE);
                Z2.setText("Z");
                Z2.setBackgroundColor(Color.WHITE);
                A3.setText("A");
                A3.setBackgroundColor(Color.WHITE);

                score += 5*multiplier;
                scoreText.setText("Puan : "+score);
                correctCount++;

            } else if(ans.toUpperCase().matches("CAZ")){
                correctAnswers.add(ans.toUpperCase());
                Toast.makeText(getApplicationContext(),"CAZ Doğru Cevap",Toast.LENGTH_SHORT).show();

                C3.setText("C");
                C3.setBackgroundColor(Color.WHITE);
                A3.setText("A");
                A3.setBackgroundColor(Color.WHITE);
                Z3.setText("Z");
                Z3.setBackgroundColor(Color.WHITE);


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
            menuIntent.putExtra("2.4Score",intent.getIntExtra("2.4Score",0));
            //2.5
            menuIntent.putExtra("2.5Score",score);

            startActivity(menuIntent);
        }
        return super.onOptionsItemSelected(item);
    }
}
