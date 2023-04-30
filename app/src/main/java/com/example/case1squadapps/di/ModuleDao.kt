package com.example.case1squadapps.di

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import com.example.case1squadapps.data.local.DatabaseDao
import com.example.case1squadapps.others.Constants.DATABASE_NAME
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@dagger.Module
@InstallIn(SingletonComponent::class)
object ModuleDao {

    @Singleton
    @Provides
    fun provideDao(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        DatabaseDao::class.java,
        DATABASE_NAME
    ).build()

    @Singleton
    @Provides
    fun provideInterfaceDao(databaseDao: DatabaseDao) = databaseDao.DBDao()
}