package com.yutaka.drawfy;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Switch repSwitch;
    private TextView qtdRepet;
    private String resultadoRep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        repSwitch = findViewById(R.id.switchRep);
        qtdRepet = findViewById(R.id.editTextRep);

        adicionarListener();
    }

    public void openDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this, R.style.MyDialogTheme);

        dialog.setTitle("Resultado")
                .setMessage(resultadoRep)
                .setIcon(R.drawable.ic_baseline_announcement_24);

        dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        dialog.create()
                .show();
    }

    public void adicionarListener() {
        repSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked) {
                    qtdRepet.setVisibility(View.VISIBLE);
                } else {
                    qtdRepet.setVisibility(View.GONE);
                }
            }
        });
    }

    public void sortearNum(View view) {

        TextView menorNumero = findViewById(R.id.editTextMenorNum);
        TextView maiorNumero = findViewById(R.id.editTextMaiorNum);
        String verificadorMenor = menorNumero.getText().toString();
        String verificadorMaior = maiorNumero.getText().toString();

        ImageView imgCircle = findViewById(R.id.imgCirculo);
        TextView texto1 = findViewById(R.id.txtMsg);
        TextView textoResultado = findViewById(R.id.txtRes);
        textoResultado.setVisibility(View.INVISIBLE);
        imgCircle.setVisibility(View.GONE);
        texto1.setVisibility(View.VISIBLE);

        String verificadorRep = qtdRepet.getText().toString();

        maiorNumero.onEditorAction(EditorInfo.IME_ACTION_DONE);

        Boolean switchState = repSwitch.isChecked();

        if(verificadorMenor.equals("") || verificadorMaior.equals("")) {
            texto1.setText("Digite um número!");
        } else {
            int numero1 = Integer.parseInt(menorNumero.getText().toString());
            int numero2 = Integer.parseInt(maiorNumero.getText().toString());
            if(numero1 > numero2) {
                texto1.setText("Digite um intervalo válido!");
            } else {
                if(switchState == true){
                    if(verificadorRep.equals("")) {
                        texto1.setText("Digite a quantidade de sorteios");
                    } else {
                        int numeroRep = Integer.parseInt(qtdRepet.getText().toString());
                        if(numeroRep == 0) {
                            texto1.setText("Digite um número maior do que 0");
                        } else {
                            resultadoRep = "";
                            int qtdRep = numeroRep; //
                            int contador = 0;
                            int[] numSorteados = new int[qtdRep];
                            texto1.setVisibility(View.GONE);
                            Random random = new Random();
                            do {
                                int numeroSorteado = random.nextInt((numero2 - numero1) + 1) + numero1;
                                numSorteados[contador] = numeroSorteado;
                                contador++;
                                resultadoRep += contador + "º resultado é: " + numeroSorteado + "\n";
                            } while(contador < qtdRep);
                            openDialog();
                        }
                    }

                } else {
                    Random random = new Random();
                    int numeroSorteado = random.nextInt((numero2 - numero1) + 1) + numero1;
                    texto1.setText("O número sorteado é:");
                    textoResultado.setText("" + numeroSorteado);
                    textoResultado.setVisibility(View.VISIBLE);
                    imgCircle.setVisibility(View.VISIBLE);
                }
            }
        }

    }
}