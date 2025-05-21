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

    Scaffold { paddingValues ->
        Contenido(
            modifier = Modifier.padding(paddingValues),
            navController = navController,
            servicio = servicio
        )
    }
}

// Contenido con NavHost, rutas etc. (lo defines luego o lo actualizas con tus composables)
@Composable
fun Contenido(
    modifier: Modifier = Modifier,
    navController: androidx.navigation.NavHostController,
    servicio: ProductApiService
) {
    // Aquí va el NavHost con rutas a tu ScreenProducts y ScreenProductDetail
}
