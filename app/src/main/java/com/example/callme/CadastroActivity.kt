package com.example.callme

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible

class CadastroActivity : AppCompatActivity() {

    var conteinerLayout : LinearLayout? = null
    var stepIndicatorsLayout : LinearLayout? = null
    var previousButton : Button? = null
    var nextButton : Button? = null

    var steps : Array<View> = arrayOf()
    var currentStep : Int = 0
    var stepIndicators : Array<TextView> = arrayOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        conteinerLayout = findViewById<LinearLayout>(R.id.container_layout)
        stepIndicatorsLayout = findViewById<LinearLayout>(R.id.step_indicators_layout)
        previousButton = findViewById<Button>(R.id.previous_button)
        nextButton = findViewById<Button>(R.id.next_button)

        //Inicializa os steps (views)
        steps = arrayOf(
            LayoutInflater.from(this).inflate(R.layout.step1, conteinerLayout, false),
            LayoutInflater.from(this).inflate(R.layout.step2, conteinerLayout, false),
            LayoutInflater.from(this).inflate(R.layout.step3, conteinerLayout, false),
        )

        //Inicializa step indicadores (circulos e setas)
        initializeStepIndicators()

        showCurrentStep()

        //Click para voltar
        previousButton?.setOnClickListener(View.OnClickListener {
            if(currentStep > 0) {
                currentStep--
                showCurrentStep()
            }
        })

        //Click para o proximo
        nextButton?.setOnClickListener(View.OnClickListener {
            if(currentStep < steps.size - 1) {
                currentStep++
                showCurrentStep()
            } else {
                //Quando o ultimo step é carregado submete o formulario
                submitForm()
            }
        })
    }
    fun initializeStepIndicators() {
        stepIndicators = Array(steps.size) { TextView(this) }
        for (i in 0 until steps.size) {
            val stepIndicator = TextView(this)
            stepIndicator.text = (i + 1).toString()
            stepIndicator.setTextColor(Color.WHITE)
            stepIndicator.textSize = 18F
            stepIndicator.setBackgroundResource(R.drawable.circle_gray)
            stepIndicator.gravity = Gravity.CENTER
            val params : LinearLayout.LayoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(10,0,10,0)
            stepIndicator.layoutParams = params
            stepIndicatorsLayout?.addView(stepIndicator)
            stepIndicators[i] = stepIndicator

            if(i < steps.size - 1){
                addArrowIndicator(stepIndicatorsLayout!!);
            }
        }
    }

    //Adiciona seta indicadora entre os steps indicadores
    fun addArrowIndicator(stepIndicatorsLayout: LinearLayout) {
        val arrow = ImageView(this)

        arrow.setImageResource(R.drawable.ic_arrow)
        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        params.gravity = Gravity.CENTER_VERTICAL
        arrow.layoutParams = params
        stepIndicatorsLayout.addView(arrow)
    }

    //Mostra o step correto
    fun showCurrentStep() {
        var visibility : Int = View.INVISIBLE
        var textButtonNext : String = "Submit"

        conteinerLayout?.removeAllViews()
        conteinerLayout?.addView(steps[currentStep])

        if(currentStep > 0) visibility = View.VISIBLE
        if(currentStep < steps.size - 1) textButtonNext = "Next"

        previousButton?.visibility = visibility
        nextButton?.text = textButtonNext

        updateStepIndicators();
    }

    //Atualiza para o step indicador
    fun updateStepIndicators() {
        for(i in 0 until stepIndicators.size) {
            if(i == currentStep) {
                stepIndicators[i].setBackgroundResource(R.drawable.circle_green)
            } else {
                stepIndicators[i].setBackgroundResource(R.drawable.circle_gray)
            }
        }
    }

    fun submitForm() {
        Toast.makeText(this, "CHUPAR A PIRIQUITA DA MARACUTCHA!", Toast.LENGTH_SHORT).show()
    }
}