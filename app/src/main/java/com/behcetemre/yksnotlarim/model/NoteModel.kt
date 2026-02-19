package com.behcetemre.yksnotlarim.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.behcetemre.yksnotlarim.view.LessonType

@Entity
data class NoteModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val content: String,
    val type: LessonType,
    val imageUri: String? = null,
)
