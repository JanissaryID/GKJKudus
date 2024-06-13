package com.example.gkjkudus.data

import android.content.Context
import androidx.room.Room
import com.example.gkjkudus.data.local.DaoItem
import com.example.gkjkudus.data.local.DatabaseItem
import com.example.gkjkudus.data.network.ServiceItem
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private var BASE_URL = "https://api.kontenbase.com/query/api/v1/d11e834d-5663-4415-9bee-cfb371e77a2e/"

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): DatabaseItem {
        return Room.databaseBuilder(appContext, DatabaseItem::class.java, "app_database")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideUserDao(db: DatabaseItem): DaoItem = db.itemDao()

    @Provides
    @Singleton
    fun CreateInstance(): ServiceItem {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(ServiceItem::class.java)
    }
}