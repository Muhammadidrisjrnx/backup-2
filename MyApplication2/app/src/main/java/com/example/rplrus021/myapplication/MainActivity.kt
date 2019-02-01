package com.example.rplrus021.myapplication

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import org.apache.http.HttpEntity
import org.apache.http.HttpResponse
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.DefaultHttpClient
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {
    var email: String = ""
    var password: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_login.setOnClickListener(View.OnClickListener {
            if (email.equals("") && password.equals("")) {
                email = edt_login_email.text.toString()
                password = edt_login_password.text.toString()
                login_user(email, password).execute()
            } else {
                Toast.makeText(applicationContext, "Failed Autentication", Toast.LENGTH_SHORT).show()
            }
        })
    }

    class login_user(email: String, password: String) : AsyncTask<Void, Void, JSONObject>() {
        var email: String = ""
        var password: String = ""

        init {
            this.email = email
            this.password = password
        }

        override fun doInBackground(vararg params: Void?): JSONObject? {
            var jsonObject: JSONObject? = null

            try {
                var url = "http://192.168.6.48/coba_kotlin/login.php?email=" + email + "&&password=" + password + ""
                println(url)
                val httpClient: DefaultHttpClient = DefaultHttpClient()
                val httpGet: HttpGet = HttpGet(url)
                val httpResponse: HttpResponse = httpClient.execute(httpGet)
                val httpEntity: HttpEntity = httpResponse.entity
                val inputStream: InputStream = httpEntity.content
                val bufferedReader: BufferedReader = BufferedReader(InputStreamReader(inputStream, "iso-8859-1"), 8)
                val stringBuilder: StringBuilder = StringBuilder()
                var line: String? = ""
                while (line != null) {
                    line = bufferedReader.readLine()
                    stringBuilder.append(line).append("\n")
                }
                inputStream.close()
                var json: String = stringBuilder.toString()
                jsonObject = JSONObject(json)
            } catch (e: Exception) {
                jsonObject = null
            }
            return jsonObject
        }

        override fun onPreExecute() {

        }

        override fun onPostExecute(jsonObject: JSONObject) {
            Log.d("hasil json", "onPostExecute: " + jsonObject.toString())
            if (jsonObject != null) {
                try {
                    val result: JSONObject = jsonObject.getJSONObject("Result")
                    var sukses: String = result.getString("Sukses")
                    Log.d("hasil sukses", "onPostExecute: " + sukses)

                    if (sukses.equals("true")) {
                    } else if (sukses.equals("false")) {

                    } else {

                    }

                } catch (e: Exception) {

                }
            } else {

            }
        }

    }
}
