package com.behcetemre.yksnotlarim.repo

import com.behcetemre.yksnotlarim.database.AppDao
import com.behcetemre.yksnotlarim.model.NoteModel
import com.behcetemre.yksnotlarim.view.LessonType
import javax.inject.Inject

class AppRepository @Inject constructor(private val dao: AppDao) {

    fun getNotesFromType(type: LessonType) = dao.getNotesFromType(type)
    fun getNoteCountFromType(type: LessonType) = dao.getNoteCountFromType(type)
    fun getNoteById(id: Int) = dao.getNoteById(id)

    suspend fun insertNote(note: NoteModel) = dao.insertNote(note)
    suspend fun updateNote(note: NoteModel) = dao.updateNote(note)
    suspend fun deleteNote(note: NoteModel) = dao.deleteNote(note)
}