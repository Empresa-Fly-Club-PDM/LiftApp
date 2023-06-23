package com.jder00138218.liftapp.ui.users.user

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jder00138218.liftapp.R
import com.jder00138218.liftapp.ui.users.admin.Menu
import com.jder00138218.liftapp.ui.users.user.routinesmenu.RoutineMenuItem


@Preview(name = "Dash User", showBackground = true)
@Composable
fun DashboardUserScreen() {
    var nameUser = "Daniel Rivera"

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {

        Box(modifier = Modifier.fillMaxSize()) {

            Column(
                // 1
                Modifier
                    .align(Alignment.TopCenter)
                    .fillMaxHeight(0.1f)
                    .fillMaxWidth()
                    .padding(8.dp),
            ) {
                Text(text = "Bienvenido,", color = colorResource(id = R.color.gray_text))
                Text(
                    text = "${nameUser}",
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
                    .fillMaxHeight(0.84f)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                MainInfoUser()
            }

            Column( // 3
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

@Composable
fun MainInfoUser() {
    Column() {
        BestInfoUser("")
        InfoUser()
        RankingFriends()
        RoutineMenuItem("Rutinas")
    }
}

@Composable
fun RankingFriends() {
    var index = 1
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.bcCard),
        )
    ) {
        Row() {

            Column(
                verticalArrangement = Arrangement.Top, // Alinear elementos en la parte superior
                modifier = Modifier.padding(8.dp)
            ) {
                Text(
                    text = "De tus amigos...",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(4.dp)
                )// Aplica el estilo de texto en negrita)
                ItemFriend("${index} Nombre Usuario")
                ItemFriend("${index} Nombre Usuario")
                ItemFriend("${index} Nombre Usuario")
            }

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.weight(1f)) // Espacio flexible para empujar el botón hacia la derecha
                Button(
                    onClick = { /* Acción del botón */ },
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White
                    ),
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text(text = "Rankings", color = Color.Red)
                }
            }
        }


    }
}

@Composable
fun ItemFriend(name: String) {
    Text(
        text = name,
        color = Color.White,
        fontWeight = FontWeight.Bold, // Aplica el estilo de texto en negrita
        modifier = Modifier.padding(4.dp)
    )
}


@Composable
fun InfoUser() {
    Column() {
        Row() {
            dataItem("Heigth", "180cm")
            dataItem("Weigth", "65Kg")
            dataItem("Age", "22yo")
        }
        Spacer(modifier = Modifier.padding(4.dp))
        Row() {
            Image(
                painter = painterResource(id = R.drawable.level_user),
                contentDescription = "Image Level",
                modifier = Modifier
                    .width(250.dp)
                    .height(180.dp)
            )
            Column() {
                dataItem("Puntaje", "1102")
                Spacer(modifier = Modifier.padding(2.dp))
                dataItem("Nivel", "Elite")
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
fun BestInfoUser(muscleGroup: String) {
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
                    text = "Mejor Levantantamiento",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = Color.Black
                    )
                )
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "500 LB", color = Color.Red)
                    Text(text = "Leg Press", color = Color.DarkGray)
                }

            }
        }
    }
}

