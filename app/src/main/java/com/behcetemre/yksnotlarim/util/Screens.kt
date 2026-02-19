package com.behcetemre.yksnotlarim.util

sealed class Screens (val route: String) {
    object HomeScreen : Screens("home_screen")
    object AddNoteScreen : Screens("add_note_screen")
    object NoteListScreen : Screens("note_list_screen/{lesson}"){
        fun routeWithArgs(lesson: String) = "note_list_screen/$lesson"
    }
}