package com.example.rplrus021.midsemester12rpl

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_login.*
import kotlin.math.log

class login : AppCompatActivity() {
    var email: String = ""
    var password: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        edt_email as EditText
        edt_password as EditText
        btn_login as Button

        btn_login.setOnClickListener(View.OnClickListener {
            email = edt_email.text.toString()
            password = edt_password.text.toString()
            if (email.isEmpty() && password.isEmpty()) {
                edt_email.setError("Please Write email")
                edt_password.setError("Please Write password")
            } else if (email.equals("zero") && password.equals("admin")) {
                val intent = Intent(applicationContext, home::class.java)
                val sharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.putString("email",email)
                editor.putString("password",password)
                startActivity(intent)
                finish()
            }

        })
    }
}
