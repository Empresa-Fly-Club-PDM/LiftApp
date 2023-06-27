package com.jder00138218.liftapp.ui.users.admin.exerciseManager.CreateExercise

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import com.jder00138218.liftapp.ui.users.admin.Menu
import com.jder00138218.liftapp.ui.users.admin.exerciseManager.CreateExercise.viewmodel.CreateExerciseViewmodel


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

        Box(modifier = Modifier.fillMaxSize()) {

            Column( // 1
                Modifier
                    .align(Alignment.TopCenter)
                    .fillMaxHeight(0.1f)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Crear Ejercicio",
                    color = Color.Black,
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp
                    )
                )
            }


            Column( // 2
                Modifier
                    .align(Alignment.Center)
                    .fillMaxHeight(0.76f)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                FieldsDetaileCreate(createExerciseViewmodel,navController)
            }

            Column( // 3
                Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxHeight(0.06f)
                    .fillMaxWidth()
                    .background(Color.White),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Menu(navController)
            }

        }

    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FieldsDetaileCreate(viewmodel: CreateExerciseViewmodel,navController:NavHostController) {
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
    ButtonsCreate(viewmodel,navController)
}

@Composable
fun ButtonsCreate(viewmodel: CreateExerciseViewmodel, navController: NavHostController) {
    val context = LocalContext.current
        Button(
            onClick = {viewmodel.onCreate(navController, context)
            }, modifier = Modifier
                .height(60.dp)
                .width(175.dp)
                .fillMaxWidth(), colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.buttonGren)
            )
        ) {
            Text(text = " Confirmar")

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
            .width(350.dp)
            .clip(RoundedCornerShape(4.dp))
            .border(
                width = 1.dp,
                color = colorResource(id = R.color.field)
            )// With padding show border color
            .background(colorResource(id = R.color.field)),
        placeholder = { Text(text = "Nombre del Ejercicio", color = Color(R.color.gray_text)) },
        singleLine = true,
        maxLines = 1,
        leadingIcon = {
            Icon(
                modifier = Modifier.size(16.dp),
                painter = painterResource(id = R.drawable.pesa),
                contentDescription = "Icon field"
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
            .width(350.dp)
            .clip(RoundedCornerShape(4.dp))
            .border(
                width = 1.dp,
                color = colorResource(id = R.color.field)
            )// With padding show border color
            .background(colorResource(id = R.color.field)),
        placeholder = { Text(text = "Músculo trabajado", color = Color(R.color.gray_text)) },
        singleLine = true,
        maxLines = 1,
        leadingIcon = {
            Icon(
                modifier = Modifier.size(16.dp),
                painter = painterResource(id = R.drawable.pesa),
                contentDescription = "Icon field"
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

    Box(modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center) {
        ExposedDropdownMenuBox(expanded = isExpanded, onExpandedChange = {isExpanded = it}) {
            TextField(value = type, onValueChange = {
                    newValue ->
                type = newValue
                viewmodel.type= newValue
            }, readOnly = true, trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
            }, modifier = Modifier
                .menuAnchor()
                .width(350.dp)
                .clip(RoundedCornerShape(4.dp))
                .border(
                    width = 1.dp,
                    color = colorResource(id = R.color.field)
                )// With padding show border color
                .background(colorResource(id = R.color.field)),
                colors = TextFieldDefaults.textFieldColors(containerColor = colorResource(id = R.color.field)) ,
                placeholder = { Text(text ="Tip de estimulo", color = Color(R.color.gray_text)) },
                leadingIcon = {
                    Icon(
                        modifier = Modifier.size(16.dp),
                        painter = painterResource(id = R.drawable.pesa),
                        contentDescription = "Icon field"
                    )
                })

            ExposedDropdownMenu(expanded = isExpanded, onDismissRequest = { isExpanded = false }) {
                DropdownMenuItem(text = { Text(text = "Hipertrofia") },
                    onClick = {
                        isExpanded = false
                        viewmodel.type = "Hipertrofia"
                        type = viewmodel.type
                    })
                DropdownMenuItem(text = { Text(text = "Estres") },
                    onClick = {
                        isExpanded = false
                        viewmodel.type = "Estres"
                        type = viewmodel.type

                    })
                DropdownMenuItem(text = { Text(text = "Fuerza") },
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

    Box(modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center) {
        ExposedDropdownMenuBox(expanded = isExpanded, onExpandedChange = {isExpanded = it}) {
            TextField(value = difficulty, onValueChange = {
                    newValue ->
                difficulty = newValue
                viewmodel.difficulty= newValue
            }, readOnly = true, trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
            }, modifier = Modifier
                .menuAnchor()
                .width(350.dp)
                .clip(RoundedCornerShape(4.dp))
                .border(
                    width = 1.dp,
                    color = colorResource(id = R.color.field)
                )// With padding show border color
                .background(colorResource(id = R.color.field)),
                colors = TextFieldDefaults.textFieldColors(containerColor = colorResource(id = R.color.field)) ,
                placeholder = { Text(text = "Dificultad", color = Color(R.color.gray_text)) },
                leadingIcon = {
                    Icon(
                        modifier = Modifier.size(16.dp),
                        painter = painterResource(id = R.drawable.pesa),
                        contentDescription = "Icon field"
                    )
                })

            ExposedDropdownMenu(expanded = isExpanded, onDismissRequest = { isExpanded = false }) {
                DropdownMenuItem(text = { Text(text = "Bajo") },
                    onClick = {
                        isExpanded = false
                        viewmodel.difficulty = "Bajo"
                        difficulty = viewmodel.difficulty
                    })
                DropdownMenuItem(text = { Text(text = "Medio") },
                    onClick = {
                        isExpanded = false
                        viewmodel.difficulty = "Medio"
                        difficulty = viewmodel.difficulty
                    })
                DropdownMenuItem(text = { Text(text = "Alto") },
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
            .width(350.dp)
            .clip(RoundedCornerShape(4.dp))
            .border(
                width = 1.dp,
                color = colorResource(id = R.color.field)
            )// With padding show border color
            .background(colorResource(id = R.color.field)),
        placeholder = { Text(text = "Descripción", color = Color(R.color.gray_text)) },
        singleLine = true,
        maxLines = 1,
        leadingIcon = {
            Icon(
                modifier = Modifier.size(16.dp),
                painter = painterResource(id = R.drawable.pesa),
                contentDescription = "Icon field"
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
            .width(350.dp)
            .clip(RoundedCornerShape(4.dp))
            .border(
                width = 1.dp,
                color = colorResource(id = R.color.field)
            )// With padding show border color
            .background(colorResource(id = R.color.field)),
        placeholder = { Text(text = "Sets", color = Color(R.color.gray_text)) },
        singleLine = true,
        maxLines = 1,
        leadingIcon = {
            Icon(
                modifier = Modifier.size(16.dp),
                painter = painterResource(id = R.drawable.pesa),
                contentDescription = "Icon field"
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
            .width(350.dp)
            .clip(RoundedCornerShape(4.dp))
            .border(
                width = 1.dp,
                color = colorResource(id = R.color.field)
            )// With padding show border color
            .background(colorResource(id = R.color.field)),
        placeholder = { Text(text = "Repeticiones", color = Color(R.color.gray_text)) },
        singleLine = true,
        maxLines = 1,
        leadingIcon = {
            Icon(
                modifier = Modifier.size(16.dp),
                painter = painterResource(id = R.drawable.pesa),
                contentDescription = "Icon field"
            )
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Next // Acción IME cuando se presiona la tecla Enter
        )
    )
}





