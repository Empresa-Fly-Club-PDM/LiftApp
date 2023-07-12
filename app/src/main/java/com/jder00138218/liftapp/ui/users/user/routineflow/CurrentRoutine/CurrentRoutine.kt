package com.jder00138218.liftapp.ui.users.user.routineflow.CurrentRoutine

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.jder00138218.liftapp.R
import com.jder00138218.liftapp.network.dto.exercise.exercise
import com.jder00138218.liftapp.ui.navigation.Rutas
import com.jder00138218.liftapp.ui.users.user.ExerciseCardUser
import com.jder00138218.liftapp.ui.users.user.HeaderBarBackArrowDumbell
import com.jder00138218.liftapp.ui.users.user.UserBottomMenu
import com.jder00138218.liftapp.ui.users.user.routinedetail.viewmodel.RoutineDetailViewModel
import kotlinx.coroutines.delay

@Composable
fun CurrentRoutine(navController: NavController){
    val navBackStackEntry = navController.currentBackStackEntry
    val routineid = navBackStackEntry?.arguments?.getInt(stringResource(R.string.id))
    val vm: RoutineDetailViewModel = viewModel(
        factory = RoutineDetailViewModel.Factory
    )
    LaunchedEffect(Unit, block = {
        vm.getRoutineDetails(routineid)
    })



    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
    ){

        Column( modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        )
        {

            Column(modifier = Modifier.fillMaxWidth(),
            ) {
            }
            HeaderBarBackArrowDumbell(stringResource(R.string.rutina), navController, backOnClick = { navController.popBackStack() })

            Timer(routineid,vm)
            LazyColumn(
                Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.7f)
            ) {
                items(vm.exercises) { index ->
                    var url = "rutas_register_lift/${index.id}/${index.name}"
                    ExerciseCardUser(index, url, navController)
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
            ButtonEndRoutine(routineid,viewmodel = vm, navController = navController )
            UserBottomMenu(navController)

        }
    }
}
@Composable
fun ButtonEndRoutine(id:Int?, viewmodel: RoutineDetailViewModel, navController: NavController) { val context = LocalContext.current
    Button(
        onClick = {navController.navigate(Rutas.DashboardUser.ruta)
        }, modifier = Modifier
            .height(60.dp)
            .fillMaxWidth(), colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(id = R.color.buttonRed)
        )
    ) {
        Text(text = stringResource(R.string.finalizar_rutina))

    }

}
private fun formatTime(seconds: Int): String {
    val hours = seconds / 3600
    val minutes = (seconds % 3600) / 60
    val remainingSeconds = seconds % 60
    return "%02d:%02d:%02d".format(hours, minutes, remainingSeconds)
}
@Composable
fun Timer(routineid:Int?, vm: RoutineDetailViewModel) {
    var isRunning by remember { mutableStateOf(true) }
    if(vm._time == 359999){
        vm.getRoutineTime(routineid)
    }else if(vm._time==0){
        isRunning=false
    }

    LaunchedEffect(isRunning) {
        if (isRunning) {
            while (true) {
                delay(1000)
                vm._time -= 1
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = if(isRunning){formatTime(vm._time)}else{
                            stringResource(R.string.tiempo_agotado)
                        },
            modifier = Modifier.padding(bottom = 16.dp),
            fontSize = 32.sp,
            color = if(isRunning){Color.Black}else{Color.Red},
        )
    }
}