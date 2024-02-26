package com.harsh.chattask

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat

class ChatAdapter(var list: ArrayList<MessageData> ): RecyclerView.Adapter<ChatAdapter.ViewHolderClass>() {
    inner class ViewHolderClass(var view: View): RecyclerView.ViewHolder(view){
        var tvMessage = view.findViewById<TextView>(R.id.tvMessage)
        var tvTime = view.findViewById<TextView>(R.id.tvTime)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatAdapter.ViewHolderClass {
        var view : View
        if (viewType == 0){
            view = LayoutInflater.from(parent.context).inflate(R.layout.rightmsg,parent,false)
        }else{
            view = LayoutInflater.from(parent.context).inflate(R.layout.leftmsg,parent,false)
        }
        return ViewHolderClass(view)
    }

    override fun getItemViewType(position: Int): Int {
        return if (list[position].id == 0){
            0
        }else 1
    }
    override fun onBindViewHolder(holder: ChatAdapter.ViewHolderClass, position: Int) {
        holder.tvMessage.setText(list[position].message)
        holder.tvTime.setText(SimpleDateFormat("hh:mm").format(list[position].dateTime.time))
    }

    override fun getItemCount(): Int {
        return list.size
    }
}