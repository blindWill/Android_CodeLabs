package by.sergey.customview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sadEmotionalFace = findViewById<EmotionalFace>(R.id.sadEmotionalFace)
        val happyEmotionalFace = findViewById<EmotionalFace>(R.id.happyEmotionalFace)
        val userEmotionalFace = findViewById<EmotionalFace>(R.id.userEmotionalFace)
        val seriousEmotionalFace = findViewById<EmotionalFace>(R.id.seriousEmotionalFace)

        happyEmotionalFace.setOnClickListener {
            userEmotionalFace.happinessState = EmotionalFace.HAPPY
        }

        sadEmotionalFace.setOnClickListener {
            userEmotionalFace.happinessState = EmotionalFace.SAD
        }

        seriousEmotionalFace.setOnClickListener {
            userEmotionalFace.happinessState = EmotionalFace.SERIOUS
        }

    }
}