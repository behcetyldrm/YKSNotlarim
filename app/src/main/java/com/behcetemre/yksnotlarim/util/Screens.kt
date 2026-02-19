package com.behcetemre.yksnotlarim.util

sealed class Screens (val route: String) {
    object HomeScreen : Screens("home_screen")
    object AddNoteScreen : Screens("add_note_screen")
    object LessonScreen : Screens("lesson_screen")
}