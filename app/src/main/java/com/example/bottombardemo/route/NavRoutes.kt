package com.example.bottombardemo.route

sealed class NavRoutes(val route: String) {
    object Home: NavRoutes("home")

    object Contacts: NavRoutes("contacts")

    object Favorites: NavRoutes("favorites")
}
