package com.jder00138218.liftapp.ui.users.admin.exerciseManager.CreateExercise

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.jder00138218.liftapp.R
import com.jder00138218.liftapp.ui.login.viewmodel.LoginViewModel
import com.jder00138218.liftapp.ui.users.admin.AdminHeaderBarBackArrowDumbell
import com.jder00138218.liftapp.ui.users.admin.Menu
import com.jder00138218.liftapp.ui.users.admin.exerciseManager.CreateExercise.viewmodel.CreateExerciseViewmodel
import com.jder00138218.liftapp.ui.users.admin.exerciseManager.updateexercise.ButtonsUpdate


@Composable
fun CreateExercise(navController: NavHostController) {
    val createExerciseViewmodel: CreateExerciseViewmodel = viewModel(
    factory = CreateExerciseViewmodel.Factory
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Column(modifier = Modifier
            .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AdminHeaderBarBackArrowDumbell(title = stringResource(R.string.crear_ejercicio), navController = navController, backOnClick = {navController.popBackStack()})
            Column(modifier = Modifier
                .fillMaxHeight(0.85f)
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())) {
                FieldsDetaileCreate(createExerciseViewmodel,navController)
                ButtonsCreate(createExerciseViewmodel,navController)
            }

            Menu(navController)
        }

    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FieldsDetaileCreate(viewmodel: CreateExerciseViewmodel,navController:NavHostController) {
    Column(modifier = Modifier,
    horizontalAlignment = Alignment.CenterHorizontally) {
        FieldName(viewmodel)
        Spacer(modifier = Modifier.padding(2.dp))
        FieldMuscle(viewmodel)
        Spacer(modifier = Modifier.padding(2.dp))
        FieldType(viewmodel)
        Spacer(modifier = Modifier.padding(2.dp))
        FieldDifficulty(viewmodel)
        Spacer(modifier = Modifier.padding(2.dp))
        FieldDescription(viewmodel)
        Spacer(modifier = Modifier.padding(2.dp))
        FieldSets(viewmodel)
        Spacer(modifier = Modifier.padding(2.dp))
        FieldReps(viewmodel)
        Spacer(modifier = Modifier.padding(8.dp))
    }

}

@Composable
fun ButtonsCreate(viewmodel: CreateExerciseViewmodel, navController: NavHostController) { val context = LocalContext.current
        Button(
            onClick = {
                viewmodel.isLoadingCreate.value = true
                viewmodel.onCreate(navController, context)
            }, modifier = Modifier
                .height(60.dp)
                .fillMaxWidth(), colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.buttonGren)
            )
        ) {
            if (viewmodel.isLoadingCreate.value) {
                // Show loading animation when isLoading is true
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(24.dp)
                        .padding(end = 8.dp)
                    ,color = Color.White

                )
            } else {
                Text(text = stringResource(R.string.confirmar))
            }

        }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FieldName(viewmodel: CreateExerciseViewmodel) {
    var name by remember { mutableStateOf(viewmodel.name) }
    OutlinedTextField(
        value = name,
        onValueChange = { newValue ->
            name = newValue
            viewmodel.name= newValue
        },
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(4.dp))
            .border(
                width = 1.dp,
                color = colorResource(id = R.color.field)
            )// With padding show border color
            .background(colorResource(id = R.color.field)),
        placeholder = { Text(text = stringResource(R.string.nombre_del_ejercicio), color = Color(R.color.gray_text)) },
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
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FieldMuscle(viewmodel: CreateExerciseViewmodel) {
    var muscle by remember { mutableStateOf(viewmodel.muscle) }
    OutlinedTextField(
        value = muscle,
        onValueChange = { newValue ->
            muscle = newValue
            viewmodel.muscle= newValue
        },
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(4.dp))
            .border(
                width = 1.dp,
                color = colorResource(id = R.color.field)
            )// With padding show border color
            .background(colorResource(id = R.color.field)),
        placeholder = { Text(text = stringResource(R.string.m_sculo_trabajado), color = Color(R.color.gray_text)) },
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FieldType(viewmodel: CreateExerciseViewmodel){

    var isExpanded by remember {
        mutableStateOf(false)
    }
    var type by remember {
        mutableStateOf(viewmodel.type)
    }

    Column(modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        ExposedDropdownMenuBox(expanded = isExpanded, onExpandedChange = {isExpanded = it}) {
            TextField(value = type, onValueChange = {
                    newValue ->
                type = newValue
                viewmodel.type= newValue
            }, readOnly = true, trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
            }, modifier = Modifier
                .fillMaxWidth()
                .menuAnchor()
                .clip(RoundedCornerShape(4.dp))
                .border(
                    width = 1.dp,
                    color = colorResource(id = R.color.field)
                )// With padding show border color
                .background(colorResource(id = R.color.field)),
                colors = TextFieldDefaults.textFieldColors(containerColor = colorResource(id = R.color.field)) ,
                placeholder = { Text(text = stringResource(R.string.tipo_de_estimulo), color = Color(R.color.gray_text)) },
                leadingIcon = {
                    Icon(
                        modifier = Modifier.size(16.dp),
                        painter = painterResource(id = R.drawable.pesa),
                        contentDescription = stringResource(R.string.icon_field)
                    )
                })

            ExposedDropdownMenu(expanded = isExpanded, onDismissRequest = { isExpanded = false }) {
                DropdownMenuItem(text = { Text(text = stringResource(R.string.hipertrofia)) },
                    onClick = {
                        isExpanded = false
                        viewmodel.type = "Hipertrofia"
                        type = viewmodel.type
                    })
                DropdownMenuItem(text = { Text(text = stringResource(R.string.estres)) },
                    onClick = {
                        isExpanded = false
                        viewmodel.type = "Estres"
                        type = viewmodel.type

                    })
                DropdownMenuItem(text = { Text(text = stringResource(R.string.fuerza)) },
                    onClick = {
                        isExpanded = false
                        viewmodel.type = "Fuerza"
                        type = viewmodel.type

                    })
            }
        }
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FieldDifficulty(viewmodel: CreateExerciseViewmodel){

    var isExpanded by remember {
        mutableStateOf(false)
    }
    var difficulty by remember {
        mutableStateOf(viewmodel.difficulty)
    }

    Column(modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        ExposedDropdownMenuBox(expanded = isExpanded, onExpandedChange = {isExpanded = it}) {
            TextField(value = difficulty, onValueChange = {
                    newValue ->
                difficulty = newValue
                viewmodel.difficulty= newValue
            }, readOnly = true, trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
            }, modifier = Modifier
                .menuAnchor()
                .fillMaxWidth()
                .clip(RoundedCornerShape(4.dp))
                .border(
                    width = 1.dp,
                    color = colorResource(id = R.color.field)
                )// With padding show border color
                .background(colorResource(id = R.color.field)),
                colors = TextFieldDefaults.textFieldColors(containerColor = colorResource(id = R.color.field)) ,
                placeholder = { Text(text = stringResource(R.string.dificultad), color = Color(R.color.gray_text)) },
                leadingIcon = {
                    Icon(
                        modifier = Modifier.size(16.dp),
                        painter = painterResource(id = R.drawable.pesa),
                        contentDescription = stringResource(R.string.icon_field)
                    )
                })

            ExposedDropdownMenu(expanded = isExpanded, onDismissRequest = { isExpanded = false }) {
                DropdownMenuItem(text = { Text(text = stringResource(R.string.bajo)) },
                    onClick = {
                        isExpanded = false
                        viewmodel.difficulty = "Bajo"
                        difficulty = viewmodel.difficulty
                    })
                DropdownMenuItem(text = { Text(text = stringResource(R.string.medio)) },
                    onClick = {
                        isExpanded = false
                        viewmodel.difficulty = "Medio"
                        difficulty = viewmodel.difficulty
                    })
                DropdownMenuItem(text = { Text(text = stringResource(R.string.alto)) },
                    onClick = {
                        isExpanded = false
                        viewmodel.difficulty = "Alto"
                        difficulty = viewmodel.difficulty

                    })
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FieldDescription(viewmodel: CreateExerciseViewmodel) {
    var description by remember { mutableStateOf(viewmodel.description) }
    OutlinedTextField(
        value = description,
        onValueChange = { newValue ->
            description = newValue
            viewmodel.description= newValue
        },
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(4.dp))
            .border(
                width = 1.dp,
                color = colorResource(id = R.color.field)
            )// With padding show border color
            .background(colorResource(id = R.color.field)),
        placeholder = { Text(text = stringResource(R.string.descripci_n), color = Color(R.color.gray_text)) },
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FieldSets(viewmodel: CreateExerciseViewmodel) {
    var Sets by remember { mutableStateOf(viewmodel.sets) }
    OutlinedTextField(
        value = Sets,
        onValueChange = { newValue ->
            Sets = newValue
            viewmodel.sets= newValue
        },
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(4.dp))
            .border(
                width = 1.dp,
                color = colorResource(id = R.color.field)
            )// With padding show border color
            .background(colorResource(id = R.color.field)),
        placeholder = { Text(text = stringResource(R.string.sets), color = Color(R.color.gray_text)) },
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
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Next // Acción IME cuando se presiona la tecla Enter
        )
    )
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FieldReps(viewmodel: CreateExerciseViewmodel) {
    var reps by remember { mutableStateOf(viewmodel.reps) }
    OutlinedTextField(
        value = reps,
        onValueChange = { newValue ->
            reps = newValue
            viewmodel.reps= newValue
        },
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(4.dp))
            .border(
                width = 1.dp,
                color = colorResource(id = R.color.field)
            )// With padding show border color
            .background(colorResource(id = R.color.field)),
        placeholder = { Text(text = stringResource(R.string.repeticiones), color = Color(R.color.gray_text)) },
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
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Next
        )
    )
}





