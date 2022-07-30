package com.example.myretrofitmemesapp

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import com.google.android.material.textfield.TextInputEditText

const val MEME_NAME = "name"
const val MEME_IMAGE = "image"
const val REQUEST_COD = 1

class MemeAddItem : AppCompatActivity() {
    private lateinit var addMemeName: TextInputEditText
    private lateinit var imageUri: Uri
    private lateinit var myImageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meme_add_item)

        myImageView = findViewById<ImageView>(R.id.myImageView)

        findViewById<Button>(R.id.done_button).setOnClickListener {
            addMeme()
        }
        addMemeName = findViewById(R.id.add_meme_name)
        myImageView.setOnClickListener {
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(gallery, REQUEST_COD)
        }
    }

    private fun addMeme(){
        val resultIntent = Intent()

        if (addMemeName.text.isNullOrEmpty() && imageUri == null) {
            setResult(Activity.RESULT_CANCELED, resultIntent)
            Log.d("Taaaaag1","Canceled")
        } else {
            val name = addMemeName.text.toString()
            val image = imageUri
            Log.d("Taaaaag1",image.toString())
            Log.d("Taaaaag1","Success")

            resultIntent.putExtra(MEME_NAME, name)
            resultIntent.putExtra(MEME_IMAGE, image.toString())  //toString always needs....
            setResult(Activity.RESULT_OK, resultIntent)
        }
        finish()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == REQUEST_COD) {
            imageUri = data?.data!!
            myImageView.setImageURI(imageUri)
        }
    }


}