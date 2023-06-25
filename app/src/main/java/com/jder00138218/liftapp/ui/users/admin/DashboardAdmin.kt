package com.jder00138218.liftapp.ui.users.admin

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jder00138218.liftapp.R
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.platform.LocalContext
import com.jder00138218.liftapp.RetrofitApplication
import com.jder00138218.liftapp.network.dto.exercise.exercise
import com.jder00138218.liftapp.ui.users.admin.DasboardAdminViewmodel.DashboardAdminViewmodel

@Preview(name = "Administrator Dashboard", showBackground = true)
@Composable
fun DashboardAdminScreen() {
    val vm = DashboardAdminViewmodel()


    LaunchedEffect(Unit, block = {
        vm.getSolicitudes("")
    })
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {

        Box(modifier = Modifier.fillMaxSize()) {

            Column(
                Modifier
                    .align(Alignment.TopCenter)
                    .fillMaxHeight(0.04f)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Solicitudes de verificacion",
                    color = Color.Black,
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp
                    )
                )
            }



            LazyColumn(
                Modifier
                    .align(Alignment.Center)
                    .fillMaxHeight(0.9f)
                    .fillMaxWidth()
            ) {
                items(vm.exercises) { index ->
                    CardExercise(index)
                }
            }

            Column(
                Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxHeight(0.06f)
                    .fillMaxWidth()
                    .background(Color.White),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Menu()
            }
        }

    }

}


// This view is for dashboard
@Composable
fun CardExercise(currentexc:exercise) {
    Card( // this
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.card)
        )
    ) {

        Box(
            modifier = Modifier
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(modifier = Modifier.fillMaxWidth(1f)) {

                Row() {
                    Text(text = currentexc.user.nombrecompleto, fontWeight = FontWeight.Bold)
                    // Icon
                }


                Row(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(1f),
                    horizontalArrangement = Arrangement.Start
                ) {
                    ItemEx(currentexc.muscle,currentexc.name)
                    ItemExRight(currentexc.type,currentexc.reps,currentexc.sets)
                }

            }
        }


    }
}


@Composable
fun ItemEx(muscle:String,name:String) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ), modifier = Modifier
            .padding(4.dp)
            .size(width = 160.dp, height = 60.dp)
    ) {
        Column(Modifier.padding(8.dp)) {
            Text(text = name, color = Color.Red)
            Text(text = muscle, color = Color(R.color.gray_text))
        }
    }

}

@Composable
fun ItemExRight(type:String,reps:Int,sets:Int) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ), modifier = Modifier
            .padding(4.dp)
            .size(width = 160.dp, height = 60.dp)
    ) {
        Column(Modifier.padding(8.dp)) {
            Text(text = type, color = Color.Red)
            Text(text = "${reps}x${sets}", color = Color(R.color.gray_text))
        }
    }

}
