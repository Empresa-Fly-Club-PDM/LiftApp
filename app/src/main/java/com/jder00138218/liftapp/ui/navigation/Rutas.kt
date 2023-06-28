package com.jder00138218.liftapp.ui.navigation

import com.jder00138218.liftapp.network.dto.exercise.exercise

sealed  class Rutas(var ruta : String) {
    object Login:Rutas("ruta_login")
    object Register:Rutas("ruta_register")
    object ForgotPss:Rutas("ruta_forgotpss")
    object DashboardAdmin:Rutas("ruta_dash_admin")
    object DashboardUser:Rutas("ruta_dash_user")
    object AdminDetailExercise:Rutas("ruta_admin_exercise_details/{id}")
    object AdminVerifyExercise:Rutas("ruta_admin_verify_exercise")
    object AdminCreateExercise:Rutas("ruta_admin_create_exercise")
    object AdminDescriptionRequest:Rutas("ruta_admin_description_request")
    object AdminProfile:Rutas("ruta_admin_profile")
    object AdminUpdateExercise:Rutas("ruta_admin_update_exercise")
    object AdminAdminManager:Rutas("ruta_admin_admin_manager")
    object AdminCreateAdmin:Rutas("ruta_crear_admin")

}