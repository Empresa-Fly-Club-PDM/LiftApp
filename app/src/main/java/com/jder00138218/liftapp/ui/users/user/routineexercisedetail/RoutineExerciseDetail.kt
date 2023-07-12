package com.jder00138218.liftapp.ui.users.user.routineexercisedetail

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.jder00138218.liftapp.R
import com.jder00138218.liftapp.network.dto.exercise.exercise
import com.jder00138218.liftapp.network.dto.routine.routine
import com.jder00138218.liftapp.ui.navigation.Rutas
import com.jder00138218.liftapp.ui.users.admin.Menu
import com.jder00138218.liftapp.ui.users.admin.exerciseManager.ManageExerciseRequests.viewModel.DetailExerciseViewmodel
import com.jder00138218.liftapp.ui.users.user.HeaderBarBackArrowDumbell
import com.jder00138218.liftapp.ui.users.user.UserBottomMenu
import com.jder00138218.liftapp.ui.users.user.routineexercisedetail.viewmodel.RoutineExerciseDetailViewModel

@Composable
fun RoutineExerciseDetail(navController: NavHostController, alternativenavcontroller: NavController) {
    val navBackStackEntry = navController.currentBackStackEntry
    val exerciseid = navBackStackEntry?.arguments?.getInt(stringResource(R.string.id))
    val routineid = navBackStackEntry?.arguments?.getInt(stringResource(R.string.routineid))
    val detailExerciseViewmodel:DetailExerciseViewmodel = viewModel(
        factory = DetailExerciseViewmodel.Factory
    )
    val routineExerciseDetailViewModel:RoutineExerciseDetailViewModel = viewModel(
        factory = RoutineExerciseDetailViewModel.Factory
    )
    if(exerciseid!=0){
        detailExerciseViewmodel.getDetailExercise(exerciseid)
    }
    val detailExercise = detailExerciseViewmodel.exercise
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .background(color = Color.White)
    ) {
        Column(modifier = Modifier
            .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            HeaderBarBackArrowDumbell(title = stringResource(R.string.detalle_del_ejercicio), navController = alternativenavcontroller, backOnClick = {navController.popBackStack()})
            FieldsDetaile(detailExercise,detailExerciseViewmodel,navController)
            ButtonsRoutineExerciseDetaile(detailExercise.id,routineid,routineExerciseDetailViewModel, navController)
            UserBottomMenu(navController)
        }
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FieldsDetaile(exercise: exercise,detailExerciseViewmodel: DetailExerciseViewmodel,navController: NavHostController) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(0.5f)
        .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally) {
        FieldDetaile(exercise.name)
        Spacer(modifier = Modifier.padding(2.dp))
        FieldDetaile(exercise.muscle)
        Spacer(modifier = Modifier.padding(2.dp))
        FieldDetaile(exercise.type)
        Spacer(modifier = Modifier.padding(2.dp))
        FieldDetaile(exercise.difficulty)
        Spacer(modifier = Modifier.padding(2.dp))
        FieldDetaile(exercise.description)
        Spacer(modifier = Modifier.padding(2.dp))
        FieldDetaile(exercise.sets.toString())
        Spacer(modifier = Modifier.padding(2.dp))
        FieldDetaile(exercise.reps.toString())
        Spacer(modifier = Modifier.padding(8.dp))
    }
}

@Composable
fun ButtonsRoutineExerciseDetaile(idexc: Int?,idrut:Int? , routineExerciseDetailViewModel: RoutineExerciseDetailViewModel,navController: NavHostController) {
    val context = LocalContext.current
    Row(modifier = Modifier.fillMaxWidth()) {
        Button(
            onClick = {routineExerciseDetailViewModel.removeExercise(idexc,idrut,navController,context)
            routineExerciseDetailViewModel._loading.value = true}, modifier = Modifier
                .height(60.dp)
                .width(350.dp)
                .fillMaxWidth(), colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.buttonRed)
            )
        ) {

            if (routineExerciseDetailViewModel._loading.value) {
                // Show loading animation when isLoading is true
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(36.dp)
                        .padding(end = 8.dp),
                    color = Color.White
                )
            } else {

                Text(text = stringResource(R.string.remover_de_la_rutina))
            }

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FieldDetaile(name: String) {
    OutlinedTextField(
        value = name,
        readOnly = true,
        onValueChange = { },
        modifier = Modifier
            .width(350.dp)
            .clip(RoundedCornerShape(4.dp))
            .border(
                width = 1.dp,
                color = colorResource(id = R.color.field)
            )// With padding show border color
            .background(colorResource(id = R.color.field)),
        placeholder = { Text(text = name, color = Color(R.color.gray_text)) },
        singleLine = true,
        maxLines = 1,
        leadingIcon = {
            Icon(
                modifier = Modifier.size(16.dp),
                painter = painterResource(id = R.drawable.pesa),
                contentDescription = stringResource(R.string.icon_field)
            )
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next // Acción IME cuando se presiona la tecla Enter
        )
    )
}