package com.example.myretrofitmemesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.myretrofitmemesapp.databinding.ActivityMemeItemInfoBinding

class MemeItemInfo : AppCompatActivity() {

    lateinit var binding: ActivityMemeItemInfoBinding //DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMemeItemInfoBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val image = intent.getStringExtra("DataImage")
        Glide.with(this).load(image)
            .apply(RequestOptions().centerCrop())
            .into(binding.imageView)

        val name = intent.getStringExtra("DataName")
        binding.tvName.text ="Name: "+name

        val box = intent.getStringExtra("DataBox")
        binding.tvBoxCount.text = "Box Count: "+box

        val heigth = intent.getStringExtra("DataH")
        binding.tvHeight.text = "Height: "+heigth

        val width = intent.getStringExtra("DataW")
        binding.tvWidth.text = "Width: "+heigth
    }
}