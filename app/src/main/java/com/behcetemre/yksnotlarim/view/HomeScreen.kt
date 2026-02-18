package com.behcetemre.yksnotlarim.view

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

enum class ExamType{ TYT, AYT }
@Composable
fun HomeScreen(navController: NavController) {
    var selected by remember { mutableStateOf(ExamType.TYT) }

    Column(modifier = Modifier.padding(12.dp)) {
        TytAytSwitch(
            selected = selected,
            onSelectedChange = { selected = it }
        )
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

    val shape = RoundedCornerShape(16.dp)

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
            .clip(shape)
            .background(Color(0xFFECECEC))
            .padding(innerPadding)
    ) {
        // Kayan beyaz arka plan
        Box(
            modifier = Modifier
                .offset(x = animatedOffset)
                .width(itemWidth)
                .fillMaxHeight()
                .clip(RoundedCornerShape(16.dp))
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
private fun SwitchItem(
    text: String,
    width: Dp,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .width(width)
            .fillMaxHeight()
            .clickable (
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ){ onClick() },
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