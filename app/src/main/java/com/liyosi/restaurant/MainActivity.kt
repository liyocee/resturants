package com.liyosi.restaurant

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.liyosi.restaurant.db.DatabaseHandler
import com.liyosi.restaurant.models.Hotel
import com.liyosi.restaurant.services.HotelsService

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        val databaseHandler = DatabaseHandler(this)
        val hotels = HotelsService(databaseHandler)

        val arrayAdapter: ArrayAdapter<Hotel> = ArrayAdapter(
            this, android.R.layout.simple_list_item_1,hotels.list())

        listView.adapter = arrayAdapter

        listView.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, poistion, id ->
            val selectedHotel: Hotel = adapterView.getItemAtPosition(poistion) as Hotel
            val intent = Intent(this, HotelDetailsActivity::class.java)
            intent.putExtra("hotelIdx", poistion)
            startActivity(intent)
            Toast.makeText(applicationContext,"Viewing hotel ${selectedHotel.name}",Toast.LENGTH_SHORT).show()
        }

        addHotelButton.setOnClickListener { view ->
            val intent = Intent(this, AddHotelActivity::class.java)
            startActivity(intent)
        }

    }
}
