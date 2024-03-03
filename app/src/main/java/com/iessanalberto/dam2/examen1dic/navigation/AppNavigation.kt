package com.iessanalberto.dam2.examen1dic.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.iessanalberto.dam2.examen1dic.screens.BorrarScreen
import com.iessanalberto.dam2.examen1dic.screens.MainScreen
import com.iessanalberto.dam2.examen1dic.screens.NewEventScreen
import com.iessanalberto.dam2.examen1dic.viewmodels.ViewModelEvento

@Composable
fun AppNavigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.MainScreen.route ){
        composable(route = AppScreens.MainScreen.route) { MainScreen(navController)}
        composable(route = AppScreens.NewEventScreen.route) { NewEventScreen(navController, mvvm = ViewModelEvento())}
        composable(route = AppScreens.BorrarScreen.route) { BorrarScreen(navController = navController, mvvm = ViewModelEvento())}
    }
}