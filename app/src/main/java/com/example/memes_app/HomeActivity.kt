package com.example.memes_app

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

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
        val queue=Volley.newRequestQueue(this@HomeActivity)
        val progBar=findViewById<ProgressBar>(R.id.progBar)

        btnNext.setOnClickListener {
            progBar.visibility = View.VISIBLE
            this@HomeActivity.loadMeme()
        }

        btnShare.setOnClickListener {
            Toast.makeText(this@HomeActivity, "Share not available yet", Toast.LENGTH_LONG).show()
        }
        val jsonObjectRequest=JsonObjectRequest (Request.Method.GET, url, null, Response.Listener { response ->
            val url=response.getString("url")
            Glide.with(this).load(url).listener(object: RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    progBar.visibility = View.GONE
                    Toast.makeText(this@HomeActivity, "Unable to load data. Please try again.", Toast.LENGTH_LONG)
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    progBar.visibility = View.GONE
                    return false
                }
            }).into(imgMeme)
        }, Response.ErrorListener {
            Toast.makeText(this@HomeActivity, "Something went wrong, please try again.", Toast.LENGTH_LONG).show()
        })
        queue.add(jsonObjectRequest)
    }
}