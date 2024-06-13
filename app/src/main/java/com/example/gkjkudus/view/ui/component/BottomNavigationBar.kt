package com.example.gkjkudus.view.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gkjkudus.PAGE_ROUTE
import com.example.gkjkudus.PAGE_STATE
import com.example.gkjkudus.R
import com.example.gkjkudus.data.ItemViewModel
import com.example.gkjkudus.navigation.Pages
import com.example.gkjkudus.view.ui.page.PageItems
import com.example.gkjkudus.view.ui.page.PagePayment
import com.example.gkjkudus.view.ui.page.PageTransaction

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNavigationBar(navController1: NavController, itemViewModel: ItemViewModel) {

    var navigationSelectedItem by remember { mutableStateOf(PAGE_STATE) }

    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Unduh-Unduh",
                        fontSize = MaterialTheme.typography.titleLarge.fontSize,
                        fontWeight = FontWeight.SemiBold
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.surfaceContainer),
                actions = {
                    Surface(modifier = Modifier
                        .size(42.dp), color = Color.Transparent, shape = RoundedCornerShape(50)
                    ) {
                        Box(modifier = Modifier.fillMaxSize().clickable {
                            itemViewModel.fetchItems()
                        }, contentAlignment = Alignment.Center) {
                            Icon(painter = painterResource(id = R.drawable.twotone_refresh_24), "Refresh")
                        }
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                }
            )
        },
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