package com.dariushm2.stockify.model

data class Deep(var symbol: String,
                private var _marketPercent: Float,
                var volume: Long,
                private var _lastSalePrice: Float,
                var lastSaleTime: Long,
                var bids: List<Bid>,
                var asks: List<Bid>,
                var tradingStatus: TradingStatus) {
    var lastSalePrice = _lastSalePrice
        set(value) {
            field = String.format("%.02f", value).toFloat()
        }
    var marketPercent = _marketPercent
        set(value) {
            field = String.format("%.02f", value * 100).toFloat()
        }
}