package com.example.gkjkudus.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "items_table")
data class ItemRoom(
    @PrimaryKey
    @field:SerializedName("_id")
    val id: String,

    @field:SerializedName("AuctionPrice")
    val auctionPrice: String? = null,

    @field:SerializedName("StatusItem")
    val statusItem: Int? = null,

    @field:SerializedName("Item")
    val item: String? = null,

    @field:SerializedName("ItemCode")
    val itemCode: String? = null,

    @field:SerializedName("Price")
    val price: String? = null,

    @field:SerializedName("PaymentType")
    val paymentType: Int? = null,

    @field:SerializedName("Buyer")
    val buyer: String? = null,
)