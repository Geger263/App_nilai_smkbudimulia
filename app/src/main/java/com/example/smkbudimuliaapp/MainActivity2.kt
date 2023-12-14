package com.example.smkbudimuliaapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

//import android.support.v7.app.AlertDialog




class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val btnsiswa = findViewById<Button>(R.id.btnsiswa)
        val btnguru = findViewById<Button>(R.id.btnguru)
        val btnmapel = findViewById<Button>(R.id.btnmapel)
        val tvnim = findViewById<TextView>(R.id.textnis)
        val btnnilai = findViewById<Button>(R.id.btnnilai)
        val nim = intent.getStringExtra("pnis")
        tvnim.text = "SELAMAT DATANG "+ nim


        btnsiswa.setOnClickListener{

            Intent(this,data_siswa::class.java).also {
                startActivity(it)

            }
        }

        btnguru.setOnClickListener{
            Intent(this, data_guru::class.java).also {
                startActivity(it)
            }
        }

        btnmapel.setOnClickListener{

            Intent(this, data_mapel::class.java).also {
                startActivity(it)

            }

        }
        btnnilai.setOnClickListener{

            Intent(this, data_nilai::class.java).also {
                startActivity(it)

            }

        }




    }
}