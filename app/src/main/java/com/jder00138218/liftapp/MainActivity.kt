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
import com.jder00138218.liftapp.ui.users.user.adduserexercises.AddUserExercise
import com.jder00138218.liftapp.ui.users.user.createroutine.CreateRoutine
import com.jder00138218.liftapp.ui.users.user.findfriends.FindFriends
import com.jder00138218.liftapp.ui.users.user.friendprofile.FriendProfile
import com.jder00138218.liftapp.ui.users.user.friends.FriendsMenu
import com.jder00138218.liftapp.ui.users.user.ranking.RankingUsers
import com.jder00138218.liftapp.ui.users.user.routinedetail.RoutineDetail
import com.jder00138218.liftapp.ui.users.user.routineexercisedetail.RoutineExerciseDetail
import com.jder00138218.liftapp.ui.users.user.routineflow.CurrentRoutine.CurrentRoutine
import com.jder00138218.liftapp.ui.users.user.routineflow.RegisterLift.RegisterExerciseStats
import com.jder00138218.liftapp.ui.users.user.routineflow.StartRoutine.StartRoutine
import com.jder00138218.liftapp.ui.users.user.routinesmenu.RoutinesMenu
import com.jder00138218.liftapp.ui.users.user.updateUser.UpdateUser
import com.jder00138218.liftapp.ui.users.user.updateuserexercise.UpdateUserExercise
import com.jder00138218.liftapp.ui.users.user.userexercises.UserExercises
import com.jder00138218.liftapp.ui.users.user.userhistory.UserHistory
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
        composable(route = Rutas.UserRoutineDetail.ruta,
            arguments = listOf(navArgument("id"){
                type = NavType.IntType
            })
            ){
            RoutineDetail(navController)
        }
        composable(route = Rutas.UserRanking.ruta){
            RankingUsers(navController)
        }
        composable(route = Rutas.UserCreateRoutine.ruta){
            CreateRoutine(navController)
        }
        composable(route = Rutas.UserAddExerciseToRoutine.ruta,
        arguments = listOf(navArgument("routineid"){
            type = NavType.IntType
        })
            ){
            AddExerciseToRoutine(navController)
        }
        composable(route = Rutas.UserExerciseDetail.ruta){
            UserPersonalExerciseDetails(navController)
        }
        composable(route = Rutas.UserProfile.ruta){
            UserProfile(navController)
        }
        composable(route = Rutas.RoutineExerciseDetail.ruta,
            arguments = listOf(navArgument("id"){
                type = NavType.IntType
            }, navArgument("routineid"){
                type = NavType.IntType
            })
            ){
            RoutineExerciseDetail(navController, navController)
        }
        composable(route = Rutas.UserExercises.ruta){
            UserExercises(navController)
        }
        composable(route = Rutas.FriendsMenu.ruta){
            FriendsMenu(navController)
        }
        composable(route=Rutas.UserAddExercises.ruta){
            AddUserExercise(navController)
        }
        composable(route=Rutas.UserUpdateExercises.ruta,
            arguments= listOf(navArgument("id"){
                type = NavType.IntType
            })
            ){
            UpdateUserExercise(navController)
        }
        composable(route=Rutas.UpdateUser.ruta){
            UpdateUser(navController)
        }

        composable(route=Rutas.FindFriends.ruta,
            arguments= listOf(navArgument("id"){
                type = NavType.IntType
            })
        ){
            FindFriends(navController)
        }
        composable(route= Rutas.FriendProfile.ruta,
            arguments = listOf(navArgument("id"){
                type = NavType.IntType
            })
            ){
            FriendProfile(navController)
        }
        composable(route=Rutas.StartRoutine.ruta,
            arguments = listOf(navArgument("id"){
                type = NavType.IntType
            })
            ){
            StartRoutine(navController)
        }
        composable(route=Rutas.CurrentRoutine.ruta,
            arguments = listOf(navArgument("id"){
                type = NavType.IntType
            })
        ){
            CurrentRoutine(navController)
        }
        composable(route = Rutas.RegisterLift.ruta,
            arguments = listOf(navArgument("exerciseid"){
                type = NavType.IntType
            }, navArgument("exercisename"){
                type = NavType.StringType
            })
            ){
            RegisterExerciseStats(navController)
        }

        composable(route=Rutas.UserHistory.ruta){
            UserHistory(navController)
        }

    }
}
