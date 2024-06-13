package com.example.gkjkudus.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.gkjkudus.data.ItemRoom

@Database(entities = [ItemRoom::class], version = 1, exportSchema = false)
abstract class DatabaseItem : RoomDatabase() {
    abstract fun itemDao(): DaoItem
}