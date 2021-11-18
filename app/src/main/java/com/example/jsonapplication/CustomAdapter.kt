package com.example.jsonapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import java.util.*
import kotlin.collections.ArrayList

class CustomAdapter(
    private var context: Context,
    //private var siteImage:ArrayList<Int>,
    private var siteNames: ArrayList<String>,
    private var siteDescription: ArrayList<String>,
    private var sitePoints: ArrayList<String>
) :
    RecyclerView.Adapter<CustomAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.row, parent, false)
        return MyViewHolder(v)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // set the data in items
       // holder.image.setImageResource(siteImage[position])
        holder.name.text = siteNames[position]
        holder.description.text = siteDescription[position]
        holder.points.text = sitePoints[position]
        // implement setOnClickListener event on item view.
        holder.itemView.setOnClickListener { // display a toast with person name on item click
            Toast.makeText(context, siteNames[position], Toast.LENGTH_SHORT).show()
        }
    }
    override fun getItemCount(): Int {
        return siteNames.size
    }
    inner class MyViewHolder(itemView: View) : ViewHolder(itemView) {
       // var image: ImageView = itemView.findViewById<View>(R.id.itemImage) as ImageView
        var name: TextView = itemView.findViewById<View>(R.id.tvName) as TextView
        var description: TextView = itemView.findViewById<View>(R.id.tvDescription) as TextView
        var points: TextView = itemView.findViewById<View>(R.id.tvPoints) as TextView
    }
}