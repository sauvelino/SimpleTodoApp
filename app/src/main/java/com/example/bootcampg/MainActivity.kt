package com.example.bootcampg

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    private lateinit var arrayList: ArrayList<String>
    private lateinit var arrayAdapter: ArrayAdapter<String>
    private lateinit var input: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val listView = findViewById<ListView>(R.id.listtt)
        arrayList = ArrayList()
        arrayAdapter = ArrayAdapter(this, R.layout.custom, R.id.textView, arrayList)
        listView.adapter = arrayAdapter
        input = findViewById(R.id.editText)
        val insert = findViewById<Button>(R.id.button)
        insert.setOnClickListener {
            val newItems = input.getText().toString()
            arrayList.add(newItems)
            arrayAdapter.notifyDataSetChanged()
            input.text.clear()
        }

        listView.onItemLongClickListener = AdapterView.OnItemLongClickListener { adapterView, view, i, l ->
            arrayAdapter.remove(arrayList.get(i))
            false
        }

        listView.onItemClickListener =
            AdapterView.OnItemClickListener { adapterView, view, i, l -> InputBox(arrayList.get(i), i) }
    }

    fun InputBox(Olditem: String, index: Int) {
        val dialog = Dialog(this@MainActivity)
        dialog.setTitle("Update")
        dialog.setContentView(R.layout.box)
        val editText = dialog.findViewById<EditText>(R.id.editText2)
        editText.setText(Olditem)
        val ntn = dialog.findViewById<Button>(R.id.update)
        ntn.setOnClickListener {
            arrayList.set(index, editText.text.toString())
            arrayAdapter.notifyDataSetChanged()
            dialog.dismiss()
        }
        dialog.show()
    }
}
