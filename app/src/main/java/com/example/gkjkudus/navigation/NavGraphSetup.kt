package com.example.gkjkudus.navigation

import androidx.annotation.OptIn
import androidx.camera.core.ExperimentalGetImage
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.gkjkudus.data.ItemViewModel
import com.example.gkjkudus.view.ui.page.PageHome
import com.example.gkjkudus.view.ui.page.PageQrScanning

//67899
@OptIn(ExperimentalGetImage::class)
@Composable
fun NavGraphSetup(
    navController: NavHostController,
    itemViewModel: ItemViewModel
) {
    NavHost(navController = navController, startDestination = Pages.Home.route){

        composable(
            route = Pages.Home.route,
        ){
            PageHome(navController = navController, itemViewModel = itemViewModel)
        }

        composable(
            route = Pages.Qr_Scanning.route,
        ) {
            PageQrScanning(
                viewModel = hiltViewModel(),
                navController = navController
            )
        }
    }
}
