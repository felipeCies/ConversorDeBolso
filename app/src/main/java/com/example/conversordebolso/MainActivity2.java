package com.example.conversordebolso;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {

    private EditText editValorTemp;
    private Spinner spinnerDe, spinnerPara;
    private TextView textResultadoTemp;
    private Button btnConverterTemp, botaoVoltar;

    String[] unidades = {"Celsius", "Fahrenheit", "Kelvin"};

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        editValorTemp = findViewById(R.id.editValorTemp);
        spinnerDe = findViewById(R.id.spinnerDe);
        spinnerPara = findViewById(R.id.spinnerPara);
        textResultadoTemp = findViewById(R.id.textResultadoTemp);
        btnConverterTemp = findViewById(R.id.btnConverterTemp);
        botaoVoltar = findViewById(R.id.botaoVoltar);


        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, unidades);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDe.setAdapter(adapter);
        spinnerPara.setAdapter(adapter);

        btnConverterTemp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double valor = Double.parseDouble(editValorTemp.getText().toString());
                String de = spinnerDe.getSelectedItem().toString();
                String para = spinnerPara.getSelectedItem().toString();

                double resultado = converterTemperatura(valor, de, para);
                textResultadoTemp.setText("Resultado: " + String.format("%.1f", resultado) + " " + para);
            }
        });

        botaoVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity2.this, InicialActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();
            }
        });
    }

    private double converterTemperatura(double valor, String de, String para) {
        double celsius;


        if (de.equals("Fahrenheit")) {
            celsius = (valor - 32) * 5/9;
        } else if (de.equals("Kelvin")) {
            celsius = valor - 273.15;
        } else {
            celsius = valor;
        }


        if (para.equals("Fahrenheit")) {
            return (celsius * 9/5) + 32;
        } else if (para.equals("Kelvin")) {
            return celsius + 273.15;
        } else {
            return celsius;
        }
    }

}
