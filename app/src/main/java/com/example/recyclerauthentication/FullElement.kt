package com.example.recyclerauthentication

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import com.squareup.picasso.Picasso


class FullElement : AppCompatActivity() {
    lateinit var titleID: TextView
    lateinit var descriptionID: TextView
    lateinit var dateID: TextView
    lateinit var imageID: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_element)

        var data = intent
        var nazvanie = data.getStringExtra("title").toString()
        var description = data.getStringExtra("description").toString()
        var dataAndTime = data.getStringExtra("data").toString()
        var image = data.getStringExtra("img").toString()


        titleID = findViewById<TextView>(R.id.title_ID).apply {
            this.text = nazvanie
        }
        descriptionID = findViewById<TextView>(R.id.description_ID).apply {
            this.text = description
        }
        dateID = findViewById<TextView>(R.id.date_time_ID).apply {
            this.text = dataAndTime
        }
        imageID = findViewById<ImageView>(R.id.image_ID)

        Picasso.get()
            .load(("http://dev-exam.l-tech.ru${image}"))
            .into(imageID)
    }
}