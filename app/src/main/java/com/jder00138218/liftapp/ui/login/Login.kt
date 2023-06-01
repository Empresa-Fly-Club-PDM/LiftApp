package com.jder00138218.liftapp.ui.login


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jder00138218.liftapp.R

@Preview(name = "login - view", showBackground = true)
@Composable
fun  LoginScreen(){
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(8.dp)
    ){
        Login(Modifier.align(Alignment.Center))
    }
}

@Composable
fun Login(modifier: Modifier) {
    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HeaderImage(Modifier.align(Alignment.CenterHorizontally))
            Spacer(modifier = Modifier.padding(8.dp))
            FieldEmail()
            Spacer(modifier = Modifier.padding(8.dp))
            FieldPassword()
            Spacer(modifier = Modifier.padding(8.dp))
            ForgotPassword(Modifier.align(Alignment.CenterHorizontally))
            Spacer(modifier = Modifier.padding(8.dp))
            SingIn(Modifier.align(Alignment.CenterHorizontally))
            Spacer(modifier = Modifier.padding(36.dp))
        }

        Column(
            modifier = Modifier.align(Alignment.BottomCenter),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OrSpacer(Modifier.align(Alignment.CenterHorizontally))
            Register(Modifier.align(Alignment.CenterHorizontally))
        }
    }
}

@Composable
fun SingIn(modifier: Modifier) {
    Button(onClick = {}, modifier = modifier
        .height(60.dp)
        .width(300.dp)
        .fillMaxWidth(), colors = ButtonDefaults.buttonColors(
        containerColor = Color.Red
    )){
        Text(text = "Ingresar")
    }
}


@Composable
fun Register(modifier: Modifier) {
    Row(modifier = modifier) {
        Text(text = "¿Aun no tienes cuenta?")
        Text(text = " Registrate", color = Color.Red, modifier = Modifier.clickable {  })
    }
}

@Composable
fun OrSpacer(modifier: Modifier) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Divider(
            color = Color.Black,
            modifier = Modifier.weight(1f),
            thickness = 1.dp
        )
        Text(
            text = "Or",
            modifier = Modifier.padding(horizontal = 8.dp)
        )
        Divider(
            color = Color.Black,
            modifier = Modifier.weight(1f),
            thickness = 1.dp
        )
    }
}


@Composable
fun HeaderImage(modifier: Modifier){
    Image(painter = painterResource(id = R.drawable.logoliftapp),
        contentDescription = "Image of lift app",
        modifier = modifier
    )
}

@Preview(name = "Email Field", showBackground = true )
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FieldEmail(){
    OutlinedTextField(value = "", onValueChange = {},
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(text = "Email") },
        singleLine = true,
        maxLines = 1
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FieldPassword(){
    OutlinedTextField(
        value = "", onValueChange = {},
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(text = "Password") },
        singleLine = true,
        maxLines = 1
    )
}

@Composable
fun ForgotPassword(modifier: Modifier){
    Text(text = stringResource(R.string.forgot_passw), modifier.clickable {  }, color = Color(R.color.forgotPaswd))
}

