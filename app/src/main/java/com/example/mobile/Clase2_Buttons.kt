package com.example.mobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_clase1.*
import kotlinx.android.synthetic.main.activity_clase2__buttons.*
import kotlinx.android.synthetic.main.activity_clase2__buttons.myLayout
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T



class Clase2_Buttons : AppCompatActivity() {

    val CONECTION_VALUE = 1
    var wins = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clase2__buttons)

        // intent.addCantegory(intent.category_default)
        // intent.setType(contractscontrac.contract.content_type)
        if (savedInstanceState != null){
            val count = savedInstanceState.getInt("wins")
            if (count != null) {
                wins = count.toInt()
                textResult.setText(wins.toString())
            }
        }
        //StartActivityForResult(intent, GET_CONTACT)
        var character = ' '
        BtnPie.setOnClickListener(){
            ConectWithView('s') //STONE
        }
        BtnPapel.setOnClickListener(){
            ConectWithView('p') //PAPEL
        }
        BtnTije.setOnClickListener(){
            ConectWithView('t') //TIJERAS
        }

        myLayout.setOnLongClickListener{
            //Toast.makeText(this, "reset", Toast.LENGTH_SHORT).show()
            wins = 0
            textResult.setText(wins.toString())
            return@setOnLongClickListener true
        }

    }

    fun ConectWithView(character:Char){
        val intent = Intent(this, Clase2_Values::class.java)
        intent.putExtra("signo",character)
        startActivityForResult(intent,CONECTION_VALUE) //Se pone un codigo para saber que vino de esto
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == CONECTION_VALUE) { // Identify activity
            if (resultCode == RESULT_OK) { // Activity succeeded
                val reply = data?.getCharExtra("Result", '-')

                if (reply == '-')
                    wins -= 1
                else
                    if(reply == '+')
                        wins += 1

                textResult.setText(wins.toString())


            }
        }
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        // Add information for saving HelloToast counter
        // to the to the outState bundle
        outState.putInt("wins", wins)
    }

    /*
    public override fun onRestoreInstanceState(mySavedState: Bundle) {
        super.onRestoreInstanceState(mySavedState)
        if (mySavedState != null) {
            val count = mySavedState.getString("count")
            if (count != null) {
                textResult.setText(wins.toString())
                println(wins)
                wins = count.toInt()
            }
        }
    }
    */

    /*
    public void onActivityResult(int requestCode,
    int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CHOOSE_FOOD_REQUEST) { // Identify activity
            if (resultCode == RESULT_OK) { // Activity succeeded
                String reply = data.getStringExtra(“FOOD”);
// ... do something with the data
            }}}

     */
    /*
        fun onActivityResult()
        super.onActivi..

            if(requestCode == GET_CONTACT && reculstcode == Activiry.Result_ok){
                val projec =  arrayof(contactcontact.contacts.DISPLAY_NAME_PRIMARY)
                var cursor = contentResolver.query(data?.data, projec, null,null,null)
                cursor.MoveTofirst()
                val contact = data?.data.toSTring() + ?n  + cursor.get(0)
                log.d(TAG, contact)
                textview.text = contact
            }
    */

}
