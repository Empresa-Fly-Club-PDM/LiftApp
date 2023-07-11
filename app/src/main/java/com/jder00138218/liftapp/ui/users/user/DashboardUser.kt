package com.jder00138218.liftapp.ui.users.user
import android.content.Context
import android.graphics.drawable.Drawable
import androidx.compose.foundation.Image
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

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.85f)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {

                Text(text = stringResource(R.string.bienvenido), color = colorResource(id = R.color.gray_text))
                Text(
                    text = detailUser.nombrecompleto,
                    color = Color.Black,
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp
                    )
                )

                BestInfoUser(detailLift = detailHighligt)
                Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically) {
                    Column(modifier = Modifier.fillMaxWidth(0.5f),
                        horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        painter = painterResource(id = gentRankDrawable(detailUser.points)),
                        contentDescription = stringResource(R.string.image_level),
                        modifier = Modifier
                            .width(250.dp)
                            .height(180.dp)
                    )
                }
                    Column(modifier = Modifier.fillMaxWidth(0.5f),
                    horizontalAlignment = Alignment.CenterHorizontally) {
                        dataItem(stringResource(R.string.altura), detailUser.height.toString() + stringResource(R.string.cm_spaced))
                        dataItem(stringResource(R.string.peso), detailUser.weight.toString() + stringResource(R.string.lb_spaced))
                        dataItem(
                            stringResource(R.string.edad),
                            calculateAge(detailUser.fechanac).toString() + stringResource(R.string.a_os)
                        )
                        dataItem(stringResource(R.string.puntaje), detailUser.points.toString())
                        dataItem(stringResource(R.string.nivel), getRank(detailUser.points, context))
                    }

                }


                RankingFriends(navController)
                RoutineMenuCard(stringResource(R.string.rutinas), navController)
            }

            UserBottomMenu(navController)
        }

    }


}

@Composable
fun RoutineMenuCard(muscleGroup: String, navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .height(72.dp)
    )
    {
        Box(modifier = Modifier.background(colorResource(id = R.color.card))) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = muscleGroup, style = TextStyle(fontSize = 20.sp, color = Color.Black))
                Button(
                    modifier = Modifier,
                    onClick = { navController.navigate(route = Rutas.UserRoutineMenu.ruta) },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(
                            id = R.color.buttonRed
                        ), contentColor = Color.White
                    )
                ) {
                    Icon(
                        Icons.Default.ArrowForward,
                        contentDescription = "to $muscleGroup routines"
                    )
                }
            }
        }

    }
}

@Composable
fun MainInfoUser(detailUser: user, detailLift: lift?, navController: NavController) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BestInfoUser(detailLift)
        InfoUser(detailUser)
    }
}

@Composable
fun RankingFriends(navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.bcCard),
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Rankings", color = Color.White)
            Button(
                onClick = { navController.navigate(route = Rutas.UserRanking.ruta) },
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White
                ),
                modifier = Modifier.padding(8.dp)
            ) {
                Text(text = stringResource(R.string.rankings), color = Color.Red)
            }
        }
    }
}

@Composable
fun ItemFriend(name: String) {
    Text(
        text = name,
        color = Color.White,
        fontWeight = FontWeight.Bold, // Aplica el estilo de texto en negrita
        modifier = Modifier.padding(4.dp)
    )
}


@Composable
fun InfoUser(detailUser: user) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

        }
        Spacer(modifier = Modifier.padding(4.dp))
        Row(modifier = Modifier.padding(8.dp)) {
            Image(
                painter = painterResource(id = gentRankDrawable(detailUser.points)),
                contentDescription = stringResource(R.string.image_level),
                modifier = Modifier
                    .width(250.dp)
                    .height(180.dp)
            )
            Column(verticalArrangement = Arrangement.SpaceBetween, modifier = Modifier.weight(1f)) {

            }

        }
    }


}

@Composable
fun dataItem(type: String, data: String, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        )
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxSize()
        ) {
            Text(
                text = data,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                color = Color.Red
            )
            Text(
                text = type,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                color = colorResource(id = R.color.gray_text)
            )
        }
    }
}


@Composable
fun BestInfoUser(detailLift: lift?) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .height(72.dp)
    )
    {
        Box(modifier = Modifier.background(colorResource(id = R.color.card))) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,

                ) {
                Text(
                    text = stringResource(R.string.mejor_levantantamiento),
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = Color.Black
                    )
                )
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "${detailLift?.weight} LB", color = Color.Red)
                    Text(
                        text = handleHighlightName(detailLift?.exercisename),
                        color = Color.DarkGray
                    )
                }

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