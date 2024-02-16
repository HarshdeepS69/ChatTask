package com.harsh.chattask

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ChatAdapter(private val messages: List<ChatMessage>) : RecyclerView.Adapter<ChatAdapter.ChatViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_chat_message, parent, false)
        return ChatViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        val message = messages[position]
        holder.bind(message)
    }

    override fun getItemCount(): Int = messages.size

    inner class ChatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val messageTextView: TextView = itemView.findViewById(R.id.message_text_view)
        private val timeTextView: TextView = itemView.findViewById(R.id.time_text_view)

        fun bind(message: ChatMessage) {
            messageTextView.text = message.message
            timeTextView.text = message.time

            val params = messageTextView.layoutParams as ViewGroup.MarginLayoutParams
            if (message.position == Position.FIRST) {
                params.marginStart = 0
                params.marginEnd = itemView.resources.getDimensionPixelOffset(R.dimen.message_end_margin)
            } else {
                params.marginStart = itemView.resources.getDimensionPixelOffset(R.dimen.message_end_margin)
                params.marginEnd = 0
            }
            messageTextView.layoutParams = params
        }
    }
}
