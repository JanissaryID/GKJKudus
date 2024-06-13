package com.example.gkjkudus

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.gkjkudus.data.ItemViewModel
import com.example.gkjkudus.navigation.NavGraphSetup
import com.example.gkjkudus.ui.theme.GKJKudusTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GKJKudusTheme(darkTheme = false) {

                val itemViewModel = hiltViewModel<ItemViewModel>()
                itemViewModel.fetchItems()

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    navController = rememberNavController()

                    Greeting(
                        itemViewModel = itemViewModel,
                        modifier = Modifier.padding(innerPadding),
                        navController = navController
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(itemViewModel: ItemViewModel, modifier: Modifier = Modifier, navController: NavHostController) {
    NavGraphSetup(
        navController = navController,
        itemViewModel = itemViewModel
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
//    GKJKudusTheme {
//        Greeting("Android")
//    }
}