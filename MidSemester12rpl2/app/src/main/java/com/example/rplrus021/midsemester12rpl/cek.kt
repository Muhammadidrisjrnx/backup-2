package com.example.rplrus021.midsemester12rpl

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class cek : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE);
        var email = sharedPreferences.getString("email", "")
        var password = sharedPreferences.getString("password", "")
        if (email.equals("") && password.equals("")) {
            val intent = Intent(applicationContext, login::class.java)
            startActivity(intent)
            finish()
        }
        else if (email==email&&password==password){
            val intent = Intent(applicationContext,home::class.java)
            startActivity(intent)
            finish()
        }
    }
}
