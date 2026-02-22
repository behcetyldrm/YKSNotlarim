package com.behcetemre.yksnotlarim.viewmodel

import androidx.lifecycle.ViewModel
import com.behcetemre.yksnotlarim.repo.AppRepository
import com.behcetemre.yksnotlarim.util.LessonType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: AppRepository) : ViewModel() {

    fun getNoteCountFromType(type: LessonType): Flow<Int> {
        return repository.getNoteCountFromType(type)
    }
}