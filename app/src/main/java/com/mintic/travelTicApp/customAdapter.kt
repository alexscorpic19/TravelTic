package com.mintic.travelTicApp

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import java.util.ArrayList


class customAdapter(
    private val mSites: ArrayList<Sites>,
    private val context: Context,
    private val onClick: (Sites?) -> Unit
) : RecyclerView.Adapter<customAdapter.SitesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SitesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.sites_list_item, parent, false)
        return SitesViewHolder(view)
    }

    override fun onBindViewHolder(holderContact: SitesViewHolder, position: Int) {
        val contact = mSites[position]
        holderContact.bind(sites = contact)
    }

    override fun getItemCount(): Int {
        return mSites.size
    }

    inner class SitesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var nameLabel: TextView = itemView.findViewById(R.id.tvName)
        private var descriptionLabel: TextView = itemView.findViewById(R.id.tvDescription)
        private var pointsLabel: TextView = itemView.findViewById(R.id.tvPoints)
        private var imageView: ImageView = itemView.findViewById(R.id.itemImage)
        private var currentSites: Sites? = null

        init {
            itemView.setOnClickListener {
                Log.d(TAG, "itemView OnClick: ${currentSites?.name}")
                onClick(currentSites)
            }
        }

        /* Bind Contact name and image. */
        fun bind(sites: Sites) {
            currentSites = sites

            nameLabel.text = sites.name
            descriptionLabel.text = sites.description
            pointsLabel.text=sites.points

            Glide.with(context)
                .load(sites.image)
                .into(imageView)
        }
    }

    companion object {
        private const val TAG = "CustomAdapter"
    }
}
