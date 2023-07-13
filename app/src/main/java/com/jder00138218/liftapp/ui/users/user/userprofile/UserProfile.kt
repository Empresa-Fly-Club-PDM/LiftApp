package com.jder00138218.liftapp.ui.users.user.userprofile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.jder00138218.liftapp.LiftAppApplication
import com.jder00138218.liftapp.R
import com.jder00138218.liftapp.network.dto.user.user
import com.jder00138218.liftapp.ui.navigation.Rutas
import com.jder00138218.liftapp.ui.users.admin.AdminHeaderBarBackArrowDumbell
import com.jder00138218.liftapp.ui.users.admin.AdminProfileInfoRow
import com.jder00138218.liftapp.ui.users.admin.Menu
import com.jder00138218.liftapp.ui.users.admin.adminProfile.viewmodel.AdminProfileViewModel
import com.jder00138218.liftapp.ui.users.user.FriendNameCard
import com.jder00138218.liftapp.ui.users.user.HeaderBarBackArrowDumbell
import com.jder00138218.liftapp.ui.users.user.UserBottomMenu
import com.jder00138218.liftapp.ui.users.user.UserProfileInfoRow
import com.jder00138218.liftapp.ui.users.user.userprofile.viewmodel.UserProfileViewModel

@Composable
fun UserProfile(navController: NavController){
    val userProfileViewModel: UserProfileViewModel = viewModel(
        factory = UserProfileViewModel.Factory
    )
    val context = LocalContext.current
    val app = context.applicationContext as LiftAppApplication
    userProfileViewModel.getUserDetails(app.getUserId())
    val detailUser = userProfileViewModel.user
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
    ){

        Column(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            HeaderBarBackArrowDumbell(title = stringResource(R.string.perfil), navController, backOnClick = {navController.navigate(Rutas.DashboardUser.ruta)})
            Column(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.90f)
                .verticalScroll(rememberScrollState())
                .padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center){
                FriendNameCard(userName = detailUser.nombrecompleto)
                Spacer(modifier = Modifier.padding(8.dp))
                UserAccountCard(navController, detailUser, app)
                }
            UserBottomMenu(navController)
        }
    }
}

@Composable
fun UserAccountCard(navController: NavController, detailUser: user, app: LiftAppApplication) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        )
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxHeight()
                .fillMaxWidth()
        ) {
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = {navController.navigate(Rutas.UpdateUser.ruta)},
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    ,
                elevation = ButtonDefaults.buttonElevation(2.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(stringResource(R.string.informacion_personal),  color = Color.Black)
                    Icon(
                        imageVector = Icons.Default.ArrowForward,
                        contentDescription = null,
                        modifier = Modifier.padding(start = 8.dp),
                        tint = Color.Black
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = {navController.navigate(Rutas.UserHistory.ruta)},
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White),
                elevation = ButtonDefaults.buttonElevation(2.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(stringResource(R.string.historial),  color = Color.Black)
                    Icon(
                        imageVector = Icons.Default.ArrowForward,
                        contentDescription = null,
                        modifier = Modifier.padding(start = 8.dp),
                        tint = Color.Black
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = {navController.navigate(route = Rutas.FriendsMenu.ruta)},
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White),
                elevation = ButtonDefaults.buttonElevation(2.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(stringResource(R.string.amigos),  color = Color.Black)
                    Icon(
                        imageVector = Icons.Default.ArrowForward,
                        contentDescription = null,
                        modifier = Modifier.padding(start = 8.dp),
                        tint = Color.Black
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = { app.saveAuthToken("user_token")
                    app.sessionManager.clearSession()
                    navController.navigate(Rutas.Login.ruta)
                },
                modifier = Modifier,
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(
                    id = R.color.buttonRed)
                ),
                elevation = ButtonDefaults.buttonElevation(2.dp)
            ) {
                Row(modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = stringResource(id = R.string.cerrar_sesi_n))
                    Icon(
                        imageVector = Icons.Default.ArrowForward,
                        contentDescription = stringResource(R.string.logout),
                        tint = Color.White
                    )
                }
            }
        }
    }
}
