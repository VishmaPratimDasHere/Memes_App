package com.example.memes_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        loadMeme()
    }

    fun loadMeme() {
        val url:String = "https://meme-api.com/gimme"
        val imgMeme=findViewById<ImageView>(R.id.imgMeme)
        val btnNext=findViewById<Button>(R.id.btnNext)
        val btnShare=findViewById<Button>(R.id.btnShare)

        btnNext.setOnClickListener {
            this@HomeActivity.loadMeme()
        }

        btnShare.setOnClickListener {
            Toast.makeText(this@HomeActivity, "Share not available yet", Toast.LENGTH_LONG)
        }
        val queue=JsonObjectRequest (Request.Method.GET, url, null, Response.Listener { response ->
            val url=response.getString("url")
            Glide.with(this).load(url).into(imgMeme)
        }, Response.ErrorListener {
            Toast.makeText(this@HomeActivity, "Something went wrong, please try again.", Toast.LENGTH_LONG)
        })
    }
}