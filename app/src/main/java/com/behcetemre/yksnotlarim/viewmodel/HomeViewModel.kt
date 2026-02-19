package com.behcetemre.yksnotlarim.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.behcetemre.yksnotlarim.repo.AppRepository
import com.behcetemre.yksnotlarim.view.LessonType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: AppRepository) : ViewModel() {

    fun getNoteCountFromType(type: LessonType) : StateFlow<Int> {
        return repository.getNoteCountFromType(type)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = 0
            )
    }
}