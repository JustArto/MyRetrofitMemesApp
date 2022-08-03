package com.example.myretrofitmemesapp.model

data class Meme(
    val box_count: Int,
    val height: Int,
    val id: String,
    val name: String,
    val url: String,
    val width: Int) {
}

//Items params
//With this can pass in layout and set instead of wrap_content default size => height width