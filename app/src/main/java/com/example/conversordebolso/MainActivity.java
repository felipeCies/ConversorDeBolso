package com.example.conversordebolso;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.conversordebolso.api.ExchangeRateApi;
import com.example.conversordebolso.api.ExchangeResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private EditText editValorUSD;
    private TextView textResultado;
    private Button btnConverter, btnVoltar;
    private ExchangeRateApi api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editValorUSD = findViewById(R.id.editValorUSD);
        textResultado = findViewById(R.id.textResultado);
        btnConverter = findViewById(R.id.btnConverter);
        btnVoltar = findViewById(R.id.btnVoltar);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.frankfurter.app/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        this.api = retrofit.create(ExchangeRateApi.class);


        textResultado.setText("Insira um valor em USD.");


        btnConverter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String valorTexto = editValorUSD.getText().toString();

                if (valorTexto.isEmpty()) {
                    textResultado.setText("Por favor, insira um valor em USD.");
                    return;
                }


                try {
                    double valorUSD = Double.parseDouble(valorTexto);
                    buscarCotacaoEConverter(valorUSD);
                } catch (NumberFormatException e) {
                    textResultado.setText("Formato de valor inválido.");
                }
            }
        });


        btnVoltar.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, InicialActivity.class);
            startActivity(intent);
            finish();
        });
    }


    private void buscarCotacaoEConverter(double valorUSD) {
        textResultado.setText("Buscando cotação atual...");


        Call<ExchangeResponse> call = api.getRates("USD", "BRL");

        call.enqueue(new Callback<ExchangeResponse>() {
            @Override
            public void onResponse(Call<ExchangeResponse> call, Response<ExchangeResponse> response) {
                if (response.isSuccessful() && response.body() != null) {


                    if (response.body().getRates() != null) {

                        Double cotacao = response.body().getRates().get("BRL");

                        if (cotacao != null) {

                            double resultado = valorUSD * cotacao;
                            textResultado.setText(String.format(" %.2f USD = R$ %.2f", valorUSD, resultado));
                        } else {

                            textResultado.setText("Erro: Taxa BRL não encontrada no retorno.");
                        }

                    } else {
                        textResultado.setText("Erro ao obter cotação");
                    }
                }
            }

            @Override
            public void onFailure(Call<ExchangeResponse> call, Throwable t) {

                textResultado.setText("Falha de Conexão: " + t.getMessage());
            }
        });
    }
}