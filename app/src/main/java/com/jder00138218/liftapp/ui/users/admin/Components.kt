package com.jder00138218.liftapp.ui.users.admin

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.jder00138218.liftapp.R
import com.jder00138218.liftapp.ui.navigation.Rutas



@Composable
fun AdminHeaderBarBackArrowDumbell(title: String){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = { /* Handle back button click */ }
        ) {
            Icon(
                Icons.Default.ArrowBack,
                contentDescription = "Back"
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
                contentDescription = "Pesa icon",
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
        Image(
            painter = painterResource(R.drawable.pesa),
            contentDescription = "Image",
            modifier = Modifier.size(48.dp)
        )

        Text(
            text = text,
            modifier = Modifier.padding(start = 16.dp),
            style = TextStyle(color = Color.Black, fontSize = 16.sp)
        )

        Button(
            onClick = {},
            colors = ButtonDefaults.buttonColors(containerColor = colorResource(
                id = R.color.buttonRed))
        ) {
            Text(
                text = "Editar",
                color = Color.White
            )
        }
    }
}

@Composable
fun AdminHeaderBarBackArrowAdd(title: String, navController: NavController, addOnClick: () ->Unit, backOnClick: () -> Unit) {
    var iconHeader = Icons.Outlined.Add

    if(title == "Ranking"){
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
                contentDescription = "Back"
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
                contentDescription = "Add"
            )
        }
    }
}

@Composable
fun Menu(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(
            onClick = {navController.navigate(route = Rutas.DashboardAdmin.ruta)},
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White
            )
        ) {
            Icon(
                painter = painterResource(R.drawable.inbox),
                contentDescription = "Imbox icon",
                tint = Color.Red,
                modifier = Modifier.size(20.dp)
            )
        }

        Button(
            onClick = {},
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight(), colors = ButtonDefaults.buttonColors(
                containerColor = Color.White
            )
        ) {
            Icon(
                painter = painterResource(R.drawable.profile),
                contentDescription = "Profile icon",
                tint = colorResource(id = R.color.gray_text),
                modifier = Modifier.size(20.dp)
            )
        }

        Button(
            onClick = {navController.navigate(route = Rutas.AdminVerifyExercise.ruta)},
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight(), colors = ButtonDefaults.buttonColors(
                containerColor = Color.White
            )
        ) {
            Icon(
                painter = painterResource(R.drawable.pesa),
                contentDescription = "Pesa icon",
                tint = colorResource(id = R.color.gray_text),
                modifier = Modifier.size(30.dp)
            )
        }
    }
}



