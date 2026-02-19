package com.behcetemre.yksnotlarim.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.behcetemre.yksnotlarim.model.NoteModel
import com.behcetemre.yksnotlarim.view.LessonType
import kotlinx.coroutines.flow.Flow

@Dao
interface AppDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: NoteModel)

    @Update
    suspend fun updateNote(note: NoteModel)

    @Delete
    suspend fun deleteNote(note: NoteModel)

    @Query("SELECT * FROM NoteModel WHERE type = :type")
    fun getNotesFromType(type: LessonType): Flow<List<NoteModel>>

    @Query("SELECT COUNT(*) FROM NoteModel WHERE type = :type")
    fun getNoteCountFromType(type: LessonType): Flow<Int>

    @Query("SELECT * FROM NoteModel WHERE id = :id")
    fun getNoteById(id: Int): Flow<NoteModel?>
}