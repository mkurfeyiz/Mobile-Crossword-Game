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

public class SecondLevelActivity extends AppCompatActivity {

    Intent intent,toNext;
    int secondLevelScore,secondLevelTime,multiplier,secondLevelCorrectsCount;
    Runnable secondLvlRunnable;
    Handler secondLvlHandler;
    TextView secondLevelTimeText,secondLevelScoreText;
    TextView K,K2,K3,K4,K5,K6;
    TextView U,U2,U3,U4,U5,U6,U7,U8;
    TextView Y,T,Z,r,O;
    TextView letterK,letterU,letterY,letterT,letterZ,letterR,letterO;
    String[] letters = {"K","U","Y","T","Z","R","O"};
    EditText answer;
    ArrayList<String> correctAnswers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_level);

        correctAnswers = new ArrayList<>(6);

        secondLevelScore = 0;
        secondLevelCorrectsCount = 0;
        secondLevelTime = 0;
        multiplier = 10;

        //intenti al
        intent = getIntent();
        //U lar
        U = findViewById(R.id.textView2U);
        U2 = findViewById(R.id.textView2U2);
        U3 = findViewById(R.id.textView2U3);
        U4 = findViewById(R.id.textView2U4);
        U5 = findViewById(R.id.textView2U5);
        U6 = findViewById(R.id.textView2U6);
        U7 = findViewById(R.id.textView2U7);
        U8 = findViewById(R.id.textView2U8);
        letterU = findViewById(R.id.textViewLetter2U);
        //K lar
        K = findViewById(R.id.textView2K);
        K2 = findViewById(R.id.textView2K2);
        K3 = findViewById(R.id.textView2K3);
        K4 = findViewById(R.id.textView2K4);
        K5 = findViewById(R.id.textView2K5);
        K6 = findViewById(R.id.textView2K6);
        letterK = findViewById(R.id.textViewLetter2K);
        //Kalan harfler
        Y = findViewById(R.id.textView2Y);
        letterY = findViewById(R.id.textViewLetter2Y);
        T = findViewById(R.id.textView2T);
        letterT = findViewById(R.id.textViewLetter2T);
        Z = findViewById(R.id.textView2Z);
        letterZ = findViewById(R.id.textViewLetter2Z);
        r = findViewById(R.id.textView2R);
        letterR = findViewById(R.id.textViewLetter2R);
        O = findViewById(R.id.textView2O);
        letterO = findViewById(R.id.textViewLetter2O);

        secondLevelScoreText = findViewById(R.id.textViewScore2);
        secondLevelTimeText = findViewById(R.id.textViewTime2);

        answer = findViewById(R.id.editText2AnswerEntry);
        time();

    }

    public void time()
    {
        secondLvlHandler = new Handler();
        secondLvlRunnable = new Runnable() {
            @Override
            public void run() {
                secondLevelTimeText.setText("Geçen Süre : "+secondLevelTime);
                secondLevelTime++;
                secondLevelTimeText.setText("Geçen Süre : "+secondLevelTime);
                if(secondLevelTime%60 == 0 && multiplier>0)
                {
                    multiplier--;  //puan azaltma kontrolu
                }
                if(multiplier<0)
                {
                    multiplier = 1; // negatif olursa 1 e esitle
                }
                secondLvlHandler.postDelayed(secondLvlRunnable,1000);
            }
        };
        secondLvlHandler.post(secondLvlRunnable);
    }

    public void answerCheck(String ans){
        if(correctAnswers.contains(ans.toUpperCase())) {
            Toast.makeText(getApplicationContext(),"Bu kelimeyi daha önce buldunuz.",Toast.LENGTH_SHORT).show();
        } else {
            if(ans.toUpperCase().matches("KUYU")){
                correctAnswers.add(ans.toUpperCase());
                Toast.makeText(getApplicationContext(),"KUYU Doğru Cevap",Toast.LENGTH_SHORT).show();

                K.setText("K");
                K.setBackgroundColor(Color.WHITE);
                U2.setText("U");
                U2.setBackgroundColor(Color.WHITE);
                Y.setText("Y");
                Y.setBackgroundColor(Color.WHITE);
                U3.setText("U");
                U3.setBackgroundColor(Color.WHITE);

                secondLevelScore += 6*multiplier;
                secondLevelScoreText.setText("Puan : "+secondLevelScore);
                secondLevelCorrectsCount++;
                //
            } else if(ans.toUpperCase().matches("UYKU")){
                correctAnswers.add(ans.toUpperCase());
                Toast.makeText(getApplicationContext(),"UYKU Doğru Cevap",Toast.LENGTH_SHORT).show();

                U.setText("U");
                U.setBackgroundColor(Color.WHITE);
                Y.setText("Y");
                Y.setBackgroundColor(Color.WHITE);
                K3.setText("K");
                K3.setBackgroundColor(Color.WHITE);
                U4.setText("U");
                U4.setBackgroundColor(Color.WHITE);

                secondLevelScore += 6*multiplier;
                secondLevelScoreText.setText("Puan : "+secondLevelScore);
                secondLevelCorrectsCount++;
                //
            } else if(ans.toUpperCase().matches("KUTU")){
                correctAnswers.add(ans.toUpperCase());
                Toast.makeText(getApplicationContext(),"KUTU Doğru Cevap",Toast.LENGTH_SHORT).show();

                K2.setText("K");
                K2.setBackgroundColor(Color.WHITE);
                U4.setText("U");
                U4.setBackgroundColor(Color.WHITE);
                T.setText("T");
                T.setBackgroundColor(Color.WHITE);
                U5.setText("U");
                U5.setBackgroundColor(Color.WHITE);

                secondLevelScore += 6*multiplier;
                secondLevelScoreText.setText("Puan : "+secondLevelScore);
                secondLevelCorrectsCount++;
                //
            } else if(ans.toUpperCase().matches("KURU")){
                correctAnswers.add(ans.toUpperCase());
                Toast.makeText(getApplicationContext(),"KURU Doğru Cevap",Toast.LENGTH_SHORT).show();

                K4.setText("K");
                K4.setBackgroundColor(Color.WHITE);
                U6.setText("U");
                U6.setBackgroundColor(Color.WHITE);
                r.setText("R");
                r.setBackgroundColor(Color.WHITE);
                U7.setText("U");
                U7.setBackgroundColor(Color.WHITE);

                secondLevelScore += 6*multiplier;
                secondLevelScoreText.setText("Puan : "+secondLevelScore);
                secondLevelCorrectsCount++;
                //
            } else if(ans.toUpperCase().matches("KUZU")){
                correctAnswers.add(ans.toUpperCase());
                Toast.makeText(getApplicationContext(),"KUZU Doğru Cevap",Toast.LENGTH_SHORT).show();

                K4.setText("K");
                K4.setBackgroundColor(Color.WHITE);
                U5.setText("U");
                U5.setBackgroundColor(Color.WHITE);
                Z.setText("Z");
                Z.setBackgroundColor(Color.WHITE);
                U8.setText("U");
                U8.setBackgroundColor(Color.WHITE);

                secondLevelScore += 6*multiplier;
                secondLevelScoreText.setText("Puan : "+secondLevelScore);
                secondLevelCorrectsCount++;
                //
            } else if(ans.toUpperCase().matches("KOKU")){
                correctAnswers.add(ans.toUpperCase());
                Toast.makeText(getApplicationContext(),"KOKU Doğru Cevap",Toast.LENGTH_SHORT).show();

                K6.setText("K");
                K6.setBackgroundColor(Color.WHITE);
                O.setText("O");
                O.setBackgroundColor(Color.WHITE);
                K5.setText("K");
                K5.setBackgroundColor(Color.WHITE);
                U8.setText("U");
                U8.setBackgroundColor(Color.WHITE);

                secondLevelScore += 6*multiplier;
                secondLevelScoreText.setText("Puan : "+secondLevelScore);
                secondLevelCorrectsCount++;
                //
            } else {
                Toast.makeText(getApplicationContext(),"Aradığınız kelime bulmacada bulunmamaktadır.",Toast.LENGTH_LONG).show();
                multiplier -= 3;
                if(multiplier<0)
                {
                    multiplier = 1; // negatif olursa 1 e esitle
                }
            }
        }
        answer.setText("");
    }

    public void search(View view){

        String input=answer.getText().toString();
        answerCheck(input);
        if(secondLevelCorrectsCount == 6){
            //Intentle yeni aktiviteye gec ve oyuncuya ilk seviyeyi bitirdigini belirt.
            toNext = new Intent(getApplicationContext(), EndOfSecondLvlActivity.class);
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
            toNext.putExtra("2.5Score",intent.getIntExtra("2.5Score",0));
            //2.6
            toNext.putExtra("2.6Score",secondLevelScore);

            startActivity(toNext);
        }
    }

    public void help(View view){
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
        letterK.setText(letters[0]);
        letterU.setText(letters[1]);
        letterY.setText(letters[2]);
        letterT.setText(letters[3]);
        letterZ.setText(letters[4]);
        letterR.setText(letters[5]);
        letterO.setText(letters[6]);
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
            menuIntent.putExtra("2.5Score",intent.getIntExtra("2.5Score",0));
            //2.6
            menuIntent.putExtra("2.6Score",secondLevelScore);

            startActivity(menuIntent);
        }
        return super.onOptionsItemSelected(item);
    }
}
