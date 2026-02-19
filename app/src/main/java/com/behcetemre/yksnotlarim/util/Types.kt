package com.behcetemre.yksnotlarim.util

enum class ExamType { TYT, AYT }

enum class LessonType(val examType: ExamType, val title: String) {
    // TYT
    TYT_TURKCE(ExamType.TYT, "TYT Türkçe"),
    TYT_SOSYAL(ExamType.TYT, "TYT Sosyal"),
    TYT_MATEMATIK(ExamType.TYT, "TYT Matematik"),
    TYT_FIZIK(ExamType.TYT, "TYT Fizik"),
    TYT_BIYOLOJI(ExamType.TYT, "TYT Biyoloji"),
    TYT_KIMYA(ExamType.TYT, "TYT Kimya"),

    // AYT
    AYT_MATEMATIK(ExamType.AYT, "AYT Matematik"),
    AYT_FIZIK(ExamType.AYT, "AYT Fizik"),
    AYT_BIYOLOJI(ExamType.AYT, "AYT Biyoloji"),
    AYT_KIMYA(ExamType.AYT, "AYT Kimya")
}