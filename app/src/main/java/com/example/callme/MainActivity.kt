package com.example.callme

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnReserva = findViewById<Button>(R.id.btn_reserva)

        btnReserva.setOnClickListener(View.OnClickListener {
//            Toast.makeText(this@MainActivity, "clik na bucetinha da Mara!", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@MainActivity, CadastroActivity::class.java)
            startActivity(intent)
        });
    }
}