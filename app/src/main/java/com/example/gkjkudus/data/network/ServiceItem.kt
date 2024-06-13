package com.example.gkjkudus.data.network

import com.example.gkjkudus.data.ItemRoom
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.Path

interface ServiceItem {
    @PATCH("Gereja/{id}")
    fun updateItem(
        @Header("Authorization") BearerToken: String,
        @Path("id") id: String?, @Body updateData : ItemRoom
    ): Call<ItemRoom>

    @GET("Gereja")
    fun getItem(
        @Header("Authorization") BearerToken: String
    ): Call<ArrayList<ItemRoom>>

//    @GET("Gereja")
//    fun getDataItem(
//        @Header("Authorization") BearerToken: String
//    ): List<Item>
}