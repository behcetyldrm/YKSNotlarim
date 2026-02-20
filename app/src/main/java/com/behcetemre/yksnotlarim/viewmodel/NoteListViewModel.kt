package com.behcetemre.yksnotlarim.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.behcetemre.yksnotlarim.model.NoteModel
import com.behcetemre.yksnotlarim.repo.AppRepository
import com.behcetemre.yksnotlarim.util.LessonType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class NoteListViewModel @Inject constructor(
    private val noteRepository: AppRepository
) : ViewModel() {

    private val lessonType = MutableStateFlow<LessonType?>(null)
    val notes : StateFlow<List<NoteModel>> = lessonType.flatMapLatest { lesson ->
        if (lesson != null){
            noteRepository.getNotesFromType(lesson)
        } else {
            flowOf(emptyList())
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    fun setType(type: LessonType){
        lessonType.value = type
    }

    private val selectedId = MutableStateFlow<Int?>(null)
    val selectedNote : StateFlow<NoteModel?> = selectedId.flatMapLatest { id ->
        if (id != null){
            noteRepository.getNoteById(id)
        } else {
            flowOf(null)
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = null
    )

    fun selectId(id: Int?){
        selectedId.value = id
    }

    fun updateNote(note: NoteModel){
        viewModelScope.launch {
            noteRepository.updateNote(note)
        }
    }

    fun deleteNote(note: NoteModel) {
        viewModelScope.launch {
            noteRepository.deleteNote(note)
        }
    }
}