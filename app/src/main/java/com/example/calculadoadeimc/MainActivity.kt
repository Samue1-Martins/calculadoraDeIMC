package com.example.calculadoadeimc

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val pesoEdt = findViewById<EditText>(R.id.Peso)
        val alturaEdt = findViewById<EditText>(R.id.Altura)
        val confirmarBtn = findViewById<Button>(R.id.Confirmar)
        val resultadoTv = findViewById<TextView>(R.id.Resultado)
        val descricaoResultadoTv = findViewById<TextView>(R.id.DescricaoResultado)

        fun calcularImc() {
            val pesoStr = pesoEdt.text.toString()
            val alturaStr = alturaEdt.text.toString()

            val peso = pesoStr.toDoubleOrNull()
            val altura = alturaStr.toDoubleOrNull()

            if (peso != null && altura != null) {

                val imc = peso / (altura * altura)
                if (imc < 18.5) {
                    resultadoTv.text = "IMC: %.2f".format(imc)
                    descricaoResultadoTv.text =
                        "Você está abaixo do peso. É importante procurar orientação médica para identificar possíveis causas e garantir que sua nutrição e saúde estejam adequadas."
                }
                if (imc in 18.5..24.9) {
                    resultadoTv.text = "IMC: %.2f".format(imc)
                    descricaoResultadoTv.text =
                        "Você está com peso normal. Mantenha um estilo de vida saudável para continuar nessa faixa."
                }
                if (imc in 25.0..29.9) {
                    resultadoTv.text = "IMC: %.2f".format(imc)
                    descricaoResultadoTv.text =
                        "Você está com sobrepeso. Considere adotar hábitos mais saudáveis, como uma dieta equilibrada e prática regular de exercícios físicos."
                }
                if (imc >= 30.0) {
                    resultadoTv.text = "IMC: %.2f".format(imc)
                    descricaoResultadoTv.text =
                        "Você está com obesidade. É importante buscar orientação médica para gerenciar seu peso e reduzir o risco de condições de saúde graves."
                }
            } else {
                descricaoResultadoTv.text = ""
                resultadoTv.text = "Ambos os campos de valores precisam estar preenchidos!"
            }
        }
        confirmarBtn.setOnClickListener {
            calcularImc()
        }
    }
}

