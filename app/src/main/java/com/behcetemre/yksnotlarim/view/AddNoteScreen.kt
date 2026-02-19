package com.behcetemre.yksnotlarim.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.behcetemre.yksnotlarim.R
import com.behcetemre.yksnotlarim.model.NoteModel
import com.behcetemre.yksnotlarim.util.LessonType
import com.behcetemre.yksnotlarim.viewmodel.AddNoteViewModel

@Composable
fun AddNoteScreen(
    navController: NavController,
    viewModel: AddNoteViewModel = hiltViewModel()
) {

    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }
    var selectedLesson by remember { mutableStateOf(LessonType.AYT_BIYOLOJI) }

    var showBottomSheet by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current


    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
    ) {
        item {
            Text(
                text = "Fotoğraf",
                color = Color.Black,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(Modifier.height(6.dp))
            Image(
                painter = painterResource(R.drawable.fotograf_ekle),
                contentDescription = "Fotoğraf Ekle",
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .height(200.dp),
                alignment = Alignment.Center,
                contentScale = ContentScale.Crop
            )
        }
        item {
            Text(
                text = "Not Başlığı",
                color = Color.Black,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(Modifier.height(6.dp))
            SpecialTextField(
                value = title,
                onValueChange = { title = it },
                placeholder = "Süreklilik Kuralı, Mitoz Aşamaları..."
            )
        }

        item {
            Text(
                text = "Ders Seçin",
                color = Color.Black,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(Modifier.height(6.dp))
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) {
                        focusManager.clearFocus()
                        showBottomSheet = true
                    },
                shape = RoundedCornerShape(12.dp),
                color = Color(0xffE1E2EC)
            ) {
                Text(
                    text = selectedLesson.title,
                    modifier = Modifier.padding(16.dp),
                    color = Color.Black
                )

            }
        }

        item {
            Text(
                text = "Not İçeriği",
                color = Color.Black,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(Modifier.height(6.dp))
            SpecialTextField(
                value = content,
                onValueChange = { content = it },
                placeholder = "Yazın...",
                singleLine = false
            )
        }

        item {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ){
                Button(
                    onClick = {
                        val note = NoteModel(
                            title = title,
                            content = content,
                            type = selectedLesson
                        )
                        viewModel.insertNote(note)
                        navController.popBackStack()
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFF44336),
                        contentColor = Color.White
                    )
                ) {
                    Text(text = "Kaydet")
                }
            }
        }
    }
}

@Composable
fun SpecialTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    singleLine: Boolean = true,
) {

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(text = placeholder) },
        singleLine = singleLine,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.Words
        ),
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color(0xffE1E2EC),
            focusedContainerColor = Color(0xffE1E2EC),
            focusedIndicatorColor = Color(0xff101828),
            unfocusedIndicatorColor = Color.Transparent
        )
    )
}