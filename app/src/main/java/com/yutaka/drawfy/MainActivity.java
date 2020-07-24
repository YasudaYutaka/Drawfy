package com.yutaka.drawfy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sortearNum(View view) {

        TextView menorNumero = findViewById(R.id.editTextMenorNum);
        TextView maiorNumero = findViewById(R.id.editTextMaiorNum);
        String verificadorMenor = menorNumero.getText().toString();
        String verificadorMaior = menorNumero.getText().toString();

        TextView texto1 = findViewById(R.id.txtMsg);
        TextView textoResultado = findViewById(R.id.txtRes);
        textoResultado.setVisibility(View.INVISIBLE);

        if(verificadorMenor.equals("") || verificadorMaior.equals("")) {
            texto1.setText("Digite um número!");
        } else {
            int numero1 = Integer.parseInt(menorNumero.getText().toString());
            int numero2 = Integer.parseInt(maiorNumero.getText().toString());
            if(numero1 > numero2) {
                texto1.setText("Digite um intervalo válido!");
            } else {
                Random random = new Random();
                int numeroSorteado = random.nextInt((numero2 - numero1) + 1) + numero1;
                texto1.setText("O número sorteado é:");
                textoResultado.setText("" + numeroSorteado);
                textoResultado.setVisibility(View.VISIBLE);
            }
        }

    }
}