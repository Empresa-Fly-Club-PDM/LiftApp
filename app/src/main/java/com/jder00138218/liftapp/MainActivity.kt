package com.jder00138218.liftapp

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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jder00138218.liftapp.ui.users.admin.DashboardAdminScreen
import com.jder00138218.liftapp.ui.login.LoginScreen
import com.jder00138218.liftapp.ui.navigation.Rutas
import com.jder00138218.liftapp.ui.recovery.forgotPasword.Recovery
import com.jder00138218.liftapp.ui.register.RegisterScreen
import com.jder00138218.liftapp.ui.theme.LiftAppTheme
import com.jder00138218.liftapp.ui.users.user.DashboardUserScreen

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
        composable(route = Rutas.DashboardAdmin.ruta){
            DashboardAdminScreen()
        }
        composable(route = Rutas.DashboardUser.ruta){
            DashboardUserScreen()
        }
    }
}
