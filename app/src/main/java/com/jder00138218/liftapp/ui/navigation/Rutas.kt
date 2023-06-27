package com.jder00138218.liftapp.ui.navigation

sealed  class Rutas(var ruta : String) {
    object Login:Rutas("ruta_login")
    object Register:Rutas("ruta_register")
    object ForgotPss:Rutas("ruta_forgotpss")
    object DashboardAdmin:Rutas("ruta_dash_admin")
    object DashboardUser:Rutas("ruta_dash_user")
    object AdminDetailExercise:Rutas("ruta_admin_exercise_details")
    object AdminVerifyExercise:Rutas("ruta_admin_verify_exercise")
    object AdminCreateExercise:Rutas("ruta_admin_create_exercise")
    object AdminDescriptionRequest:Rutas("ruta_admin_description_request")
    object AdminProfile:Rutas("ruta_admin_profile")

}