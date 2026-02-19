package com.behcetemre.yksnotlarim

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.behcetemre.yksnotlarim.ui.theme.YKSNotlarımTheme
import com.behcetemre.yksnotlarim.util.Screens
import com.behcetemre.yksnotlarim.view.AddNoteScreen
import com.behcetemre.yksnotlarim.view.HomeScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            YKSNotlarımTheme {
                val navController = rememberNavController()
                val navBackStackEntry = navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry.value?.destination?.route

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = { TopAppBar() },
                    floatingActionButton = {
                        if (currentRoute != Screens.AddNoteScreen.route)
                            FloatingButton(navController)
                    }
                ) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        NavHost(navController = navController, startDestination = Screens.HomeScreen.route){

                            composable(Screens.HomeScreen.route){
                                HomeScreen(navController)
                            }

                            composable(Screens.AddNoteScreen.route){
                                AddNoteScreen(navController)
                            }

                            composable(Screens.LessonScreen.route){

                            }
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar() {

    CenterAlignedTopAppBar(
        title = { Text(
            text = "YKS Notlarım",
            color = Color.White,
            fontSize = 28.sp,
            fontWeight = FontWeight.SemiBold
        ) },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xFFF44336)
        ),
        modifier = Modifier.clip(RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp))
    )
}

@Composable
fun FloatingButton(navController: NavController) {
    FloatingActionButton(
        onClick = {
            navController.navigate(Screens.AddNoteScreen.route)
        },
        containerColor = Color(0xFFF44336),
        contentColor = Color.White,
        shape = CircleShape
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "Add Note"
        )
    }
}
