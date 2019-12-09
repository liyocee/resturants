package com.liyosi.restaurant.services

import com.liyosi.restaurant.db.DatabaseHandler
import com.liyosi.restaurant.models.Hotel

class HotelsService (private val databaseHandler: DatabaseHandler){
    private val hotels: ArrayList<Hotel> = arrayListOf(
        Hotel("Sarova", "P.O BOX 4992, Nairobi", "+254715441308"),
        Hotel("Hilton", "P.O BOX 3292, Machakos", "+254715441902"),
        Hotel("Kempiski", "P.O BOX 92, Mombasa", "+254715441323"),
        Hotel("Habesha", "P.O BOX 892, Kikuyu", "+254715441312")
    )

    fun initHotels() = hotels.forEach { hotel -> databaseHandler.addHotel(hotel) }

    fun add(hotel: Hotel)  {
        databaseHandler.addHotel(hotel)
    }

    fun list(): ArrayList<Hotel> {
        return databaseHandler.listHotel()
    }
}