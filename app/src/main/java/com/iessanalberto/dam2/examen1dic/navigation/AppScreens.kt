package com.iessanalberto.dam2.examen1dic.navigation

sealed class AppScreens(val route: String){
    object MainScreen: AppScreens (route = "main_screen")
    object NewEventScreen: AppScreens(route = "new_event_screen")
    object BorrarScreen: AppScreens(route = "borrar_screen")
}
