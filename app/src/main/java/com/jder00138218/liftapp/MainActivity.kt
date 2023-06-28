package com.jder00138218.liftapp
import UserPersonalExerciseDetails
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.jder00138218.liftapp.ui.users.admin.DashboardAdminScreen
import com.jder00138218.liftapp.ui.login.LoginScreen
import com.jder00138218.liftapp.ui.navigation.Rutas
import com.jder00138218.liftapp.ui.recovery.forgotPasword.Recovery
import com.jder00138218.liftapp.ui.register.RegisterScreen
import com.jder00138218.liftapp.ui.theme.LiftAppTheme
import com.jder00138218.liftapp.ui.users.admin.exerciseManager.ManageExerciseRequests.DetaileExercise
import com.jder00138218.liftapp.ui.users.admin.adminProfile.AdminProfile
import com.jder00138218.liftapp.ui.users.admin.exerciseManager.CreateExercise.CreateExercise
import com.jder00138218.liftapp.ui.users.admin.exerciseManager.VerifiedExerciseView.VerifyExercises
import com.jder00138218.liftapp.ui.users.admin.exerciseManager.updateexercise.AdminUpdateExercise
import com.jder00138218.liftapp.ui.users.admin.userManager.AdminManagement.AdminManager
import com.jder00138218.liftapp.ui.users.admin.userManager.CreateAdmin.CreateAdmin
import com.jder00138218.liftapp.ui.users.admin.userManager.UpdateAdmin.UpdateAdmin
import com.jder00138218.liftapp.ui.users.user.DashboardUserScreen
import com.jder00138218.liftapp.ui.users.user.addexercisetoroutine.AddExerciseToRoutine
import com.jder00138218.liftapp.ui.users.user.createroutine.CreateRoutine
import com.jder00138218.liftapp.ui.users.user.ranking.GlobalRankingUsers
import com.jder00138218.liftapp.ui.users.user.routine.Routine
import com.jder00138218.liftapp.ui.users.user.routinesmenu.RoutinesMenu
import com.jder00138218.liftapp.ui.users.user.userprofile.UserProfile

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LiftAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                   NavigationGraph()
                 }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun NavigationGraph(){
    val navController: NavHostController = rememberNavController()
    NavHost(navController = navController, startDestination =  Rutas.Login.ruta){
        composable(route = Rutas.Login.ruta){
            LoginScreen(navController)
        }
        composable(route = Rutas.Register.ruta){
            RegisterScreen(navController)
        }
        composable(route = Rutas.ForgotPss.ruta){
            Recovery(navController)
        }
        composable(route = Rutas.DashboardAdmin.ruta,){
            DashboardAdminScreen(navController)
        }
        composable(route = Rutas.DashboardUser.ruta){
            DashboardUserScreen(navController)
        }
        composable(route = Rutas.AdminDetailExercise.ruta,
            arguments = listOf(navArgument("id"){
                type = NavType.IntType
            })
            ){
            DetaileExercise(navController)
        }
        composable(route = Rutas.AdminVerifyExercise.ruta){
            VerifyExercises(navController)
        }
        composable(route = Rutas.AdminCreateExercise.ruta){
            CreateExercise(navController)
        }
        composable(route = Rutas.AdminProfile.ruta){
            AdminProfile(navController)
        }
        composable(route = Rutas.AdminUpdateExercise.ruta,
            arguments= listOf(navArgument("id"){
                type = NavType.IntType
            })
            ){
            AdminUpdateExercise(navController)
        }
        composable(route = Rutas.AdminAdminManager.ruta){
            AdminManager(navController)
        }
        composable(route = Rutas.AdminCreateAdmin.ruta){
            CreateAdmin(navController)
        }
        composable(route = Rutas.UpdateAdmin.ruta,
            arguments= listOf(navArgument("id"){
                type = NavType.IntType
            })
        ){
            UpdateAdmin(navController)
        }

        composable(route = Rutas.UserRoutineMenu.ruta){
            RoutinesMenu(navController)
        }
        composable(route = Rutas.UserRoutine.ruta){
            Routine(navController)
        }
        composable(route = Rutas.UserRanking.ruta){
            GlobalRankingUsers(navController)
        }
        composable(route = Rutas.UserCreateRoutine.ruta){
            CreateRoutine(navController)
        }
        composable(route = Rutas.UserAddExercise.ruta){
            AddExerciseToRoutine(navController)
        }
        composable(route = Rutas.UserExerciseDetail.ruta){
            UserPersonalExerciseDetails(navController)
        }
        composable(route = Rutas.UserProfile.ruta){
            UserProfile(navController)
        }
    }
}
