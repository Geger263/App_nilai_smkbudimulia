package com.example.smkbudimuliaapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnsubmit = findViewById<Button>(R.id.btnhasil)
        val etnis = findViewById<EditText>(R.id.ednis)
        val etpw = findViewById<EditText>(R.id.edpw)
        val username = "Admin"
        val pw = "admin"
        fun showToast(context: Context, message: String) {
            val toast = Toast.makeText(context, message, Toast.LENGTH_SHORT)
            toast.show()
        }



        btnsubmit.setOnClickListener{
            val nim = etnis.text.toString()
            val pass = etpw.text.toString()

            if (nim == username && pass == pw)  {
                Intent(this, MainActivity2::class.java).also {
                    it.putExtra("pnis", nim)
                    startActivity(it)
                }
            } else {showToast(this, "DATA SALAH")}


        }
    }
}