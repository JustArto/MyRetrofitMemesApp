package com.example.myretrofitmemesapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.material.textfield.TextInputEditText

const val MEME_NAME = "name"

class MemeAddItem : AppCompatActivity() {
    private lateinit var addMemeName: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meme_add_item)

        findViewById<Button>(R.id.done_button).setOnClickListener {
            addMeme()
        }
        addMemeName = findViewById(R.id.add_meme_name)
    }

    private fun addMeme() {
        val resultIntent = Intent()

        if (addMemeName.text.isNullOrEmpty()) {
            setResult(Activity.RESULT_CANCELED, resultIntent)
        } else {
            val name = addMemeName.text.toString()
            resultIntent.putExtra(MEME_NAME, name)
            setResult(Activity.RESULT_OK, resultIntent)
        }
        finish()
    }
}