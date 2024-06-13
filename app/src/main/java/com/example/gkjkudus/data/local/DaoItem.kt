package com.example.gkjkudus.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.OnConflictStrategy
import com.example.gkjkudus.data.ItemRoom
import kotlinx.coroutines.flow.Flow

@Dao
interface DaoItem {
    @Query("SELECT * FROM items_table")
    fun getAllData(): Flow<List<ItemRoom>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertData(item: ItemRoom)
}