package com.example.mobile

import android.app.Service
import android.content.Intent
import android.os.*
import java.lang.Process

class ContadorService : Service() {

    private var mlooper : Looper? = null
    private var mServiceHandler : ServiceHandler? = null

    inner class ServiceHandler(l:Looper) : Handler(l) {

        override fun handleMessage(msg: Message) {

            Thread.sleep(2000)
            var i = msg.obj.toString().toInt()
            print("value of i:" + i)

            val intent = Intent()
            intent.action = "intentService"
            intent.putExtra("i", i)
            intent.putExtra("sender", 2)
            //intent.flags = Intent.FLAG_INCLUDE_STOPPED_PACKAGES
            sendBroadcast(intent)

            println("Back handle" + Thread.currentThread().name + "     ")
            stopSelf(msg.arg1)
        }
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate() {

        val thread : HandlerThread = HandlerThread("ServiceStartArgumentes", android.os.Process.THREAD_PRIORITY_BACKGROUND)
        thread.start()

        mlooper = thread.looper
        mServiceHandler = ServiceHandler(mlooper!!)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        Thread( Runnable {
            val msg : Message = mServiceHandler!!.obtainMessage()
            msg.arg1 = startId
            msg.obj = intent?.getIntExtra("i", 0)
            mServiceHandler?.sendMessage(msg)
            println("back Command" + Thread.currentThread().name) }).start()

        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}