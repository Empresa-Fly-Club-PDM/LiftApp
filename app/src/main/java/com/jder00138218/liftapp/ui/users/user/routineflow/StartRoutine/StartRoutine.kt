package com.jder00138218.liftapp.ui.users.user.routineflow.StartRoutine

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.jder00138218.liftapp.R
import com.jder00138218.liftapp.network.dto.exercise.exercise
import com.jder00138218.liftapp.ui.users.user.ExerciseCardUser
import com.jder00138218.liftapp.ui.users.user.ExerciseCardUserNoClick
import com.jder00138218.liftapp.ui.users.user.HeaderBarBackArrowAdd
import com.jder00138218.liftapp.ui.users.user.HeaderBarBackArrowDumbell
import com.jder00138218.liftapp.ui.users.user.UserBottomMenu
import com.jder00138218.liftapp.ui.users.user.routinedetail.viewmodel.RoutineDetailViewModel

@Composable
fun StartRoutine(navController:NavController){
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

            HeaderBarBackArrowDumbell(stringResource(R.string.empezar_rutina), navController, backOnClick = { navController.popBackStack() })


            LazyColumn(
                Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.6f)
            ) {
                items(vm.exercises) { index ->
                    ExerciseCardUserNoClick(index, navController)
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
            ButtonDeleteRoutine(routineid,viewmodel = vm, navController = navController )
            UserBottomMenu(navController)

        }
    }
}

@Composable
fun ButtonDeleteRoutine(id:Int?, viewmodel: RoutineDetailViewModel, navController: NavController) { val context = LocalContext.current
    Button(
        onClick = {navController.navigate("rutas_courrent_routine/${id}")
        }, modifier = Modifier
            .height(60.dp)
            .fillMaxWidth(), colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(id = R.color.buttonGren)
        )
    ) {
        Text(text = stringResource(R.string.iniciar_rutina))

    }

}


@Composable
fun CardExercise(exercise: exercise) {
    Card( // this
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
            },
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
                    Text(
                        text = exercise.name,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.weight(1f)
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.pesa),
                        contentDescription = stringResource(R.string.verify_icon),
                        modifier = Modifier.size(20.dp)
                    )
                }


                Row(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(1f),
                    horizontalArrangement = Arrangement.Start
                ) {
                    ItemEx(exercise)
                    ItemExRight(exercise)
                }

            }
        }


    }
}


@Composable
fun ItemEx(exercise: exercise) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ), modifier = Modifier
            .padding(4.dp)
            .size(width = 160.dp, height = 60.dp)
    ) {
        Column(Modifier.padding(8.dp)) {
            Text(text = stringResource(R.string.musculo), color = Color.Red)
            Text(text = exercise.muscle, color = Color(R.color.gray_text))
        }
    }

}

@Composable
fun ItemExRight(exercise: exercise) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ), modifier = Modifier
            .padding(4.dp)
            .size(width = 160.dp, height = 60.dp)
    ) {
        Column(Modifier.padding(8.dp)) {
            Text(text = exercise.type, color = Color.Red)
            Text(text = "${exercise.reps}x${exercise.sets}", color = Color(R.color.gray_text))
        }
    }

}