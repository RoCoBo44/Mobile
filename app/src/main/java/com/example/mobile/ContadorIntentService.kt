package com.example.mobile

import android.app.IntentService
import android.content.Intent

class ContadorIntentService: IntentService("servicio") {

    override fun onCreate() {
        super.onCreate()
    }

    override fun onHandleIntent(p0: Intent?) {
        Thread.sleep(100)
        var i = p0?.getIntExtra("i", 0)
        print("value of i:" + i)

        val intent = Intent()
        intent.action = "intentService"
        intent.putExtra("i", i)
        intent.putExtra("sender", 1)
        //intent.flags = Intent.FLAG_INCLUDE_STOPPED_PACKAGES
        sendBroadcast(intent)
        print("que onda pib?")
    }
}