package com.liyosi.restaurant.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import com.liyosi.restaurant.models.Hotel
import java.util.logging.Logger

class DatabaseHandler(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    val Log = Logger.getLogger(DatabaseHandler::class.java.name)

    companion object{
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "hotels_03.db"

        private const val TABLE_HOTELS = "hotelsTable"
        private const val KEY_NAME = "name"
        private const val KEY_ADDRESS = "address"
        private const val KEY_PHONE = "phoneNumber"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createHotelsTable =  ("CREATE TABLE " + TABLE_HOTELS + "("
                + KEY_NAME + " TEXT,"
                + KEY_ADDRESS + " TEXT,"
                + KEY_PHONE + " TEXT" + ")")

        Log.info(createHotelsTable)
        db.execSQL(createHotelsTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_HOTELS")
        onCreate(db)
    }


    fun addHotel(hotel: Hotel): Long {
        Log.info("Adding hotel $hotel")
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_NAME, hotel.name)
        contentValues.put(KEY_ADDRESS, hotel.address)
        contentValues.put(KEY_PHONE, hotel.phoneNumber)

        // insert row
        val success = db.insert(TABLE_HOTELS, null, contentValues)
        db.close()
        return success
    }

    fun listHotel(): ArrayList<Hotel> {
        val hotels: ArrayList<Hotel> = ArrayList()
        val readQuery = "SELECT * FROM $TABLE_HOTELS"
        val db = this.readableDatabase
        var cursor: Cursor? = null

        try{
            cursor = db.rawQuery(readQuery, null)
        } catch (e: SQLiteException) {
            print(e.stackTrace)
            print(e.message)
            db.execSQL(readQuery)
            return ArrayList()
        }

        var name: String
        var phone: String
        var address: String
        if (cursor.moveToFirst()) {
            do {
                name = cursor.getString(cursor.getColumnIndex("name"))
                address = cursor.getString(cursor.getColumnIndex("address"))
                phone = cursor.getString(cursor.getColumnIndex("phoneNumber"))
                val hotel = Hotel(name=name, address = address, phoneNumber = phone)
                hotels.add(hotel)
            } while (cursor.moveToNext())
        }

        return hotels
    }
}