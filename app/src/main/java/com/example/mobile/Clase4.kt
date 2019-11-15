package com.example.mobile

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_clase4.*

class Clase4 : AppCompatActivity() {

    inner class MyReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val reason = intent.getIntExtra("sender", 0)
            if (reason == 1){
                tIntSer.setText(intent.getIntExtra("i", 0).toString())
            }
            if (reason == 2){
                tIntBack.setText(intent.getIntExtra("i", 0).toString())
            }
            println("hola wach")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clase4)

        val filter = IntentFilter()
        filter.addAction("intentService")
        val receiver = MyReceiver()
        registerReceiver(receiver, filter)

        //val c : ContadorIntentService = ContadorIntentService()
        bIntentService.setOnClickListener {
            for (i in 1..4 ){
                val inSer = Intent(this, ContadorIntentService::class.java)
                inSer.putExtra("i",i)
                startService(inSer)
                println("Lomand")
            }
        }

        bServiceBackground.setOnClickListener {
            for (i in 1..1 ){
                val inSer = Intent(this, ContadorService::class.java)
                inSer.putExtra("i",i)
                startService(inSer)
                println("Lomand2")
            }
        }
    }
}
