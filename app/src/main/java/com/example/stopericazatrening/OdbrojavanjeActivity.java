package com.example.stopericazatrening;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


public class OdbrojavanjeActivity extends AppCompatActivity {

    int brojac;
    int timer;
    MediaPlayer mediaPlayer;
    ProgressBar progressBar;
    int progress = 100;
    Button button;
    TextView textView;
    TextView tvTrenutno;
    Button buttonStop;
    int trenutnaRunda = 0;

    private void updateProgressBar(){
        progressBar.setProgress(progress);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_odbrojavanje);
        assignViews();
        updateProgressBar();

        Intent intent = getIntent();



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int brojKrugova = intent.getIntExtra("brojKrugova", 0);
                int trajanjeKruga = intent.getIntExtra("trajanjeKruga", 0);
                int trajanjeOdmora = intent.getIntExtra("trajanjeOdmora", 0);
                brojac = 0;
                buttonStop.setClickable(true);
                button.setClickable(false);
                Odbrojavanje(trajanjeKruga, trajanjeOdmora, brojKrugova);
            }
        });
    }


    public void Odbrojavanje(int trajanjeKruga, int trajanjeOdmora, int brojKrugova){
        int runde = brojKrugova * 2 - 1;

        if (brojac %2 == 0){
            timer = trajanjeKruga;
            trenutnaRunda ++;
            tvTrenutno.setText("Runda broj: " + trenutnaRunda);

        }else{
            timer = trajanjeOdmora;
            tvTrenutno.setText("Odmor");
        }

        progressBar.setMax(timer);

          //brojKrugova * 2 - 1 = broj rundi - 1 jer se zadnji odmor ne racuna
            CountDownTimer countDownTimer = new CountDownTimer(timer * 1000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    textView.setText(millisUntilFinished / 1000 + " s");

                    progress = (int) (millisUntilFinished / 1000);
                    updateProgressBar();

                    buttonStop.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            cancel();
                            textView.setText("Stopped");
                            button.setClickable(true);
                            buttonStop.setClickable(false);
                            progress = timer;
                            updateProgressBar();
                        }
                    });

                    if (millisUntilFinished < 3000) {
                        mediaPlayer.start();
                    }
                }



                @Override
                public void onFinish() {
                    brojac++;
                    if(brojac < runde) {
                        Odbrojavanje(trajanjeKruga, trajanjeOdmora, brojKrugova);
                    }else{
                        textView.setText("Gotovo");
                        button.setClickable(true);
                        buttonStop.setClickable(false);
                    }

                }
            }.start();
        }


    private void assignViews(){
        progressBar = findViewById(R.id.progressBar);
        button = findViewById(R.id.button2);
        textView = findViewById(R.id.textView2);
        mediaPlayer = MediaPlayer.create(this, R.raw.beep3);
        tvTrenutno = findViewById(R.id.tvTrenutno);
        buttonStop = findViewById(R.id.buttonStop);
    }

}
    

