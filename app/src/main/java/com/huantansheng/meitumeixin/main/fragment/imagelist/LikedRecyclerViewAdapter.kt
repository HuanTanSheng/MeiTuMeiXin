package com.huantansheng.meitumeixin.main.fragment.imagelist

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.RequestManager
import com.huantansheng.meitumeixin.R
import com.huantansheng.meitumeixin.controller.ViewController
import com.huantansheng.meitumeixin.listener.CommonListener
import kotlinx.android.synthetic.main.item_fragment_main_liked.view.*

/**
 * Created by huan on 2017/7/10.
 */

class LikedRecyclerViewAdapter(val glide: RequestManager, val list: ArrayList<String>, val inflater: LayoutInflater, val screenWidth: Int, val spanCount: Int, val listener: CommonListener.OnPhotoClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return LikedViewHolder(inflater.inflate(R.layout.item_fragment_main_liked, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder as LikedViewHolder
        with(holder.itemView) {
            ViewController.setCardViewParams(screenWidth, spanCount, card_view, 1080, 1920)
            glide.clear(iv_photo)
            glide.load(list[position]).thumbnail(0.1f).into(iv_photo)
            iv_photo.setOnClickListener(View.OnClickListener { listener.onPhotoClick(position) })
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class LikedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}

}