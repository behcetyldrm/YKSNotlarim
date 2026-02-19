package com.behcetemre.yksnotlarim.util

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoGraph
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.Grass
import androidx.compose.material.icons.filled.HistoryEdu
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.filled.Science
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

data class Lessons(
    val name: String,
    val icon: ImageVector,
    val type: LessonType,
    val backgroundColor1: Color,
    val backgroundColor2: Color
) {
    companion object {
        val allLessons = listOf(
            Lessons("TYT Türkçe", Icons.Default.HistoryEdu, LessonType.TYT_TURKCE, Color(0xFFA855F7), Color(0xFFEC4899)),
            Lessons("TYT Sosyal", Icons.Default.Public, LessonType.TYT_SOSYAL, Color(0xFF3B82F6), Color(0xFF06B6D4)),
            Lessons("TYT Matematik", Icons.Default.Calculate, LessonType.TYT_MATEMATIK, Color(0xFFF97316), Color(0xFFEF4444)),
            Lessons("TYT Fizik", Icons.Default.Bolt, LessonType.TYT_FIZIK, Color(0xFF22C55E), Color(0xFF10B981)),
            Lessons("TYT Biyoloji", Icons.Default.Grass, LessonType.TYT_BIYOLOJI, Color(0xFF14B8A6), Color(0xFF0891B2)),
            Lessons("TYT Kimya", Icons.Default.Science, LessonType.TYT_KIMYA, Color(0xFF6366F1), Color(0xFFA855F7)),

            Lessons("AYT Matematik", Icons.Default.AutoGraph, LessonType.AYT_MATEMATIK, Color(0xFFF43F5E), Color(0xFFEA580C)),
            Lessons("AYT Fizik", Icons.Default.Bolt, LessonType.AYT_FIZIK, Color(0xFF84CC16), Color(0xFF16A34A)),
            Lessons("AYT Biyoloji", Icons.Default.Grass, LessonType.AYT_BIYOLOJI, Color(0xFF10B981), Color(0xFF0D9488)),
            Lessons("AYT Kimya", Icons.Default.Science, LessonType.AYT_KIMYA, Color(0xFF8B5CF6), Color(0xFFD946EF))
        )
    }
}
