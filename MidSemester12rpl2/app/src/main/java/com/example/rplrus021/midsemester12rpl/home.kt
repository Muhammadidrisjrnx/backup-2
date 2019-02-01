package com.example.rplrus021.midsemester12rpl

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.activity_home.*

class home : AppCompatActivity() {
    var data: ArrayList<data>? = null
    var data2: data? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        data = ArrayList<data>()
        var adapter2:adapter2 = adapter2()
        recycle_view.adapter(adapter2)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        var id = item!!.itemId
        if (id == R.id.sign_out) {
            val intent = Intent(applicationContext, login::class.java)
            val sharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.clear()
            editor.apply()
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }
}
