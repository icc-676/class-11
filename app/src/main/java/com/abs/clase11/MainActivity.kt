package com.abs.clase11

import android.os.AsyncTask
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.abs.clase11.model.Database
import com.abs.clase11.model.GIFResponse
import com.abs.clase11.model.Gif
import com.abs.clase11.model.GifDao
import com.abs.clase11.networking.GIFService
import com.abs.clase11.networking.GifAPI
import com.abs.clase11.ui.GifFragment
import com.abs.clase11.utils.loadGif
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Response
import retrofit2.Call
import retrofit2.Callback

class MainActivity : AppCompatActivity() {

    lateinit var request: GifAPI
    lateinit var database: GifDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        database = Room.databaseBuilder(this,Database::class.java,"gif").build().gifDao()
        request = GIFService.buildService(GifAPI::class.java)
        get_gif.setOnClickListener {
            getRandomGif()
        }
        show_history.setOnClickListener {
            val gifHistory = GifFragment.newInstance()
            gifHistory.show(
                supportFragmentManager,
                "gif_history"
            )
        }
    }

    private fun getRandomGif() {
        val call = request.getRandomGif()
        call.enqueue(object : Callback<GIFResponse> {
            override fun onResponse(call: Call<GIFResponse>, response: Response<GIFResponse>) {
                if (response.isSuccessful) {
                    if (response.body() != null) {
                        val gif = Gif(
                            response.body()?.data?.id ?: "",
                            response.body()?.data?.imageUrl ?: ""
                        ) //Mejor que forzar un optional pero aum asi hay mejores formas
                        gif_image_view.loadGif(gif.url)
                        AsyncTask.execute{
                            database.insert(gif)
                        }
                    }
                } else {
                    Toast.makeText(this@MainActivity, "${response.errorBody()}", Toast.LENGTH_SHORT)
                        .show()
                }
            }

            override fun onFailure(call: Call<GIFResponse>, t: Throwable) {
                Toast.makeText(this@MainActivity, "${t.message}", Toast.LENGTH_SHORT).show()
            }
        })

    }


}
