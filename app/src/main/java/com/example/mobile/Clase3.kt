package com.example.mobile

import android.annotation.SuppressLint
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_clase3.*
import java.util.concurrent.TimeUnit
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock
import android.R.id.edit
import android.content.SharedPreferences
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T



class Clase3 : AppCompatActivity() {
    var classcounter = 0
    var exe = false // para que no exe varios AsymClock
    var sharedPrefFile = "com.example.android.hellosharedprefs"
    var mPreferences : SharedPreferences? = null
    inner class AsymClock : AsyncTask <Long,Int,Void> () {

        val TAG = "MainActivity"
        val lock  = ReentrantLock()
        val condition = lock.newCondition()

        override fun doInBackground(vararg params: Long?): Void? {

            while(!this.isCancelled) {
                lock.withLock {
                    condition.await(params.get(0)!!, TimeUnit.SECONDS) //Para representar un segundo
                    classcounter++
                    Log.d(TAG, "Segundos:" + classcounter)
                    publishProgress(classcounter)
                }
            }
            return null
        }

        override fun onProgressUpdate(vararg values: Int?) {
            super.onProgressUpdate(*values)

            tCounter.setText(values.get(0).toString())
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clase3)
        println("Hasta aca anda")
        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE)
        classcounter = mPreferences?.getInt("count", 1)!!
        tCounter.setText(classcounter.toString())

        var asymClock : AsymClock? = null
        var stepValue : Long = 1
        bStart.setOnClickListener {
            if (!exe) {
                stepValue = 1
                if (!tStep.text.isEmpty()){
                    stepValue = tStep.text.toString().toLong()
                }
                asymClock = this.AsymClock()
                asymClock?.execute(stepValue)
                exe = true
            }
        }

        bStop.setOnClickListener {
            asymClock?.cancel(true)
            exe = false
        }

        bReset.setOnClickListener {
            asymClock?.cancel(true)
            exe = false
            classcounter = 0
            tCounter.setText(classcounter.toString())
        }




        /*
        //EN TEORIA TIENE 16 MiniSegundos para terminar to do sino se rompe to do
        // LOcalBroad es mas eficiente que broadcast - se usa para comunicar

        //si cierro la app y la abro que tenga el contador en el mismo valor (sharedPreferences), agregar boton reset
        //Ademas teneer un step que te dice la cantidad de segundos que tiene que dormir
        // si pones el stop no tenes que esperar a que termine el ultimo para poner el start

        val active = false
        val TAG = "MainActivity"
        stop.isEnabled = false
        var counter = 0

        val lock  = RentrantLock()
        val condition = lock.condition

        start.setonclicklistener { _ ->
                active = ture
                stop.IsEnabled = ture
                while(active){
                    lock.withLock {
                        condition.await(1, TimeUnit.SECONDS) //Para representar un segundo
                        counter ++
                        Log.d(TAG, "Segundos:" + counter)
                        counterText.text = counter.toString()
                    }
                }
         }

         stop.setonclicklistener{ _ ->
            log.d(TAG, )
         }
         */
    }

    override fun onPause() {
        super.onPause()
        val preferencesEditor = mPreferences?.edit()
        preferencesEditor?.putInt("count", classcounter)
        preferencesEditor?.apply()
    }
}

