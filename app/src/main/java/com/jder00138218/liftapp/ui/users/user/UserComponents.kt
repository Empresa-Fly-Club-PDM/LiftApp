package com.jder00138218.liftapp.ui.users.user

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
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
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.jder00138218.liftapp.R
import com.jder00138218.liftapp.network.dto.exercise.exercise
import com.jder00138218.liftapp.ui.navigation.Rutas

@Composable
fun ExerciseCardUser(exercise: exercise, ruta: String, navController: NavController){

    val isVerified = exercise.verified

    Card(modifier = Modifier.fillMaxWidth().clickable {
        navController.navigate(route = ruta)
    }, colors = CardDefaults.cardColors(
        containerColor = colorResource(id = R.color.card)
    )) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(text = exercise.name, softWrap = true, modifier = Modifier.weight(1f), textAlign = TextAlign.Center)
                if(isVerified){
                    Icon(painter = painterResource(id = R.drawable.shield_done),
                    contentDescription = stringResource(R.string.verify_icon),
                    modifier = Modifier
                        .size(30.dp)
                        .weight(1f))
                }else{
                    Icon(painter = painterResource(id = R.drawable.pesa),
                        contentDescription = stringResource(R.string.pesa_icon),
                        modifier = Modifier
                            .size(20.dp)
                            .weight(1f))
                }

            }
            Row(modifier = Modifier
                .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Card(modifier = Modifier
                    .weight(1f)
                    .height(56.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    )
                ) {
                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Row(modifier = Modifier
                            .fillMaxWidth()
                            , horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                            Text(text = stringResource(id = R.string.musculo), softWrap = true, modifier = Modifier, textAlign = TextAlign.Center, color = Color.Red)
                        }
                        Row(modifier = Modifier
                            .fillMaxWidth()
                            , horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                            Text(text = exercise.muscle, softWrap = true, modifier = Modifier, textAlign = TextAlign.Center, color = Color.Black)
                        }
                    }

                }
                Spacer(modifier = Modifier.width(16.dp))
                Card(modifier = Modifier
                    .weight(1f)
                    .height(56.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    )
                ) {
                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Row(modifier = Modifier
                            .fillMaxWidth()
                            , horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                            Text(text = exercise.type, softWrap = true, modifier = Modifier, textAlign = TextAlign.Center, color = Color.Red)
                        }
                        Row(modifier = Modifier
                            .fillMaxWidth()
                            , horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                            Text(text = "${exercise.sets} x ${exercise.reps}", softWrap = true, modifier = Modifier, textAlign = TextAlign.Center, color = Color.Black)
                        }
                    }

                }

            }
        }

    }

}
@Composable
fun UserProfileInfoRow(text: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {

        Text(
            text = text,
            modifier = Modifier.padding(start = 16.dp),
            style = TextStyle(color = Color.Black, fontSize = 16.sp)
        )
    }
}
// Search Bar
@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(){

    var text by remember { mutableStateOf("Buscar..") }
    OutlinedTextField(value = text, onValueChange = { newText: String ->
        text = newText }, modifier = Modifier
        .padding(horizontal = 8.dp)
        .fillMaxWidth()
        .clip(RoundedCornerShape(8.dp))
        .background(
            colorResource(id = R.color.field)
        )
        .border(width = 0.dp, color = Color.White)
    )
}


@Composable
fun UserBottomMenu(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom
    ) {
        Button(
            onClick = {navController.navigate(route = Rutas.DashboardUser.ruta)},
            modifier = Modifier
                .weight(1f),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White
            )
        ) {
            Icon(
                painter = painterResource(R.drawable.inbox),
                contentDescription = stringResource(R.string.inbox_icon),
                tint = Color.Gray,
                modifier = Modifier.size(20.dp)
            )
        }

        Button(
            onClick = {navController.navigate(route = Rutas.UserProfile.ruta)},
            modifier = Modifier
                .weight(1f)
            , colors = ButtonDefaults.buttonColors(
                containerColor = Color.White
            )
        ) {
            Icon(
                painter = painterResource(R.drawable.profile),
                contentDescription = stringResource(R.string.profile_icon),
                tint = colorResource(id = R.color.gray_text),
                modifier = Modifier.size(20.dp)
            )
        }

        Button(
            onClick = {navController.navigate(route = Rutas.UserExercises.ruta)},
            modifier = Modifier
                .weight(1f), colors = ButtonDefaults.buttonColors(
                containerColor = Color.White
            )
        ) {
            Icon(
                painter = painterResource(R.drawable.pesa),
                contentDescription = stringResource(R.string.pesa_icon),
                tint = colorResource(id = R.color.gray_text),
                modifier = Modifier.size(30.dp)
            )
        }
    }
}

