package com.jder00138218.liftapp.ui.users.user.friendprofile

import android.content.Context
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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.jder00138218.liftapp.R
import com.jder00138218.liftapp.network.dto.lift.lift
import com.jder00138218.liftapp.network.dto.user.user
import com.jder00138218.liftapp.ui.users.user.BestLiftCard
import com.jder00138218.liftapp.ui.users.user.DataRow2Elements
import com.jder00138218.liftapp.ui.users.user.DataRow3Elements
import com.jder00138218.liftapp.ui.users.user.FriendNameCard
import com.jder00138218.liftapp.ui.users.user.HeaderBarBackArrowDumbell
import com.jder00138218.liftapp.ui.users.user.UserBottomMenu
import com.jder00138218.liftapp.ui.users.user.friendprofile.viewmodel.FriendProfileLiftViewModel
import com.jder00138218.liftapp.ui.users.user.friendprofile.viewmodel.FriendProfileViewModel
import java.time.LocalDate


@Composable
fun FriendProfile(navController: NavController) {
    val navBackStackEntry = navController.currentBackStackEntry
    val friendid = navBackStackEntry?.arguments?.getInt(stringResource(id = R.string.id))
    val friendProfileViewModel: FriendProfileViewModel = viewModel(
        factory = FriendProfileViewModel.Factory
    )
    val friendProfileLiftViewModel: FriendProfileLiftViewModel = viewModel(
        factory = FriendProfileLiftViewModel.Factory
    )
    if(friendid!=0){
        friendProfileViewModel.getUserDetails(friendid)
        friendProfileLiftViewModel.getUserBestLift(friendid)
    }
    val context = LocalContext.current
    val detailUser = friendProfileViewModel.user
    val detailHighligt: lift? = friendProfileLiftViewModel.lift
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {

            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .fillMaxHeight()
                .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween){

                HeaderBarBackArrowDumbell(title = stringResource(R.string.perfil_amigo), navController = navController, backOnClick = {navController.popBackStack()})
                FriendNameCard(userName = detailUser.nombrecompleto)
                Spacer(modifier = Modifier.padding(vertical = 8.dp))
                Image(
                    painter = painterResource(id = com.jder00138218.liftapp.ui.users.user.gentRankDrawable(
                        detailUser.points
                    )
                    ),
                    contentDescription = stringResource(R.string.image_level),
                    modifier = Modifier
                        .width(250.dp)
                        .height(180.dp)
                )
                Spacer(modifier = Modifier.padding(vertical = 8.dp))
                DataRow3Elements(detailUser = detailUser)
                Spacer(modifier = Modifier.padding(vertical = 8.dp))
                DataRow2Elements(detailUser = detailUser, context = context)
                Spacer(modifier = Modifier.padding(vertical = 8.dp))
                BestLiftCard(bestLift = detailHighligt, navController = navController)
                Spacer(modifier = Modifier.padding(vertical = 8.dp))
                UserBottomMenu(navController)
            }



    }


}




@Composable
fun MainInfoUser(detailUser: user, detailLift: lift?, navController: NavController, context:Context) {
    Column() {
        BestInfoUser(detailLift)
        InfoUser(detailUser,context)
    }
}


@Composable
fun InfoUser(detailUser: user, context: Context) {
    Column() {
        Row() {
            dataItem(stringResource(R.string.altura), detailUser.height.toString()+ stringResource(R.string.cm_spaced))
            dataItem(stringResource(R.string.peso), detailUser.weight.toString()+ stringResource(R.string.lb_spaced))
            dataItem(stringResource(R.string.edad), calculateAge(detailUser.fechanac).toString() + stringResource(
                            R.string.a_os)
                        )
        }
        Spacer(modifier = Modifier.padding(4.dp))
        Row() {
            Image(
                painter = painterResource(id = gentRankDrawable(detailUser.points)),
                contentDescription = stringResource(R.string.image_level),
                modifier = Modifier
                    .width(250.dp)
                    .height(180.dp)
            )
            Column() {
                dataItem(stringResource(R.string.puntaje), detailUser.points.toString())
                Spacer(modifier = Modifier.padding(2.dp))
                dataItem(stringResource(R.string.nivel), getRank(detailUser.points,context))
            }

        }
    }


}

@Composable
fun dataItem(type: String, data: String) {
    Card(
        modifier = Modifier
            .width(120.dp)
            .height(80.dp),
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
    Spacer(modifier = Modifier.padding(2.dp))
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
                    Text(text = handleHighlightName(detailLift?.exercisename), color = Color.DarkGray)
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

fun gentRankDrawable(user_points:Int):Int{
    var logo = 0
    if(user_points<4000){
        logo = R.drawable.novato
    }else if(user_points>=4000 && user_points<10000){
        logo = R.drawable.basico1
    }else if(user_points>=10000 && user_points<18000){
        logo = R.drawable.basico2
    }else if(user_points>=18000 && user_points<26000){
        logo = R.drawable.basico3
    }else if(user_points>=26000 && user_points<36000){
        logo = R.drawable.intermedio1
    }else if(user_points>=36000 && user_points<48000){
        logo = R.drawable.intermedio2
    }else if(user_points>=48000 && user_points<64000){
        logo = R.drawable.intermedio3
    }else if(user_points>=64000 && user_points<88000){
        logo = R.drawable.elite1
    }else if(user_points>=88000 && user_points<120000){
        logo = R.drawable.elite2
    }else if(user_points>=120000){
        logo = R.drawable.elite3
    }
    return logo
}

fun handleHighlightName(name:String?):String{
    var auxname = name
    if(auxname == null){
        auxname =""
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