package com.harsh.chattask
import android.app.Dialog
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.harsh.chattask.ChatAdapter
import com.harsh.chattask.ChatMessage
import com.harsh.chattask.Position
import com.harsh.chattask.R
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ChatAdapter
    private val messages = mutableListOf<ChatMessage>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = ChatAdapter(messages)
        recyclerView.adapter = adapter

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { showDialog() }
    }

    private fun showDialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_layout)
        val window = dialog.window
        window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)

        val messageEditText = dialog.findViewById<EditText>(R.id.message_edittext)
        val positionRadioGroup = dialog.findViewById<RadioGroup>(R.id.position_radio_group)
        val submitButton = dialog.findViewById<Button>(R.id.submit_button)

        submitButton.setOnClickListener {
            val message = messageEditText.text.toString().trim()
            if (message.isNotEmpty()) {
                val selectedRadioButtonId = positionRadioGroup.checkedRadioButtonId
                val selectedRadioButton = dialog.findViewById<RadioButton>(selectedRadioButtonId)
                val position = if (selectedRadioButton.text == "First") Position.FIRST else Position.SECOND
                val time = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())

                messages.add(ChatMessage(message, position, time))
                adapter.notifyDataSetChanged()
                recyclerView.smoothScrollToPosition(messages.size - 1)
                dialog.dismiss()
            } else {
                Toast.makeText(this@MainActivity, "Please enter a message", Toast.LENGTH_SHORT).show()
            }
        }

        dialog.show()
    }
}
