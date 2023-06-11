package com.jder00138218.liftapp.ui.user

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jder00138218.liftapp.R


@Preview
@Composable
fun RoutinesHeaderRowComponent() {
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
            text = "Routines",
            textAlign = TextAlign.Center,
            style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold),
            modifier = Modifier.weight(1f)
        )

        IconButton(
            onClick = { /* Handle back button click */ }
        ) {
            Icon(
                Icons.Outlined.Add,
                contentDescription = "Add"
            )
        }
    }
}

// Search Bar
@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(){

    var text by remember { mutableStateOf("search...") }
    OutlinedTextField(value = text, onValueChange = { newText: String ->
        text = newText }, modifier = Modifier.padding(horizontal = 8.dp)
        .fillMaxWidth()
        .clip(RoundedCornerShape(8.dp))
        .background(
            colorResource(id = R.color.field)
        )
        .border(width = 0.dp, color = Color.White)
    )
}

@Composable
fun RoutineMenuItem(muscleGroup: String){
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)
        .height(72.dp))
        { Box(modifier = Modifier.background(colorResource(id = R.color.card))) {
            Row(modifier = Modifier
                .fillMaxSize().padding(horizontal = 8.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = muscleGroup, style = TextStyle(fontSize = 20.sp, color = Color.Black))
                Button(modifier = Modifier , onClick = {}, colors = ButtonDefaults.buttonColors(containerColor = colorResource(
                    id = R.color.buttonRed
                ), contentColor = Color.White)) {
                    Icon(
                        Icons.Default.ArrowForward,
                        contentDescription = "to $muscleGroup routines"
                    )
                }
            }
        }

    }
}

@Preview
@Composable
fun UserBottomMenu() {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(
            onClick = { /* Acción del primer botón */ },
            modifier = Modifier
                .weight(1f)
               , colors = ButtonDefaults.buttonColors(
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
                .weight(1f), colors = ButtonDefaults.buttonColors(
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
                .weight(1f), colors = ButtonDefaults.buttonColors(
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




