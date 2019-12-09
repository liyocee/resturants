package com.liyosi.restaurant

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.liyosi.restaurant.db.DatabaseHandler
import com.liyosi.restaurant.models.Hotel
import com.liyosi.restaurant.services.HotelsService

import kotlinx.android.synthetic.main.activity_add_hotel.*
import kotlinx.android.synthetic.main.activity_add_hotel.toolbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_add_hotel.*

class AddHotelActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_hotel)
        setSupportActionBar(toolbar)
        val databaseHandler = DatabaseHandler(this)
        val hotels = HotelsService(databaseHandler)

        createHotelButton.setOnClickListener { view ->
            val hotel = Hotel(addHotelName.text.toString(), addHotelAddress.text.toString(), addHotelPhone.text.toString())
            hotels.add(hotel)
            Toast.makeText(applicationContext,"New hotel hotel ${hotel.name} added", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

}
