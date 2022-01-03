package com.example.myretrofitmemesapp

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.myretrofitmemesapp.model.Meme



//val callbackInterface: CallbackInterface,
class RecyclerAdapter(val context: Context) : RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>() {

   /* interface CallbackInterface {  //for passing data in MainActivity.... listi copyin Main
        fun passResultCallback(message: MutableList<Meme>)
    }

    lateinit var mListner: onItemClickListener
    interface onItemClickListener{
        fun onItemClick(position: Int)
    }
    fun setOnItemClickListner(listener: onItemClickListener){
        mListner = listener
    }*/


    var memeItemList : MutableList<Meme> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_adapter,parent,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return memeItemList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.tvMovieName.text = memeItemList[position].name
        Glide.with(context).load(memeItemList[position].url)
                .apply(RequestOptions().centerCrop())
                .into(holder.image)

        holder.itemView.setOnClickListener {
            //Set your codes about intent here
            val intent = Intent(holder.itemView.context, MemeItemInfo::class.java)
            intent.putExtra("Data", "${memeItemList[position].name}")
            intent.putExtra("Data1", "${memeItemList[position].url}")
            holder.itemView.context.startActivity(intent)
            //callbackInterface.passResultCallback(mutableListOf<Meme>(memeItemList[position]))
        }
        holder.itemView.setOnClickListener {
            //Set your codes about intent here
            val intent = Intent(holder.itemView.context, MemeItemInfo::class.java)
            intent.putExtra("Meme", "${memeItemList}")
            holder.itemView.context.startActivity(intent)
            //callbackInterface.passResultCallback(mutableListOf<Meme>(memeItemList[position]))
        }

    }

    @SuppressLint("NotifyDataSetChanged")
    fun setMemeListItems(memeList: List<Meme>){
        memeItemList.clear()
        memeItemList.addAll(memeList)
        notifyDataSetChanged()
    }

    class MyViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        val tvMovieName: TextView = itemView!!.findViewById(R.id.title)
        val image: ImageView = itemView!!.findViewById(R.id.image)
        

     /*   init {
            itemView!!.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }*/
    }
}