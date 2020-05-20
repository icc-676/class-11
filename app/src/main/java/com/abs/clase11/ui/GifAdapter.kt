package com.abs.clase11.ui

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.abs.clase11.R
import com.abs.clase11.model.Gif
import com.abs.clase11.utils.loadGif


import kotlinx.android.synthetic.main.fragment_gif.view.*

class GifAdapter() : RecyclerView.Adapter<GifAdapter.ViewHolder>() {

    private var data = listOf<Gif>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_gif, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.bindItem(item)
    }

    fun setData(newData: List<Gif>) {
        this.data = newData
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = data.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val view = mView
        val imageView = view.findViewById<ImageView>(R.id.gif_image_view)
        fun bindItem(gif: Gif) {
            view.gif_id.text = gif.id
            view.gif_url.text = gif.url
            imageView.loadGif(gif.url)
        }
    }
}