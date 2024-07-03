package com.sonia.ordertask

data class Order(
    var name: String?="",
    var quantity : Int?=0,
) {
    override fun toString(): String {
        return "$name "
    }
}
