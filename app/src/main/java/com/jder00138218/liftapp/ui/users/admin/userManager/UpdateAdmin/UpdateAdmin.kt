package com.jder00138218.liftapp.ui.users.admin.userManager.UpdateAdmin

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.jder00138218.liftapp.R
import com.jder00138218.liftapp.ui.users.admin.AdminHeaderBarBackArrowDumbell
import com.jder00138218.liftapp.ui.users.admin.Menu
import com.jder00138218.liftapp.ui.users.admin.exerciseManager.CreateExercise.viewmodel.CreateExerciseViewmodel
import com.jder00138218.liftapp.ui.users.admin.userManager.CreateAdmin.viewmodel.CreateAdminViewModel
import com.jder00138218.liftapp.ui.users.admin.userManager.UpdateAdmin.viewmodel.UpdateAdminViewModel
import java.util.Calendar
import java.util.Date

@Composable
fun UpdateAdmin(navController: NavHostController) {
    val navBackStackEntry = navController.currentBackStackEntry
    val userid = navBackStackEntry?.arguments?.getInt(stringResource(R.string.id))
    val updateAdminViewModel: UpdateAdminViewModel = viewModel(
        factory = UpdateAdminViewModel.Factory
    )

    if(userid!=0){
        updateAdminViewModel.getUserDetail(userid)
    }
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
            AdminHeaderBarBackArrowDumbell(title = stringResource(R.string.informacion_personal), navController = navController, backOnClick = {navController.popBackStack()})
            CreateAdminFields(updateAdminViewModel,navController)
            ButtonsUpdate(userid,updateAdminViewModel,navController)
            Menu(navController)
        }

    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateAdminFields(viewmodel: UpdateAdminViewModel, navController: NavHostController) {
    Column(modifier = Modifier
        .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally) {
        FieldName(viewmodel)
        Spacer(modifier = Modifier.padding(2.dp))
        FieldEmail(viewmodel)
        Spacer(modifier = Modifier.padding(2.dp))
        FieldPassword(viewmodel)
        Spacer(modifier = Modifier.padding(2.dp))
        FieldConfirmPassword(viewmodel)
        Spacer(modifier = Modifier.padding(8.dp))
    }

}

@Composable
fun ButtonsUpdate(id: Int?, updateAdminViewModel: UpdateAdminViewModel, navController: NavHostController) {
    val context = LocalContext.current
    Row() {
        Button(
            onClick = {updateAdminViewModel.onUpdate(id,navController,context)
                updateAdminViewModel._loading.value=true
            }, modifier = Modifier
                .height(60.dp)
                .width(175.dp)
                .fillMaxWidth(), colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.buttonGray)
            )
        ) {

            if (updateAdminViewModel._loading.value) {
                // Show loading animation when isLoading is true
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(36.dp)
                        .padding(end = 8.dp),
                    color = Color.White
                )
            } else {
                Text(text = stringResource(R.string.editar))
            }
        }

        Button(
            onClick = {updateAdminViewModel.deleteUser(id, navController,context)
            }, modifier = Modifier
                .height(60.dp)
                .width(175.dp)
                .fillMaxWidth(), colors = ButtonDefaults.buttonColors(
                containerColor = Color.Red
            )
        ) {
                Text(text = stringResource(R.string.eliminar))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FieldName(viewmodel: UpdateAdminViewModel) {
    OutlinedTextField(
        value = viewmodel._nombrecompleto,
        onValueChange = { newValue ->
            viewmodel._nombrecompleto= newValue
        },
        modifier = Modifier
            .width(350.dp)
            .clip(RoundedCornerShape(4.dp))
            .border(
                width = 1.dp,
                color = colorResource(id = R.color.field)
            )// With padding show border color
            .background(colorResource(id = R.color.field)),
        placeholder = { Text(text = stringResource(R.string.nombre_completo), color = Color(R.color.gray_text)) },
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
fun FieldEmail(viewmodel: UpdateAdminViewModel) {
    OutlinedTextField(
        value = viewmodel._email,
        onValueChange = { newValue ->
            viewmodel._email= newValue
        },
        modifier = Modifier
            .width(350.dp)
            .clip(RoundedCornerShape(4.dp))
            .border(
                width = 1.dp,
                color = colorResource(id = R.color.field)
            )// With padding show border color
            .background(colorResource(id = R.color.field)),
        placeholder = { Text(text = stringResource(R.string.email), color = Color(R.color.gray_text)) },
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
fun FieldPassword(viewModel: UpdateAdminViewModel) {
    var isVisible by remember { mutableStateOf(viewModel._isVisiblePaswd) }
    val visualTransformation =
        if (isVisible) VisualTransformation.None else PasswordVisualTransformation()

    OutlinedTextField(
        value = viewModel._password,
        onValueChange = { newValue ->
            viewModel._password = newValue
        },
        modifier = Modifier
            .width(350.dp)
            .clip(RoundedCornerShape(4.dp))
            .background(colorResource(id = R.color.field))
            .border(
                width = 1.dp,
                color = colorResource(id = R.color.field)
            ),
        placeholder = { Text(text = stringResource(R.string.password), color = Color(R.color.gray_text)) },
        singleLine = true,
        maxLines = 1,
        leadingIcon = {
            Icon(
                modifier = Modifier
                    .size(16.dp),
                painter = painterResource(id = R.drawable.icon_password),
                contentDescription = stringResource(R.string.icon_password),
            )
        },
        trailingIcon = {

            Icon(
                modifier = Modifier
                    .size(16.dp)
                    .clickable { isVisible = !isVisible },
                painter = painterResource(id = R.drawable.icon_hide),
                contentDescription = stringResource(R.string.hide_icon)
            )
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Next // Acción IME cuando se presiona la tecla Enter
        ),
        visualTransformation = visualTransformation
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FieldConfirmPassword(viewModel: UpdateAdminViewModel) {
    var isVisible by remember { mutableStateOf(viewModel._isVisiblePaswd) }

    val visualTransformation =
        if (isVisible) VisualTransformation.None else PasswordVisualTransformation()

    OutlinedTextField(
        value = viewModel._confirmpassowrd,
        onValueChange = { newValue ->
            viewModel._confirmpassowrd = newValue
        },
        modifier = Modifier
            .width(350.dp)
            .clip(RoundedCornerShape(4.dp))
            .background(colorResource(id = R.color.field))
            .border(
                width = 1.dp,
                color = colorResource(id = R.color.field)
            ),
        placeholder = { Text(text = stringResource(R.string.password), color = Color(R.color.gray_text)) },
        singleLine = true,
        maxLines = 1,
        leadingIcon = {
            Icon(
                modifier = Modifier
                    .size(16.dp),
                painter = painterResource(id = R.drawable.icon_password),
                contentDescription = stringResource(R.string.icon_password),
            )
        },
        trailingIcon = {

            Icon(
                modifier = Modifier
                    .size(16.dp)
                    .clickable { isVisible = !isVisible },
                painter = painterResource(id = R.drawable.icon_hide),
                contentDescription = stringResource(R.string.hide_icon)
            )
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Next // Acción IME cuando se presiona la tecla Enter
        ),
        visualTransformation = visualTransformation
    )
}