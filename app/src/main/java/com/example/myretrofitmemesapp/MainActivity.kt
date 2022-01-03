package com.example.myretrofitmemesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myretrofitmemesapp.model.Meme
import com.example.myretrofitmemesapp.model.Post

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var recyclerAdapter: RecyclerAdapter
    //lateinit var newList: MutableList<Meme>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

   /*     var bundle : Bundle? = intent.extras
        var message = bundle!!.getString("dd")
*/

        recyclerView = findViewById(R.id.recyclerview)
        recyclerAdapter = RecyclerAdapter(this)
        //recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = recyclerAdapter


        val apiInterface = ApiInterface.create().getMemes()

        apiInterface.enqueue( object : Callback<Post>{
            override fun onResponse(call: Call<Post>?, response: Response<Post>?) {
                Log.d("MyTag","Success ")
                if(response!!.body() != null)
                    recyclerAdapter.setMemeListItems(response.body()?.data?.memes!!)
                Log.d("MyTag","Success "+response.body().toString())
            }

            override fun onFailure(call: Call<Post>?, t: Throwable?) {
                Log.d("MyTag", "Failure "+t.toString())
            }
        })

   /*     val msg = intent.getStringExtra("Data")
        newList = mutableListOf()// adding data from RecyclerAdapter
        newList.addAll(message)

        var adapter = RecyclerAdapter()
        recyclerView.adapter = adapter
        adapter.setOnItemClickListner(object : RecyclerAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {
                Toast.makeText(this@MainActivity,"You Clicked", Toast.LENGTH_LONG).show()
            }
        })*/
    }

/*    override fun passResultCallback(message: MutableList<Meme>) {
        var msg = intent.getStringExtra("Data")
        var msg1 = intent.getStringExtra("Data1")

        var myMessage = message
        newList.addAll(myMessage)
    }*/

}