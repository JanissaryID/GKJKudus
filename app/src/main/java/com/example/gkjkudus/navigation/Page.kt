package com.example.gkjkudus.navigation


sealed class Pages(val route: String) {
    object Home : Pages(route = "home_page")
    object Transaction : Pages("search_route")
    object Profile : Pages("profile_route")
    object Qr_Scanning: Pages(route = "qr_scaning_route")
}
