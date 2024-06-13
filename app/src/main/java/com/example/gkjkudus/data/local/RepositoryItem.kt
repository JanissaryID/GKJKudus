package com.example.gkjkudus.data.local

import android.util.Log
import com.example.gkjkudus.data.AppModule
import com.example.gkjkudus.data.ItemRoom
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepositoryItem @Inject constructor(
    private val itemDao: DaoItem,
) {
    var allItems: Flow<List<ItemRoom>> = itemDao.getAllData()

    var allItemsTransaction: Flow<List<ItemRoom>> = itemDao.getAllData().map { items ->
        items.filter { it.statusItem!! <= 1 }
    }
    var allItemsPayment: Flow<List<ItemRoom>> = itemDao.getAllData().map { items ->
        items.filter { it.statusItem!! == 1 }
    }

    fun insertItem(user: ItemRoom) {
        itemDao.insertData(user)
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun fetchItemFromNetwork() {
        try {
            AppModule.CreateInstance().getItem(
                BearerToken = "Bearer " + "51772c72ff72e7a142e7aa26a7178c6c"
            ).enqueue(object : Callback<ArrayList<ItemRoom>> {
                override fun onResponse(
                    call: Call<ArrayList<ItemRoom>>,
                    response: Response<ArrayList<ItemRoom>>
                ) {
                    Log.d("item_viewmodel", "Get Data = $response")
                    if (response.code() == 200) {
                        response.body()?.let {
                            val items = response.body()!!
                            GlobalScope.launch(Dispatchers.IO) {
                                items.forEach { itemDao.insertData(it) }
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<ArrayList<ItemRoom>>, t: Throwable) {
                    Log.d("item_viewmodel", "Fail get Data ${t.message.toString()}")
                    fetchItemFromNetwork()
                }
            })
        }
        catch (e: Exception) {
            Log.e("item_viewmodel", e.toString())
        }
    }

}