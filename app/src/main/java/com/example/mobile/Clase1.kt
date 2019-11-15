package com.example.mobile

import android.gesture.Gesture
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_clase1.*





class Clase1 : AppCompatActivity() {

    var contador = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clase1)
        myLayout.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                when (event?.action) {
                    MotionEvent.ACTION_DOWN ->  incr()
                }

                return v?.onTouchEvent(event) ?: true
            }
        })
        myLayout.setOnLongClickListener{
            //Toast.makeText(this, "reset", Toast.LENGTH_SHORT).show()
            reset()
            return@setOnLongClickListener true
        }
    }

    fun incr(){
        contador = contador + 1
        ContadorText.setText(contador.toString())
    }

    fun reset() {
        //println("3")
        contador = 0
        ContadorText.setText(contador.toString())
    }
}