@Composable
fun HeaderBarBackArrowAdd(title: String, navController: NavController, addOnClick: () ->Unit, backOnClick: () -> Unit) {
    var iconHeader = Icons.Outlined.Add

    if(title == stringResource(R.string.ranking)){
        iconHeader = Icons.Outlined.Search
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = {backOnClick()}
        ) {
            Icon(
                Icons.Default.ArrowBack,
                contentDescription = stringResource(R.string.back)
            )
        }

        Text(
            text = title,
            textAlign = TextAlign.Center,
            style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold),
            modifier = Modifier.weight(1f)
        )

        IconButton(
            onClick = {addOnClick()}
        ) {
            Icon(
                imageVector = iconHeader,
                contentDescription = stringResource(R.string.add)
            )
        }
    }
}
@Composable
fun HeaderBarBackArrowCheck(title: String, navController: NavController, backOnClick: () -> Unit, checkOnClick: () -> Unit){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = { backOnClick() }
        ) {
            Icon(
                Icons.Default.ArrowBack,
                contentDescription = stringResource(R.string.back)
            )
        }

        Text(
            text = title,
            textAlign = TextAlign.Center,
            style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold),
            modifier = Modifier.weight(1f)
        )

        IconButton(
            onClick = { checkOnClick()},

        ) {
            Icon(
                Icons.Default.Check,
                contentDescription = stringResource(R.string.back)
            )
        }
    }
}

@Composable
fun HeaderBarBackArrowDumbell(title: String, navController: NavController, backOnClick: () -> Unit){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = { backOnClick()}
        ) {
            Icon(
                Icons.Default.ArrowBack,
                contentDescription = stringResource(R.string.back)
            )
        }

        Text(
            text = title,
            textAlign = TextAlign.Center,
            style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold),
            modifier = Modifier.weight(1f)
        )

        IconButton(
            onClick = { /* Handle back button click */ },

            ) {
            Icon(
                painter = painterResource(R.drawable.pesa),
                contentDescription = stringResource(R.string.pesa_icon),
                tint = colorResource(id = R.color.gray_text),
                modifier = Modifier.size(30.dp)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomInputField(hint: String){
    OutlinedTextField(
        value = "",
        onValueChange = { },
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(4.dp))
            .border(
                width = 1.dp,
                color = colorResource(id = R.color.field)
            )// With padding show border color
            .background(colorResource(id = R.color.field)),
        placeholder = { Text(text = hint, color = Color(R.color.gray_text)) },
        singleLine = true,
        maxLines = 1,
        leadingIcon = {
            Icon(
                modifier = Modifier.size(16.dp),
                painter = painterResource(id = R.drawable.pesa),
                contentDescription = stringResource(R.string.pesa_icon)
            )
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next // Acción IME cuando se presiona la tecla Enter
        )
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun CustomSelectField(){

    var isExpanded by remember {
        mutableStateOf(false)
    }
    var option by remember {
        mutableStateOf("")
    }

    Box(modifier = Modifier.fillMaxWidth(),
    contentAlignment = Alignment.Center) {
        ExposedDropdownMenuBox(expanded = isExpanded, onExpandedChange = {isExpanded = it}) {
            TextField(value = option, onValueChange = {}, readOnly = true, trailingIcon = {
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
                DropdownMenuItem(text = { Text(text = stringResource(R.string.back)) },
                    onClick = {
                        isExpanded = false
                        option = "Alto"
                    })
                DropdownMenuItem(text = { Text(text = stringResource(R.string.medio)) },
                    onClick = {
                        isExpanded = false
                        option = "Medio"
                    })
                DropdownMenuItem(text = { Text(text = stringResource(R.string.alto)) },
                    onClick = {
                        isExpanded = false
                        option = "Alto"
                    })
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun CustomTypeSelectField(){

    var isExpanded by remember {
        mutableStateOf(false)
    }
    var option by remember {
        mutableStateOf("")
    }

    Box(modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center) {
        ExposedDropdownMenuBox(expanded = isExpanded, onExpandedChange = {isExpanded = it}) {
            TextField(value = option, onValueChange = {}, readOnly = true, trailingIcon = {
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
                placeholder = { Text(text = stringResource(R.string.tipo), color = Color(R.color.gray_text)) },
                leadingIcon = {
                    Icon(
                        modifier = Modifier.size(16.dp),
                        painter = painterResource(id = R.drawable.pesa),
                        contentDescription = stringResource(R.string.pesa_icon)
                    )
                })

            ExposedDropdownMenu(expanded = isExpanded, onDismissRequest = { isExpanded = false }) {
                DropdownMenuItem(text = { Text(text = stringResource(R.string.pecho)) },
                    onClick = {
                        isExpanded = false
                        option = "Alto"
                    })
                DropdownMenuItem(text = { Text(text = stringResource(R.string.piernas)) },
                    onClick = {
                        isExpanded = false
                        option = "Medio"
                    })
                DropdownMenuItem(text = { Text(text = stringResource(R.string.espalda)) },
                    onClick = {
                        isExpanded = false
                        option = "Alto"
                    })
                DropdownMenuItem(text = { Text(text = stringResource(R.string.hombros)) },
                    onClick = {
                        isExpanded = false
                        option = "Hombros"
                    })
                DropdownMenuItem(text = { Text(text = stringResource(R.string.cardio)) },
                    onClick = {
                        isExpanded = false
                        option = "Alto"
                    })
                DropdownMenuItem(text = { Text(text = stringResource(R.string.abdomen)) },
                    onClick = {
                        isExpanded = false
                        option = "Alto"
                    })
            }
        }
    }

}


@Composable
fun ItemEx() {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ), modifier = Modifier
            .padding(4.dp)
            .size(width = 160.dp, height = 60.dp)
    ) {
        Column(Modifier.padding(8.dp)) {
            Text(text = stringResource(R.string.press_de_banca), color = Color.Red)
            Text(text = stringResource(R.string.pecho), color = Color(R.color.gray_text))
        }
    }

}




