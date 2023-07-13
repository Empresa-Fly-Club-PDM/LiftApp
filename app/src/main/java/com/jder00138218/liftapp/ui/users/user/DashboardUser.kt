package com.jder00138218.liftapp.ui.users.user
import android.content.Context
import android.graphics.drawable.Drawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.jder00138218.liftapp.LiftAppApplication
import com.jder00138218.liftapp.R
import com.jder00138218.liftapp.ui.navigation.Rutas
import com.jder00138218.liftapp.network.dto.lift.lift
import com.jder00138218.liftapp.network.dto.user.user
import com.jder00138218.liftapp.ui.users.admin.Menu
import com.jder00138218.liftapp.ui.users.user.routinesmenu.RoutineMenuItem
import com.jder00138218.liftapp.ui.users.user.viewmodel.DashboardUserLiftViewModel
import com.jder00138218.liftapp.ui.users.user.viewmodel.DashboardUserViewModel
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter

@Composable
fun DashboardUserScreen(navController: NavController) {
    val dashboardUserViewModel: DashboardUserViewModel = viewModel(
        factory = DashboardUserViewModel.Factory
    )
    val dashboardUserLiftViewModel: DashboardUserLiftViewModel = viewModel(
        factory = DashboardUserLiftViewModel.Factory
    )
    val context = LocalContext.current
    val app = context.applicationContext as LiftAppApplication
    dashboardUserViewModel.getUserDetails(app.getUserId())
    dashboardUserLiftViewModel.getUserBestLift(app.getUserId())
    val detailUser = dashboardUserViewModel.user
    val detailHighligt: lift? = dashboardUserLiftViewModel.lift
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            if(dashboardUserViewModel._loading.value){
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.9f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(36.dp)
                            .padding(end = 8.dp),
                        color = Color.Red
                    )
                }
            }else{
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.9f)
                        .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Spacer(modifier = Modifier.padding(8.dp))
                    GreetingCard(userName = detailUser.nombrecompleto)
                    Spacer(modifier = Modifier.padding(8.dp))

                    Image(
                        painter = painterResource(id = gentRankDrawable(detailUser.points)),
                        contentDescription = stringResource(R.string.image_level),
                        modifier = Modifier
                            .width(250.dp)
                            .height(180.dp)
                    )
                    Spacer(modifier = Modifier.padding(8.dp))
                    DataRow3Elements(detailUser = detailUser)
                    DataRow2Elements(detailUser = detailUser, context = context)
                    Spacer(modifier = Modifier.padding(8.dp))
                    BestLiftCard(bestLift = detailHighligt, navController)
                    Spacer(modifier = Modifier.padding(8.dp))
                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp),
                        shape = RoundedCornerShape(8.dp),
                        onClick = { navController.navigate(route = Rutas.UserRoutineMenu.ruta) },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorResource(
                                id = R.color.buttonRed
                            ), contentColor = Color.White
                        )
                    ) {
                        Row(modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                            Text(text = stringResource(R.string.rutinas))
                            Spacer(modifier = Modifier.padding(8.dp))
                            Icon(
                                Icons.Default.ArrowForward,
                                contentDescription = "to routines"
                            )
                        }

                    }
                }
            }

            UserBottomMenu(navController)
        }

    }


}

@Composable
fun GreetingCard(userName: String){
    Card(modifier = Modifier
        .fillMaxWidth()
        .height(64.dp)
        , colors = CardDefaults.cardColors(
        containerColor = Color.White
    ), elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        )) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(8.dp)) {
            Column(modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = stringResource(R.string.bienvenido), color = Color.Gray)
                Text(text = userName, style = TextStyle(
                    fontWeight = FontWeight.Bold
                ), color = Color.Black)
            }
        }
    }
}


fun getRank(user_points:Int, context: Context):String{
    var rank:String =""
    if(user_points<4000){
        rank = context.getString(R.string.principiante)
    }else if(user_points>=4000 && user_points<10000){
        rank = context.getString(R.string.novato_1)
    }else if(user_points>=10000 && user_points<18000){
        rank = context.getString(R.string.novato_2)
    }else if(user_points>=18000 && user_points<26000){
        rank = context.getString(R.string.novato_3)
    }else if(user_points>=26000 && user_points<36000){
        rank = context.getString(R.string.intermedio_1)
    }else if(user_points>=36000 && user_points<48000){
        rank = context.getString(R.string.intermedio_2)
    }else if(user_points>=48000 && user_points<64000){
        rank = context.getString(R.string.intermedio_3)
    }else if(user_points>=64000 && user_points<88000){
        rank = context.getString(R.string.lite_1)
    }else if(user_points>=88000 && user_points<120000){
        rank = context.getString(R.string.lite_2)
    }else if(user_points>=120000){
        rank = context.getString(R.string.lite_3)
    }
    return rank
}

fun gentRankDrawable(user_points: Int): Int {
    var logo = 0
    if (user_points < 4000) {
        logo = R.drawable.novato
    } else if (user_points >= 4000 && user_points < 10000) {
        logo = R.drawable.basico1
    } else if (user_points >= 10000 && user_points < 18000) {
        logo = R.drawable.basico2
    } else if (user_points >= 18000 && user_points < 26000) {
        logo = R.drawable.basico3
    } else if (user_points >= 26000 && user_points < 36000) {
        logo = R.drawable.intermedio1
    } else if (user_points >= 36000 && user_points < 48000) {
        logo = R.drawable.intermedio2
    } else if (user_points >= 48000 && user_points < 64000) {
        logo = R.drawable.intermedio3
    } else if (user_points >= 64000 && user_points < 88000) {
        logo = R.drawable.elite1
    } else if (user_points >= 88000 && user_points < 120000) {
        logo = R.drawable.elite2
    } else if (user_points >= 120000) {
        logo = R.drawable.elite3
    }
    return logo
}

fun handleHighlightName(name: String?): String {
    var auxname = name
    if (auxname == null) {
        auxname = ""
    }
    return auxname
}

fun calculateAge(dateString: String): Int? {
    val dateParts = dateString.split("/")
    if (dateParts.size == 3) {
        val birthYear = dateParts[2].toInt()
        val currentYear = LocalDate.now().year
        val age = currentYear - birthYear
        return age
    }
    return null
}