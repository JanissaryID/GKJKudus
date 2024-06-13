package com.example.gkjkudus.view.ui.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.gkjkudus.navigation.Pages

data class BottomNavItem(
    val label : String = "",
    val icon : ImageVector = Icons.Filled.Home,
    val route : String = ""
) {
    //function to get the list of bottomNavigationItems
    fun bottomNavigationItems() : List<BottomNavItem> {
        return listOf(
            BottomNavItem(
                label = "Items",
                icon = Icons.Filled.List,
                route = Pages.Home.route
            ),
            BottomNavItem(
                label = "Transaction",
                icon = Icons.Filled.AddCircle,
                route = Pages.Transaction.route
            ),
            BottomNavItem(
                label = "Payment",
                icon = Icons.Filled.ShoppingCart,
                route = Pages.Profile.route
            ),
        )
    }
}