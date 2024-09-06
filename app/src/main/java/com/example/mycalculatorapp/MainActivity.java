package com.example.mycalculatorapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText lblValor1, lblValor2;
    private TextView txtResultado, txtErrores;
    private Button btnExponente, btnRadical, btnLimpiar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lblValor1 = findViewById(R.id.lblValor1);
        lblValor2 = findViewById(R.id.lblValor2);
        txtResultado = findViewById(R.id.txtResultado);
        txtErrores = findViewById(R.id.txtErrores);
        btnExponente = findViewById(R.id.btnExponente);
        btnRadical = findViewById(R.id.btnRadical);
        btnLimpiar = findViewById(R.id.btnLimpiar);

        btnExponente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realizarOperacion("exponente");
            }
        });

        btnRadical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realizarOperacion("radical");
            }
        });

        btnLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limpiarCampos();
            }
        });
    }

    private void realizarOperacion(String operacion) {
        txtErrores.setText("");

        String valor1Str = lblValor1.getText().toString();
        String valor2Str = lblValor2.getText().toString();

        if (valor1Str.isEmpty() || valor2Str.isEmpty()) {
            txtErrores.setText("Ambos valores deben estar ingresados.");
            return;
        }

        try {
            double valor1 = Double.parseDouble(valor1Str);
            double valor2 = Double.parseDouble(valor2Str);

            if (valor1 < 0 || valor2 < 0) {
                txtErrores.setText("Los valores no deben ser negativos.");
                return;
            }

            double resultado;
            switch (operacion) {
                case "exponente":
                    resultado = Math.pow(valor1, valor2);
                    txtResultado.setText("RESULTADO: " + resultado);
                    break;
                case "radical":
                    if (valor2 == 0) {
                        txtErrores.setText("No se puede hacer la raíz con índice 0.");
                    } else {
                        resultado = Math.pow(valor1, 1.0 / valor2);
                        txtResultado.setText("RESULTADO: " + resultado);
                    }
                    break;
            }
        } catch (NumberFormatException e) {
            txtErrores.setText("Por favor, ingresa números válidos.");
        }
    }

    private void limpiarCampos() {
        lblValor1.setText("");
        lblValor2.setText("");
        txtResultado.setText("RESULTADO:");
        txtErrores.setText("");
    }
}
