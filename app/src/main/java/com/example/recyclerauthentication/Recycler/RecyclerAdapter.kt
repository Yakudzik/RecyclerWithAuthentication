package com.example.recyclerauthentication.Recycler

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerauthentication.FullElement
import com.example.recyclerauthentication.JsModel.JsonModelTwinItem
import com.example.recyclerauthentication.OneFullElementFragment
import com.example.recyclerauthentication.OneFullElementFragmentArgs
import com.example.recyclerauthentication.R
import com.squareup.picasso.Picasso
import java.util.ArrayList

class RecyclerAdapter(private val recyclerAdapterList: ArrayList<JsonModelTwinItem>) :
    RecyclerView.Adapter<RecyclerAdapter.myViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_one_element, parent, false)
        return myViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        holder.bindingData(recyclerAdapterList[position])

        holder.itemView.setOnClickListener {
            var intent = Intent(it.context,FullElement::class.java)
            intent.putExtra("title",recyclerAdapterList[position].title)
            intent.putExtra("description",recyclerAdapterList[position].text)
            intent.putExtra("data",recyclerAdapterList[position].date)
            intent.putExtra("img",recyclerAdapterList[position].image)
            it.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = recyclerAdapterList.size

    class myViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindingData(result: JsonModelTwinItem) {
            val title: TextView = itemView.findViewById(R.id.title_oef_ID)
            val description: TextView = itemView.findViewById(R.id.description_oef_ID)
            val date: TextView = itemView.findViewById(R.id.date_time_oef_ID)
            val image: ImageView = itemView.findViewById(R.id.image_oef_ID)

            title.text = result.title
            description.text = result.text
            date.text = result.date

            Picasso.get().load("http://dev-exam.l-tech.ru${result.image}")
                .into(image)

        }
    }
}