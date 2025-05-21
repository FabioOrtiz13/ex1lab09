package com.tecsup.ex1lab09

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.tecsup.ex1lab09.ui.theme.Ex1lab09Theme
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.navigation.NavHostController

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Crear instancia Retrofit y servicio API
        val retrofit = Retrofit.Builder()
            .baseUrl("https://dummyjson.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val servicio = retrofit.create(ProductApiService::class.java)

        setContent {
            Ex1lab09Theme {
                ProgPrincipal(servicio)
            }
        }
    }
}

@Composable
fun ProgPrincipal(servicio: ProductApiService) {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BarraInferior(navController) }
    ) { paddingValues ->
        Contenido(
            modifier = Modifier.padding(paddingValues),
            navController = navController,
            servicio = servicio
        )
    }
}

@Composable
fun Contenido(
    modifier: Modifier = Modifier,
    navController: androidx.navigation.NavHostController,
    servicio: ProductApiService
) {
    NavHost(
        navController = navController,
        startDestination = "productList",
        modifier = modifier
    ) {
        composable("productList") {
            ScreenProducts(navController = navController, servicio = servicio)
        }
        composable(
            route = "productDetail/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id") ?: 0
            ScreenProductDetail(navController = navController, servicio = servicio, id = id)
        }
    }
}

@Composable
fun BarraInferior(navController: NavHostController) {
    NavigationBar {
        NavigationBarItem(
            icon = { Icon(Icons.Outlined.ShoppingCart, contentDescription = "Productos") },
            label = { Text("Productos") },
            selected = navController.currentDestination?.route == "productList",
            onClick = {
                navController.navigate("productList") {
                    // Evita múltiples copias en backstack
                    launchSingleTop = true
                    restoreState = true
                }
            }
        )
    }
}

