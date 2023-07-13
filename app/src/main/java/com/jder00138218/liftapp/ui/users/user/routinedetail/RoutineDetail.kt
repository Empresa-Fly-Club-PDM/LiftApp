package com.jder00138218.liftapp.ui.users.user.routinedetail

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
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.navigation.NavHostController
import com.jder00138218.liftapp.LiftAppApplication
import com.jder00138218.liftapp.R
import com.jder00138218.liftapp.network.dto.exercise.exercise
import com.jder00138218.liftapp.network.dto.routine.routine
import com.jder00138218.liftapp.ui.navigation.Rutas
import com.jder00138218.liftapp.ui.users.admin.ExerciseCardAdmin
import com.jder00138218.liftapp.ui.users.admin.userManager.CreateAdmin.viewmodel.CreateAdminViewModel
import com.jder00138218.liftapp.ui.users.admin.viewmodel.DashboardAdminViewmodel
import com.jder00138218.liftapp.ui.users.user.ExerciseCardUser
import com.jder00138218.liftapp.ui.users.user.HeaderBarBackArrowAdd
import com.jder00138218.liftapp.ui.users.user.UserBottomMenu
import com.jder00138218.liftapp.ui.users.user.routinedetail.viewmodel.RoutineDetailViewModel

@Composable
fun RoutineDetail(navController:NavController){
    val navBackStackEntry = navController.currentBackStackEntry
    val routineid = navBackStackEntry?.arguments?.getInt(stringResource(R.string.id))
    val vm: RoutineDetailViewModel = viewModel(
        factory = RoutineDetailViewModel.Factory
    )
    LaunchedEffect(Unit, block = {
        vm.getRoutineDetails(routineid)
    })

    val handleAddOnClick = {
        navController.navigate(route = "ruta_user_add_exercise_to_routine/${routineid}")
    }
    val handleBackOnClick = {
        navController.navigate(route = Rutas.UserRoutineMenu.ruta)
    }

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

            HeaderBarBackArrowAdd(stringResource(R.string.detalle_de_rutina), navController, addOnClick = handleAddOnClick, backOnClick = handleBackOnClick)


            LazyColumn(
                Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.6f)
            ) {
                items(vm.exercises) { index ->
                    var url = "routine_exercise_detail/${index.id}/${routineid}"
                    ExerciseCardUser(index, url, navController)
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
            ButtonDeleteRoutine(routineid,viewmodel = vm, navController = navController )
            UserBottomMenu(navController)

        }
    }
}

@Composable
fun ButtonDeleteRoutine(id:Int?,viewmodel: RoutineDetailViewModel, navController: NavController) { val context = LocalContext.current
    Button(
        onClick = { viewmodel.removeRouitne(id, navController, context)
                  viewmodel._loading.value = true}, modifier = Modifier
            .height(60.dp)
            .fillMaxWidth(), colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(id = R.color.buttonRed)
        )
    ) {
        if (viewmodel._loading.value) {
            // Show loading animation when isLoading is true
            CircularProgressIndicator(
                modifier = Modifier
                    .size(36.dp)
                    .padding(end = 8.dp),
                color = Color.White
            )
        } else {

            Text(text = stringResource(R.string.remover_rutina))
        }

    }

}