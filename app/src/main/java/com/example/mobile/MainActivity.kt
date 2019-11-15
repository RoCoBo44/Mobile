package com.example.mobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Clase1Btn.setOnClickListener(){
            var intent = Intent(this, Clase1::class.java)
            startActivity(intent)
        }

        Clase2Btn.setOnClickListener(){
            var intent = Intent(this, Clase2_Buttons::class.java)
            startActivity(intent)
        }

        Clase3Btn.setOnClickListener(){
            var intent = Intent(this, Clase3::class.java)
            startActivity(intent)
        }

        Clase4Btn.setOnClickListener(){
            var intent = Intent(this, Clase4::class.java)
            startActivity(intent)
        }
    }
}
