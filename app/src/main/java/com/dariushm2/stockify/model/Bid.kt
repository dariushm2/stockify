package com.dariushm2.stockify.model

data class Bid(private var _price: Float,
               var size: Long) {
    var price = _price
    set(value) {
        field = String.format("%.02f", value).toFloat()
    }
}
