package com.example.stopericazatrening;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button buttonStart;
    Spinner spinnerBrojKrugova;
    Spinner spinnerTrajanjeKrugova;
    Spinner spinnerTrajanjeKrugovaSec;
    Spinner spinnertrajanjeOdmora;
    Spinner spinnerTrajanjeOdmoraSec;
    Integer brojKrugova;
    Integer trajanjeKrugova;
    Integer trajanjeKrugovaSec;
    Integer trajanjeOdmora;
    Integer trajanjeOdmoraSec;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        assignViews();




            buttonStart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    brojKrugova = Integer.parseInt(spinnerBrojKrugova.getSelectedItem().toString());
                    trajanjeKrugova = Integer.parseInt(spinnerTrajanjeKrugova.getSelectedItem().toString());
                    trajanjeKrugovaSec = Integer.parseInt(spinnerTrajanjeKrugovaSec.getSelectedItem().toString());
                    trajanjeOdmora = Integer.parseInt(spinnertrajanjeOdmora.getSelectedItem().toString());
                    trajanjeOdmoraSec = Integer.parseInt(spinnerTrajanjeOdmoraSec.getSelectedItem().toString());

                    trajanjeKrugovaSec = trajanjeKrugova * 60 + trajanjeKrugovaSec;
                    trajanjeOdmoraSec = trajanjeOdmora * 60 + trajanjeOdmoraSec;

                    if (brojKrugova == 0) {
                        Toast.makeText(MainActivity.this, "Ne mozes poceti timer bez broja rundi", Toast.LENGTH_SHORT).show();
                    } else {
                        Intent intent = new Intent(MainActivity.this, OdbrojavanjeActivity.class);
                        intent.putExtra("brojKrugova", brojKrugova);
                        intent.putExtra("trajanjeKruga", trajanjeKrugovaSec);
                        intent.putExtra("trajanjeOdmora", trajanjeOdmoraSec);
                        startActivity(intent);
                    }
                }
            });
        }


        private void assignViews () {
            buttonStart = findViewById(R.id.buttonStart);
            spinnerBrojKrugova = findViewById(R.id.spinnerBrojKrugova);     //instanca broj krugova spinnera

            //napravi array od arraya iz stringova
            ArrayList<Integer> brojeviArrayList = new ArrayList<>();
            for (int i = 0; i < 21; i++) {
                brojeviArrayList.add(i);
            }

            //napravi i postavi adapter za spinner
            ArrayAdapter<Integer> arrayAdapterBrojevi = new ArrayAdapter<>(this, R.layout.spinner_item, brojeviArrayList);
            spinnerBrojKrugova.setAdapter(arrayAdapterBrojevi);

            spinnerTrajanjeKrugova = findViewById(R.id.spinnerTrajanjeKrugova);
            spinnerTrajanjeKrugova.setAdapter(arrayAdapterBrojevi);

            ArrayList<Integer> sekundeArrayList = new ArrayList<>();

            for (int i = 0; i < 61; i++) {
                sekundeArrayList.add(i);
            }

            spinnerTrajanjeKrugovaSec = findViewById(R.id.spinnerTrajanjeKrugovaSec);
            ArrayAdapter<Integer> arrayAdapterSekunde = new ArrayAdapter<>(this, R.layout.spinner_item, sekundeArrayList);
            spinnerTrajanjeKrugovaSec.setAdapter(arrayAdapterSekunde);


            spinnertrajanjeOdmora = findViewById(R.id.spinnerTrajanjeOdmora);
            spinnertrajanjeOdmora.setAdapter(arrayAdapterBrojevi);

            spinnerTrajanjeOdmoraSec = findViewById(R.id.spinnerTrajanjeOdmoraSec);
            spinnerTrajanjeOdmoraSec.setAdapter(arrayAdapterSekunde);

        }
    }
