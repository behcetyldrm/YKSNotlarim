package com.behcetemre.yksnotlarim.util

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.behcetemre.yksnotlarim.view.LessonType

data class Lessons(
    val name: String,
    val icon: ImageVector,
    val type: LessonType,
    val backgroundColor1: Color,
    val backgroundColor2: Color
)
