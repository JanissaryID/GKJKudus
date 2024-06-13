package com.example.gkjkudus.data

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gkjkudus.data.local.RepositoryItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class ItemViewModel @Inject constructor(
    private val repository: RepositoryItem
) : ViewModel() {

//    val allUsers: Flow<List<ItemRoom>> = userDao.getAllUsers()

    val items: StateFlow<List<ItemRoom>> = repository.allItems.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    val itemsTransaction: StateFlow<List<ItemRoom>> = repository.allItemsTransaction.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    val itemsPayment: StateFlow<List<ItemRoom>> = repository.allItemsPayment.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    fun addItem(item: ItemRoom) {
        viewModelScope.launch {
            repository.insertItem(item)
        }
    }

    fun fetchItems() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.fetchItemFromNetwork()
        }
    }

    fun updateItems(
        idItem: String,
        buyer: String,
        auctionPrice: String,
        callback: (Int) -> Unit
    ) {
        val bodyUpdate = ItemRoom(
            statusItem = 1,
            buyer = buyer,
            auctionPrice = auctionPrice,
            paymentType = 0,
            id = idItem
        )

        try {
            AppModule.CreateInstance().updateItem(
                BearerToken = "Bearer 51772c72ff72e7a142e7aa26a7178c6c",
                id = idItem,
                updateData = bodyUpdate
            ).enqueue(object : Callback<ItemRoom> {
                override fun onResponse(call: Call<ItemRoom>, response: Response<ItemRoom>) {
                    Log.d("item_viewmodel", "Update Data = $response")
                    fetchItems()
                    callback(response.code())
                }

                override fun onFailure(call: Call<ItemRoom>, t: Throwable) {
                    Log.d("item_viewmodel", "Fail get Data ${t.message}")
                    callback(-1) // or any error code you prefer
                }
            })
        } catch (e: Exception) {
            Log.e("item_viewmodel", e.toString())
            callback(-1)
        }
    }

    fun updateItemsPayment(
        idItem: String,
        payment: Int,
        callback: (Int) -> Unit
    ) {
        val bodyUpdate = ItemRoom(
            statusItem = 2,
            paymentType = payment,
            id = idItem
        )

        try {
            AppModule.CreateInstance().updateItem(
                BearerToken = "Bearer 51772c72ff72e7a142e7aa26a7178c6c",
                id = idItem,
                updateData = bodyUpdate
            ).enqueue(object : Callback<ItemRoom> {
                override fun onResponse(call: Call<ItemRoom>, response: Response<ItemRoom>) {
                    Log.d("item_viewmodel", "Update Data = $response")
                    fetchItems()
                    callback(response.code())
                }

                override fun onFailure(call: Call<ItemRoom>, t: Throwable) {
                    Log.d("item_viewmodel", "Fail get Data ${t.message}")
                    callback(-1) // or any error code you prefer
                }
            })
        } catch (e: Exception) {
            Log.e("item_viewmodel", e.toString())
            callback(-1)
        }
    }
}