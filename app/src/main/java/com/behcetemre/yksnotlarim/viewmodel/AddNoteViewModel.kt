package com.behcetemre.yksnotlarim.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.behcetemre.yksnotlarim.model.NoteModel
import com.behcetemre.yksnotlarim.repo.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddNoteViewModel @Inject constructor(
    private val repository: AppRepository
): ViewModel() {

    fun insertNote(note: NoteModel) {
        viewModelScope.launch {
            repository.insertNote(note)
        }
    }
}