package com.jder00138218.liftapp.ui.navigation

sealed  class Rutas(var ruta : String) {
    object Login:Rutas("ruta_login")
    object Register:Rutas("ruta_register")
    object ForgotPss:Rutas("ruta_forgotpss")
}