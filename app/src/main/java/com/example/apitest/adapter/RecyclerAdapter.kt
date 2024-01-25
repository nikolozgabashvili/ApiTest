package com.example.apitest.adapter

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.apitest.classes.CardClass
import com.example.apitest.databinding.CardItemBinding
import kotlin.concurrent.timerTask

class RecyclerAdapter(private var infoList: List<CardClass>,
    private val listener: OnItemClickListener

    ):RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        return RecyclerViewHolder(CardItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount()  = infoList.size


    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val item = infoList[position]

        with(holder) {
            title.text = item.title
            user.text = item.userName
            Glide.with(back)
                .load(item.imageUrl)
                .apply(RequestOptions().centerCrop())
                .into(object : CustomTarget<Drawable>() {
                    override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                        back.background = resource
                    }

                    override fun onLoadCleared(placeholder: Drawable?) {

                    }
                })


        }
    }

    inner class RecyclerViewHolder(binding:CardItemBinding):RecyclerView.ViewHolder(binding.root),
        View.OnClickListener {

        val title = binding.title
        val user = binding.username
        val back = binding.root

        init {
            binding.root.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position =  adapterPosition
            listener.onItemClick(position)
        }
    }
    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }
}