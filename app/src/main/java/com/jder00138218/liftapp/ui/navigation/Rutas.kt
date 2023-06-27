package com.jder00138218.liftapp.ui.navigation

import com.jder00138218.liftapp.network.dto.exercise.exercise

sealed  class Rutas(var ruta : String) {
    object Login:Rutas("ruta_login")
    object Register:Rutas("ruta_register")
    object ForgotPss:Rutas("ruta_forgotpss")
    object DashboardAdmin:Rutas("ruta_dash_admin")
    object DashboardUser:Rutas("ruta_dash_user")
    object AdminDetailExercise:Rutas("ruta_admin_exercise_details/{id}")

}