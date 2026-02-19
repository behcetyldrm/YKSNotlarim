package com.behcetemre.yksnotlarim.view

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.behcetemre.yksnotlarim.util.ExamType
import com.behcetemre.yksnotlarim.util.Lessons
import com.behcetemre.yksnotlarim.util.Screens
import com.behcetemre.yksnotlarim.viewmodel.HomeViewModel
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val lessonsList = remember { Lessons.allLessons }
    val scope = rememberCoroutineScope()
    
    val pagerState = rememberPagerState(pageCount = { 2 })
    val selectedTab = if (pagerState.currentPage == 0) ExamType.TYT else ExamType.AYT

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(top = 16.dp)
    ) {
        Box(modifier = Modifier.padding(horizontal = 16.dp)) {
            TytAytSwitch(
                selected = selectedTab,
                onSelectedChange = { type ->
                    scope.launch {
                        pagerState.animateScrollToPage(if (type == ExamType.TYT) 0 else 1)
                    }
                }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize(),
            beyondViewportPageCount = 1
        ) { page ->
            val currentExamType = if (page == 0) ExamType.TYT else ExamType.AYT
            val filteredLessons = lessonsList.filter { it.type.examType == currentExamType }

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(filteredLessons) { lesson ->
                    val noteCount by viewModel.getNoteCountFromType(lesson.type).collectAsState()
                    LessonCard(lesson = lesson, noteCount = noteCount, navController = navController)
                }
            }
        }
    }
}

@Composable
fun LessonCard(lesson: Lessons, noteCount: Int, modifier: Modifier = Modifier, navController: NavController) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(160.dp)
            .clip(RoundedCornerShape(24.dp))
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(lesson.backgroundColor1, lesson.backgroundColor2)
                )
            )
            .clickable {
                navController.navigate(Screens.NoteListScreen.routeWithArgs(lesson.type.name))
            }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color.White.copy(alpha = 0.2f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = lesson.icon,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                }

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color.White.copy(alpha = 0.2f))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "$noteCount Not",
                        color = Color.White,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Text(
                text = lesson.name,
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.ExtraBold,
                lineHeight = 22.sp
            )
        }
    }
}

@Composable
fun TytAytSwitch(
    selected: ExamType,
    onSelectedChange: (ExamType) -> Unit,
    modifier: Modifier = Modifier
) {
    val containerWidth = 175.dp
    val containerHeight = 35.dp
    val innerPadding = 4.dp

    val itemWidth: Dp = (containerWidth - (innerPadding * 2)) / 2
    val targetOffset: Dp = if (selected == ExamType.TYT) 0.dp else itemWidth

    val animatedOffset by animateDpAsState(
        targetValue = targetOffset,
        animationSpec = tween(durationMillis = 240),
        label = "switch_offset"
    )

    Box(
        modifier = modifier
            .width(containerWidth)
            .height(containerHeight)
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFFECECEC))
            .padding(innerPadding)
    ) {
        Box(
            modifier = Modifier
                .offset(x = animatedOffset)
                .width(itemWidth)
                .fillMaxHeight()
                .clip(RoundedCornerShape(12.dp))
                .background(Color.White)
        )

        Row(modifier = Modifier.fillMaxSize()) {
            SwitchItem(
                text = "TYT",
                width = itemWidth,
                onClick = { onSelectedChange(ExamType.TYT) }
            )
            SwitchItem(
                text = "AYT",
                width = itemWidth,
                onClick = { onSelectedChange(ExamType.AYT) }
            )
        }
    }
}

@Composable
fun SwitchItem(
    text: String,
    width: Dp,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .width(width)
            .fillMaxHeight()
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF0F172A)
        )
    }
}