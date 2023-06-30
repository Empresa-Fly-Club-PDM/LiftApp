package com.jder00138218.liftapp.ui.navigation

import com.jder00138218.liftapp.network.dto.exercise.exercise

sealed  class Rutas(var ruta : String) {

    //auth
    object Login:Rutas("ruta_login")
    object Register:Rutas("ruta_register")
    object ForgotPss:Rutas("ruta_forgotpss")

    //admin
    object DashboardAdmin:Rutas("ruta_dash_admin")
    object DashboardUser:Rutas("ruta_dash_user")
    object AdminDetailExercise:Rutas("ruta_admin_exercise_details/{id}")
    object AdminVerifyExercise:Rutas("ruta_admin_verify_exercise")
    object AdminCreateExercise:Rutas("ruta_admin_create_exercise")
    object AdminDescriptionRequest:Rutas("ruta_admin_description_request")
    object AdminProfile:Rutas("ruta_admin_profile")
    object AdminAdminManager:Rutas("ruta_admin_admin_manager")
    object AdminCreateAdmin:Rutas("ruta_crear_admin")
    object AdminUpdateExercise:Rutas("ruta_admin_update_exercise/{id}")
    object UpdateAdmin:Rutas("ruta_admin_update_admin/{id}")

    //User
    object UserRoutineMenu:Rutas("ruta_user_routine_menu")
    object UserCreateRoutine:Rutas("ruta_user_create_routine")
    object UserRoutineDetail:Rutas("ruta_user_routine_detail/{id}")
    object UserAddExerciseToRoutine:Rutas("ruta_user_add_exercise_to_routine/{routineid}")
    object UserExerciseDetail:Rutas("rutas_user_exercise_detail")
    object UserRanking:Rutas("rutas_user_ranking")
    object UserProfile:Rutas("rutas_user_profile")
    object RoutineExerciseDetail:Rutas("routine_exercise_detail/{id}/{routineid}")
    object UserExercises:Rutas("rutas_user_exercises")
    object FriendsMenu:Rutas("ruta_user_friends")
    object UserAddExercises:Rutas("rutas_user_add_exercise")
    object UserUpdateExercises:Rutas("rutas_user_update_exercise/{id}")



}