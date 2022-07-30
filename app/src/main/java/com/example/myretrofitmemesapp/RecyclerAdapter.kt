package com.example.myretrofitmemesapp

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.myretrofitmemesapp.model.Meme
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.http.Url


class RecyclerAdapter(val context: Context) : RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>() {

    var memeItemList : MutableList<Meme> = mutableListOf()
    private var changedData: MutableList<Meme> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_adapter,parent,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return changedData.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentItem = changedData[position]

        holder.tvMovieName.text = currentItem.name
        Glide.with(context).load(currentItem.url)
                .apply(RequestOptions().centerCrop())
                .into(holder.image)

        holder.itemView.setOnClickListener {
            //Set your codes about intent here
            val intent = Intent(holder.itemView.context, MemeItemInfo::class.java)
            intent.putExtra("Data", "${changedData[position].name}")
            intent.putExtra("Data1", "${changedData[position].url}")
//            intent.putExtra("Data", "${memeItemList[position].name}")
//            intent.putExtra("Data1", "${memeItemList[position].url}")
            holder.itemView.context.startActivity(intent)
        }

        holder.myRemoveButton.setOnClickListener(){
            myRemoveListItem(position)
        }

    }

    @SuppressLint("NotifyDataSetChanged")
    fun setMemeListItems(memeList: List<Meme>){
        memeItemList.clear()
        memeItemList.addAll(memeList)
        changedData.addAll(memeItemList)
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addMemeItem(mItemName: String?, mItemImage: String?){
        mItemImage
        changedData.add(Meme(name = mItemName!!, height = 0, box_count = 0, id = "", url = mItemImage!!.toString(), width = 0))
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun filterDataBySearch(queryText: String) {
        changedData.clear()
        if (queryText.isNotEmpty()) {
            val filteredData =
                memeItemList.filter {
                    it.name.contains(queryText, ignoreCase = true)
                }
                    .toMutableList()
            changedData.addAll(filteredData)
            notifyDataSetChanged()
        } else {
            changedData.addAll(memeItemList)
            notifyDataSetChanged()
        }
    }
    fun myRemoveListItem(position: Int){
        changedData.removeAt(position)
        notifyDataSetChanged()
    }

    class MyViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        val tvMovieName: TextView = itemView!!.findViewById(R.id.title)
        val myRemoveButton: Button = itemView!!.findViewById(R.id.button)
        val image: ImageView = itemView!!.findViewById(R.id.image)

    }
}