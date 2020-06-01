package com.example.myapplication0528

import android.content.Context
import android.hardware.input.InputManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.renderscript.ScriptGroup
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListAdapter
import android.widget.TextView
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var data: ArrayList<People>
    private lateinit var adapter: MyListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = Room.databaseBuilder(
            applicationContext,
            RoomDatabase::class.java, "database-name"
        ).build()
        data = ArrayList()
        data.add(People(0, "김갹갹", "010123456780", 19))
        adapter = MyListAdapter(this, data)
        list_database.adapter = adapter


        button_add.setOnClickListener {

            if (this.input_name.text.isNotBlank()) {
                val name = this.input_name.text.toString()
                val phone = this.input_phone.text.toString()
                val age = this.input_age.text.toString()

                val p = if (age.isEmpty()) {
                    People(0, name, phone, age.toInt())
                } else {
                    People(0, name, phone, age.toInt())
                }
                val addThread = Thread { db.peopleDao().insert(p) }
                addThread.start()

                data.add(p)
                adapter.notifyDataSetChanged()
            }
        }


    }


}

class MyListAdapter constructor(context: Context, data: ArrayList<People>): BaseAdapter() {
    private val data: ArrayList<People> = data
    private val inﬂater: LayoutInflater = LayoutInflater.from(context)
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        if(convertView == null) {
            view = this.inﬂater.inflate(R.layout.list_item, parent, false)
            var itemId = view.findViewById<TextView>(R.id.item_id)
            itemId.text = data[position].id.toString()
            var itemName = view.findViewById<TextView>(R.id.item_name)
            itemName.text = data[position].name
            var itemPhone = view.findViewById<TextView>(R.id.item_phone)
            itemPhone.text = data[position].phone
            var itemAge = view.findViewById<TextView>(R.id.item_age)
            itemAge.text = data[position].age.toString()        }
        else {            view = convertView        }
        return view    }

    override fun getItem(position: Int): Any= (data[position])
    override fun getItemId(position: Int): Long =position.toLong()
    override fun getCount(): Int =data.size

    }





