package com.behcetemre.yksnotlarim.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.behcetemre.yksnotlarim.model.NoteModel

@Database(entities = [NoteModel::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun noteDao(): AppDao
}