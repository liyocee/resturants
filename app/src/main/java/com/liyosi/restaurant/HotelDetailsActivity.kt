package com.liyosi.restaurant

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.liyosi.restaurant.models.Hotel
import com.liyosi.restaurant.services.HotelsService
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_hotel_details.*

/**
 * A placeholder fragment containing a simple view.
 */
class HotelDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val hotels = HotelsService()
        val hotel: Hotel = hotels.list().get(intent.getIntExtra("hotelIdx", 0))
        setContentView(R.layout.fragment_hotel_details)

        hotelName.text = hotel.name
        hotelAddress.text = hotel.address
        hotelPhone.text = hotel.phoneNumber

        setSupportActionBar(toolbar)
    }
}
