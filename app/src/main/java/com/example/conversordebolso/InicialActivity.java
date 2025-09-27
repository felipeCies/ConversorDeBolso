package com.example.conversordebolso;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
public class InicialActivity extends AppCompatActivity{


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicial);

        Button botaoMoedas = findViewById(R.id.botaoMoedas);
        Button botaoMedidas = findViewById(R.id.botaoMedidas);

        botaoMoedas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InicialActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        botaoMedidas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InicialActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });

        }
    }

