package com.behcetemre.yksnotlarim.module

import android.content.Context
import androidx.room.Room
import com.behcetemre.yksnotlarim.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "yks_not_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideAppDao(appDatabase: AppDatabase) = appDatabase.noteDao()

}