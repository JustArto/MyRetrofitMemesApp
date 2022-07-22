package com.example.myretrofitmemesapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myretrofitmemesapp.model.Meme
import com.example.myretrofitmemesapp.model.Post
import com.google.android.material.floatingactionbutton.FloatingActionButton

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var recyclerAdapter: RecyclerAdapter
    lateinit var fabButton: FloatingActionButton
    //lateinit var newList: MutableList<Meme>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerview)
        recyclerAdapter = RecyclerAdapter(this)
        //recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = recyclerAdapter

        fabButton = findViewById(R.id.floatingActionButton)
        fabButton.setOnClickListener(View.OnClickListener {
            fabOnClick()
        })

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

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        val item = menu?.findItem(R.id.menu_search)
        val searchView = item?.actionView as androidx.appcompat.widget.SearchView
        searchView.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String): Boolean = false

            override fun onQueryTextChange(newText: String): Boolean {
                recyclerAdapter.filterDataBySearch(newText.trim())
                return false
            }
        })
        return super.onCreateOptionsMenu(menu)
    }
    private fun fabOnClick() {
        val intent = Intent(this, MemeAddItem::class.java)
        startActivityForResult(intent, 1)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {
        super.onActivityResult(requestCode, resultCode, intentData)

        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            intentData?.let { data ->
                val memeName = data.getStringExtra(MEME_NAME)
                Log.d("MyTagg",memeName.toString())
                recyclerAdapter.addMemeItem(memeName)
                //flowersListViewModel.insertFlower(flowerName, flowerDescription)
            }
        }
    }

}