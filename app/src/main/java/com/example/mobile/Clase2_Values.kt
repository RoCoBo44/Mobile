package com.example.mobile

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.CalendarContract
import androidx.core.graphics.toColorInt
import kotlinx.android.synthetic.main.activity_clase2__values.*

class Clase2_Values : AppCompatActivity() {

    var character = '-'
    var contrario = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clase2__values)
        character = this.intent.getCharExtra("signo", '-')
        println(character)

        val res = Calculate()
        if (contrario < 1)
            TextEn.setText("Papel")
        else
            if(contrario < 2)
                TextEn.setText("Tijera")
            else
                if(contrario <= 3)
                    TextEn.setText("Piedra")

        if (character == 'p')
            TextYo.setText("Papel")
        else
            if(character == 't')
                TextYo.setText("Tijera")
            else
                if(character == 's')
                    TextYo.setText("Piedra")

        if (res == '-') {
            textSignEn.setTextColor("#fffaa1".toColorInt())
            textSignEn.setTextSize(25F);
        }
        else
            if (res == '+') {
                textSignYo.setTextColor("#fffaa1".toColorInt())
                textSignYo.setTextSize(25F);
            }

        //Esto es para que si vaya para atras, igual tenga el resultado
        val replyIntent = Intent()
        replyIntent.putExtra("Result", res)
        this.setResult(Activity.RESULT_OK,replyIntent)

        BtnCalculate.setOnClickListener(){

            finish()

        }
    }

    fun Calculate():Char{
        val randomNumber = Math.random()*3
        contrario = randomNumber
        // menor a 1 papel// menor a 2 tijera // menor de 3 piedra
        if (character == 's'){ //stone
            if (randomNumber < 1)
                return '-'
            if (randomNumber < 2)
                return '+'
        }
        if (character == 't'){ //tijaras
            if (randomNumber < 1)
                return '+'
            if (randomNumber > 2 && randomNumber <= 3)
                return '-'
        }
        if (character == 'p'){ //papel
            if (randomNumber > 1 && randomNumber < 2)
                return '-'
            if (randomNumber <= 3 && randomNumber > 2)
                return '+'
        }
        return 't'
    }
}
