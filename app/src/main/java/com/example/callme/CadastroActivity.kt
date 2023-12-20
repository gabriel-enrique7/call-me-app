package com.example.callme

import android.content.Intent
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
//    var previousButton : Button? = null
    var nextButton : Button? = null
    var tvTitle : TextView? = null
    var tvSubtitle : TextView? = null
    var tvPassos : TextView? = null

    var steps : Array<View> = arrayOf()
    var currentStep : Int = 0
//    var stepIndicators : Array<TextView> = arrayOf()
    var stepIndicators : Array<View> = arrayOf()
    var titles : Array<String> = arrayOf()
    var subtitles : Array<String> = arrayOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        conteinerLayout = findViewById<LinearLayout>(R.id.container_layout)
        stepIndicatorsLayout = findViewById<LinearLayout>(R.id.step_indicators_layout)
//        previousButton = findViewById<Button>(R.id.previous_button)
        nextButton = findViewById<Button>(R.id.next_button)
        tvTitle = findViewById(R.id.title)
        tvSubtitle = findViewById(R.id.subtitle)
        tvPassos = findViewById(R.id.tvPassos)

        //Inicializa os steps (views)
        steps = arrayOf(
            LayoutInflater.from(this).inflate(R.layout.step1, conteinerLayout, false),
            LayoutInflater.from(this).inflate(R.layout.step2, conteinerLayout, false),
            LayoutInflater.from(this).inflate(R.layout.step3, conteinerLayout, false),
            LayoutInflater.from(this).inflate(R.layout.step4, conteinerLayout, false),
        )

        //Inicializa os titulos
        titles = arrayOf(
            "Conecte a sua agenda!",
            "Bem vindo ao Call-me",
            "Quase lá",
            "Conecte a sua agenda!",
        )

        //inicializa os subtitlos
        subtitles = arrayOf(
            "Precisamos de algumas informações para criar seu perfil. Ah, você pode editar essas informaões depois.",
            "Conecte o seu calendário para verificar automaticamente as horas ocupadas e os novos eventos a medida em que são agendados.",
            "Defina o intervalo de horário que você está disponível em cada dia da semana.",
            "Precisamo de algumas informações para criar o seu perfil. Ah, você pode editar essas informações depois.",
        )

        //Inicializa step indicadores (circulos e setas)
        initializeStepIndicators()

        showCurrentStep()

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
        stepIndicators = Array(steps.size) { View(this) }
        for (i in 0 until steps.size) {
            val stepView = View(this)
            stepView.setBackgroundResource(R.color.gray_600)
            val params: LinearLayout.LayoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(10, 0, 10, 0)
            params.weight = 1F
            params.height = 5
            stepView.layoutParams = params
            stepIndicatorsLayout?.addView(stepView)
            stepIndicators[i] = stepView
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
//        var visibility : Int = View.INVISIBLE
        var textButtonNext : String = "Finalizar"

        conteinerLayout?.removeAllViews()
        conteinerLayout?.addView(steps[currentStep])

//        if(currentStep > 0) visibility = View.VISIBLE
        if(currentStep < steps.size - 1) textButtonNext = "Próximo passo"

//        previousButton?.visibility = visibility
        nextButton?.text = textButtonNext
        tvTitle?.text = titles[currentStep]
        tvSubtitle?.text = subtitles[currentStep]
        tvPassos?.text = "Passo " + (currentStep + 1) + " de " + steps.size

        updateStepIndicators();
    }

    //Atualiza para o step indicador
    fun updateStepIndicators() {
        for(i in 0 until stepIndicators.size) {
            if(i == currentStep) {
                stepIndicators[i].setBackgroundResource(R.color.ignite_500)
            } else {
                stepIndicators[i].setBackgroundResource(R.color.gray_600)
            }
        }
    }

    fun submitForm() {
        Toast.makeText(this, "CADASTRO DE USUARIO!", Toast.LENGTH_SHORT).show()

        val intent = Intent(this, CalendarActivity::class.java)
        startActivity(intent)
    }
}