package com.example.bottombardemo.bottombar

import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Criando data class BarItem e deixando a main activity menos poluida,
 * a classe de dados do bar navigation contem o titulo, o icone e a rota.
 */

data class BarItem(
    val title: String,
    val image: ImageVector,
    val route: String
)
