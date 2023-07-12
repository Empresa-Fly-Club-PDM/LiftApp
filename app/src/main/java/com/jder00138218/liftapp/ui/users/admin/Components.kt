package com.jder00138218.liftapp.ui.users.admin

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.jder00138218.liftapp.R
import com.jder00138218.liftapp.network.dto.exercise.exercise
import com.jder00138218.liftapp.ui.navigation.Rutas

@Composable
fun ExerciseCardAdmin(exercise: exercise, ruta: String, navController: NavController){

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
                            .size(20.dp)
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
@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminSearchBar(){
    var text by remember { mutableStateOf("Buscar...") }
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
fun AdminHeaderBarBackArrowDumbell(title: String,  navController: NavController, backOnClick: () -> Unit){
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
@Composable
fun AdminProfileInfoRow(text: String) {
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

@Composable
fun AdminHeaderBarBackArrowAdd(title: String, navController: NavController, addOnClick: () ->Unit, backOnClick: () -> Unit) {
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
            onClick = { addOnClick()}
        ) {
            Icon(
                imageVector = iconHeader,
                contentDescription = stringResource(R.string.add)
            )
        }
    }
}

@Composable
fun Menu(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom
    ) {
        Button(
            onClick = {navController.navigate(route = Rutas.DashboardAdmin.ruta)},
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
            onClick = {navController.navigate(route = Rutas.AdminProfile.ruta)},
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
            onClick = {navController.navigate(route = Rutas.AdminVerifyExercise.ruta)},
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



