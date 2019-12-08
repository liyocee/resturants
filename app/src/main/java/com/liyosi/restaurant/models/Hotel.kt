package com.liyosi.restaurant.models

data class Hotel(val name: String, val address: String, val phoneNumber: String) {
    override fun toString(): String {
        return name
    }
}