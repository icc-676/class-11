package com.abs.clase11.ui

import android.os.AsyncTask
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.room.Room
import com.abs.clase11.R
import com.abs.clase11.model.Database
import com.abs.clase11.model.GifDao
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class GifFragment : BottomSheetDialogFragment() {

    lateinit var database: GifDao

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_gif_list, container, false)
        if (view is RecyclerView) {
            database = Room.databaseBuilder(view.context, Database::class.java,"gif").build().gifDao()
            view.layoutManager = LinearLayoutManager(context)
            val adapter = GifAdapter()
            view.adapter = adapter
            AsyncTask.execute() {
                adapter.setData(database.getAllGifs())
            }
        }
        return view
    }


    companion object {
        fun newInstance() = GifFragment()
    }
}