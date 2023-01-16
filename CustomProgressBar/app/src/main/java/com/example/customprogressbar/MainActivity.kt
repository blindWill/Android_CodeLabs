package com.example.customprogressbar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val verticalProgressBar = findViewById<VerticalProgressBar>(R.id.verticalProgressBar)

        val button = findViewById<Button>(R.id.btCalculate)
        val etMin = findViewById<EditText>(R.id.etMin)
        val etMax = findViewById<EditText>(R.id.etMax)
        val etCurrent = findViewById<EditText>(R.id.etCurrent)
        
        button.setOnClickListener{
            verticalProgressBar.min = etMin.text.toString().toInt()
            verticalProgressBar.max = etMax.text.toString().toInt()
            verticalProgressBar.current = etCurrent.text.toString().toInt()
        }
    }
}