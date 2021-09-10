package com.example.recyclerauthentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.recyclerauthentication.DataTransfer.FragmentDataTransfer
import com.example.recyclerauthentication.JsModel.JsonModelTwinItem
import com.example.recyclerauthentication.Retrofit.ApiResponse
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}