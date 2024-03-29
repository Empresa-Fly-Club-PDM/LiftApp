package com.jder00138218.liftapp.ui.recovery.forgotPasword

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.jder00138218.liftapp.R
import com.jder00138218.liftapp.ui.login.viewmodel.LoginViewModel
import com.jder00138218.liftapp.ui.recovery.viewmodel.RecoveryViewModel
import com.jder00138218.liftapp.ui.users.user.HeaderBarBackArrowDumbell


@Composable
fun Recovery(navController: NavHostController) {
    val recovery: RecoveryViewModel = viewModel(
        factory = RecoveryViewModel.Factory
    )

    BlockFields(recovery, navController)
}

@Composable
fun BlockFields(recoveryViewModel: RecoveryViewModel, navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {

            Column(
                Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Text(
                    text = stringResource(R.string.olvido_su_contrase_a),
                    color = Color.Black,
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp
                    )
                )
                Spacer(modifier = Modifier.padding(8.dp))
                FieldEmail(recoveryViewModel)
                Spacer(modifier = Modifier.padding(24.dp))
                Confirm(Modifier.align(Alignment.CenterHorizontally), recoveryViewModel, navController)
                Spacer(modifier = Modifier.padding(8.dp))
                Button(onClick = {navController.popBackStack()}, colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White
                ), modifier = Modifier.height(60.dp)
                    .width(300.dp), elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 10.dp
                )) {
                    Text(text = "Regresar a ingreso", color = Color.Black)
                }
            }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FieldEmail(viewModel: RecoveryViewModel) {
    var emailUser by remember { mutableStateOf(viewModel.email) }

    OutlinedTextField(
        value = emailUser,
        onValueChange = { newValue ->
            emailUser = newValue
            viewModel.email = newValue
        },
        modifier = Modifier
            .width(350.dp)
            .clip(RoundedCornerShape(4.dp))
            .background(colorResource(id = R.color.field))
            .border(
                width = 1.dp,
                color = colorResource(id = R.color.field)
            )
            .background(colorResource(id = R.color.field)),
        placeholder = { Text(text = stringResource(R.string.correo), color = Color(R.color.gray_text)) },
        singleLine = true,
        maxLines = 1,
        leadingIcon = {
            Icon(
                modifier = Modifier.size(16.dp),
                painter = painterResource(id = R.drawable.icon_message),
                contentDescription = stringResource(R.string.icon_email)
            )
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = androidx.compose.ui.text.input.ImeAction.Next // Acción IME cuando se presiona la tecla Enter
        ),
    )
}

@Composable
fun Confirm(modifier: Modifier, viewModel: RecoveryViewModel, navController: NavHostController) {
    val context = LocalContext.current

    Button(
        onClick = { viewModel.onRecovery(navController, context)},
        modifier = modifier
            .height(60.dp)
            .width(300.dp)
            .fillMaxWidth(), colors = ButtonDefaults.buttonColors(
            containerColor = Color.Red
        ), elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 10.dp
        )
    ) {
        Row() {
            Text(text = stringResource(R.string.recuperar_contrase_a))
        }
    }
}