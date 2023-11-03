package com.example.bottombardemo.bottombar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home

/**
 * Criando objeto NavBarItems, que contera uma lista com as navegacoes de barra
 */

object NavBarItems {
    val barItems = listOf<BarItem>(
        BarItem(
            title = "Home",
            image = Icons.Filled.Home,
            route = "home"
        ),

        BarItem(
            title = "Contacts",
            image = Icons.Filled.Face,
            route = "contacts"
        ),

        BarItem(
            title = "Favorites",
            image = Icons.Filled.Favorite,
            route = "favorites"
        )
    )
}