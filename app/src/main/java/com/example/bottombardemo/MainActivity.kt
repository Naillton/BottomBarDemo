package com.example.bottombardemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.bottombardemo.bottombar.NavBarItems
import com.example.bottombardemo.route.NavRoutes
import com.example.bottombardemo.screens.ContactScreen
import com.example.bottombardemo.screens.FavoriteScreen
import com.example.bottombardemo.screens.HomeScreen
import com.example.bottombardemo.ui.theme.BottomBarDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BottomBarDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

/**
 * Criando navigation host e navigation controller
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    // usando Scaffold que nos fornece um layout com slots de elementos como top bar, content area e bottom bar
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Bottom Navigaiton Demo")
            })},
        content = {it ->
            Column(Modifier.padding(it)) {
                NavigationHost(navController = navController)
            }
        },
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    )
}

@Composable
fun NavigationHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = NavRoutes.Home.route
    ) {
        composable(NavRoutes.Home.route) {
            HomeScreen().Home()
        }

        composable(NavRoutes.Contacts.route) {
            ContactScreen().Contact()
        }

        composable(NavRoutes.Favorites.route) {
            FavoriteScreen().Favorite()
        }
    }
}

/**
 * Criando bottom navigation bar que contera a navegacao em barra,
 * e nela criaremos um backStackAsState para identificarmos em qual tela
 * estamos no momento.
 */
@Composable
fun BottomNavigationBar(navController: NavHostController) {
    NavigationBar {
        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = backStackEntry?.destination?.route

        NavBarItems.barItems.forEach {
            NavigationBarItem(
                // comparando rota atual com a rota do barItem retornando true ou false
                selected = currentRoute == it.route,
                // ao clicar no botao navegacao ate a rota atual e usamos o popUpTo
                // para encontrar a rota de navegacao e definindo o save, restore e launchingSingleTop como true
                onClick = {
                    navController.navigate(it.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(imageVector = it.image, contentDescription = it.title)
                },
                label = {
                    Text(text = it.title)
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BottomBarDemoTheme {
        MainScreen()
    }
}