package com.jder00138218.liftapp.ui.administrator.exerciseManager

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jder00138218.liftapp.R
@Preview
@Composable
fun Menu() {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(
            onClick = { /* Acción del primer botón */ },
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight(), colors = ButtonDefaults.buttonColors(
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
            onClick = { /* Acción del segundo botón */ },
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
            onClick = { /* Acción del tercer botón */ },
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



