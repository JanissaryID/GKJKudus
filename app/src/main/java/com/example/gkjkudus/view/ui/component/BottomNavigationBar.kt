package com.example.gkjkudus.view.ui.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gkjkudus.PAGE_ROUTE
import com.example.gkjkudus.PAGE_STATE
import com.example.gkjkudus.data.ItemViewModel
import com.example.gkjkudus.navigation.Pages
import com.example.gkjkudus.view.ui.page.PageItems
import com.example.gkjkudus.view.ui.page.PagePayment
import com.example.gkjkudus.view.ui.page.PageTransaction

@Composable
fun BottomNavigationBar(navController1: NavController, itemViewModel: ItemViewModel) {

    var navigationSelectedItem by remember { mutableStateOf(PAGE_STATE) }

    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar {
                //getting the list of bottom navigation items for our data class
                BottomNavItem().bottomNavigationItems().forEachIndexed { index, navigationItem ->

                    //iterating all items with their respective indexes
                    NavigationBarItem(
                        selected = index == navigationSelectedItem,
                        label = {
                            Text(navigationItem.label)
                        },
                        icon = {
                            Icon(
                                navigationItem.icon,
                                contentDescription = navigationItem.label
                            )
                        },
                        onClick = {
                            navigationSelectedItem = index
                            PAGE_STATE = navigationSelectedItem
                            PAGE_ROUTE = navigationItem.route
                            navController.navigate(navigationItem.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = PAGE_ROUTE,
            modifier = Modifier.padding(paddingValues = paddingValues)) {
            composable(Pages.Home.route) {
                PageItems(itemViewModel = itemViewModel)
            }
            composable(Pages.Transaction.route) {
                PageTransaction(itemViewModel = itemViewModel, navController = navController1)
            }
            composable(Pages.Profile.route) {
                PagePayment(itemViewModel = itemViewModel, navController = navController1)
            }
        }
    }
}