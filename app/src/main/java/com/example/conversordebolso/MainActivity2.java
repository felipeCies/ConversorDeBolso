package com.example.conversordebolso;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {

    private EditText editQuantidadeMoeda;
    private EditText editCotacao;
    private TextView textResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editQuantidadeMoeda = findViewById(R.id.editQuantidadeMoeda);
        editCotacao = findViewById(R.id.editCotacao);
        textResultado = findViewById(R.id.textResultado);

    }


    public void converter (View view){

        double quantidadeMoeda = Double.parseDouble(editQuantidadeMoeda.getText().toString());
        double cotacao = Double.parseDouble(editCotacao.getText().toString());
        double resultado = quantidadeMoeda * cotacao;

        textResultado.setText("Valor convertido: R$" + resultado);


    }

}