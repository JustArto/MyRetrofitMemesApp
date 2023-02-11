package com.example.myretrofitmemesapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myretrofitmemesapp.databinding.ActivityMainBinding
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
        //For Navigation menu
    lateinit var binding : ActivityMainBinding
    lateinit var toggle: ActionBarDrawerToggle

/*    override fun onCreate(savedInstanceState: Bundle?) {
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

    }*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerAdapter = RecyclerAdapter(this)
        //recyclerview.layoutManager = LinearLayoutManager(this)
        binding.recyclerview.layoutManager = LinearLayoutManager(this)
        binding.recyclerview.adapter = recyclerAdapter

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

        binding.apply {
            toggle = ActionBarDrawerToggle(this@MainActivity, drawerLayout, R.string.open, R.string.close)
            drawerLayout.addDrawerListener(toggle)
            toggle.syncState()

            supportActionBar?.setDisplayHomeAsUpEnabled(true)

            navView.setNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.firstItem -> {
                        Toast.makeText(this@MainActivity, "First Item Clicked", Toast.LENGTH_SHORT).show()
                    }
                    R.id.secondtItem -> {
                        Toast.makeText(this@MainActivity, "Second Item Clicked", Toast.LENGTH_SHORT).show()
                    }
                    R.id.thirdItem -> {
                        Toast.makeText(this@MainActivity, "third Item Clicked", Toast.LENGTH_SHORT).show()
                    }
                }
                true
            }

        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean { //Nav Draver
        if (toggle.onOptionsItemSelected(item)){
            true
        }
        return super.onOptionsItemSelected(item)
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
                val memeImage = data.getStringExtra(MEME_IMAGE)
                Log.d("Taaaaag2",memeName.toString())
                Log.d("Taaaaag2", memeImage.toString()) //null

                recyclerAdapter.addMemeItem(memeName,memeImage)
                //flowersListViewModel.insertFlower(flowerName, flowerDescription)
            }
        }
    }

}