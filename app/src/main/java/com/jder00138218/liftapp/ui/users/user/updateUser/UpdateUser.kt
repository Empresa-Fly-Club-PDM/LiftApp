package com.jder00138218.liftapp.ui.users.user.updateUser

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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.jder00138218.liftapp.LiftAppApplication
import com.jder00138218.liftapp.R
import com.jder00138218.liftapp.ui.users.admin.Menu
import com.jder00138218.liftapp.ui.users.admin.userManager.UpdateAdmin.viewmodel.UpdateAdminViewModel
import com.jder00138218.liftapp.ui.users.user.UserBottomMenu
import com.jder00138218.liftapp.ui.users.user.updateUser.viewmodel.UpdateUserViewModel
import java.util.Calendar
import java.util.Date

@Composable
fun UpdateUser(navController: NavHostController) {
    val updateUserViewModel: UpdateUserViewModel = viewModel(
        factory = UpdateUserViewModel.Factory
    )
    val context = LocalContext.current
    val app = context.applicationContext as LiftAppApplication

    if(app.getUserId()!=0){
        updateUserViewModel.getUserDetail(app.getUserId())
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
            Text(
                text = "Informacion de usuario",
                color = Color.Black,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                )
            )
            UpdateUserFields(updateUserViewModel,navController)
            ButtonsUpdateUser(app.getUserId(),updateUserViewModel,navController)
            UserBottomMenu(navController)
        }

    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
    fun UpdateUserFields(viewmodel: UpdateUserViewModel, navController: NavHostController) {
    Column(modifier = Modifier
        .fillMaxWidth()
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
        UpdateUserSelectField(viewmodel)
        Spacer(modifier = Modifier.padding(8.dp))
        FieldHeight(viewmodel = viewmodel)
        Spacer(modifier = Modifier.padding(8.dp))
        FieldWeight(viewmodel = viewmodel)
        Spacer(modifier = Modifier.padding(8.dp))
        DateInputField(viewmodel)
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateUserSelectField(viewmodel:UpdateUserViewModel){
    var isExpanded by remember {
        mutableStateOf(false)
    }

    Box(modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center) {
        ExposedDropdownMenuBox(expanded = isExpanded, onExpandedChange = {isExpanded = it}) {
            TextField(value = viewmodel._genero, onValueChange = {}, readOnly = true, trailingIcon = {
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
                placeholder = { Text(text = "Sexo", color = Color(R.color.gray_text)) },
                leadingIcon = {
                    Icon(
                        modifier = Modifier.size(16.dp),
                        painter = painterResource(id = R.drawable.pesa),
                        contentDescription = "Icon field"
                    )
                })

            ExposedDropdownMenu(expanded = isExpanded, onDismissRequest = { isExpanded = false }) {
                DropdownMenuItem(text = { Text(text = "Masculino") },
                    onClick = {
                        isExpanded = false
                        viewmodel._genero = "Masculino"
                    })
                DropdownMenuItem(text = { Text(text = "Femenino") },
                    onClick = {
                        isExpanded = false
                        viewmodel._genero = "Femenino"
                    })
            }
        }
    }

}

@Composable
fun ButtonsUpdateUser(id: Int?, updateuserviewmodel: UpdateUserViewModel, navController: NavHostController) {
    val context = LocalContext.current
    Row(modifier = Modifier.fillMaxWidth()) {
        Button(
            onClick = {updateuserviewmodel.onUpdate(id,navController,context)
            }, modifier = Modifier
                .height(60.dp)
                .fillMaxWidth(), colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.buttonGren)
            )
        ) {

            Text(text = "Actualizar Informacion")

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FieldWeight(viewmodel: UpdateUserViewModel) {
    OutlinedTextField(
        value = viewmodel._weight,
        onValueChange = { newValue ->
            viewmodel._weight= newValue
        },
        modifier = Modifier
            .width(350.dp)
            .clip(RoundedCornerShape(4.dp))
            .border(
                width = 1.dp,
                color = colorResource(id = R.color.field)
            )// With padding show border color
            .background(colorResource(id = R.color.field)),
        placeholder = { Text(text = "Peso en lb", color = Color(R.color.gray_text)) },
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
fun FieldHeight(viewmodel: UpdateUserViewModel) {
    OutlinedTextField(
        value = viewmodel._height,
        onValueChange = { newValue ->
            viewmodel._height= newValue
        },
        modifier = Modifier
            .width(350.dp)
            .clip(RoundedCornerShape(4.dp))
            .border(
                width = 1.dp,
                color = colorResource(id = R.color.field)
            )// With padding show border color
            .background(colorResource(id = R.color.field)),
        placeholder = { Text(text = "Estatura en cm", color = Color(R.color.gray_text)) },
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
fun FieldName(viewmodel: UpdateUserViewModel) {
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
        placeholder = { Text(text = "Nombre Completo", color = Color(R.color.gray_text)) },
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
fun FieldEmail(viewmodel: UpdateUserViewModel) {
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
        placeholder = { Text(text = "Email", color = Color(R.color.gray_text)) },
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
fun FieldPassword(viewModel: UpdateUserViewModel) {
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
        placeholder = { Text(text = "Password", color = Color(R.color.gray_text)) },
        singleLine = true,
        maxLines = 1,
        leadingIcon = {
            Icon(
                modifier = Modifier
                    .size(16.dp),
                painter = painterResource(id = R.drawable.icon_password),
                contentDescription = "Icon Password",
            )
        },
        trailingIcon = {

            Icon(
                modifier = Modifier
                    .size(16.dp)
                    .clickable { isVisible = !isVisible },
                painter = painterResource(id = R.drawable.icon_hide),
                contentDescription = "Hide Icon"
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
fun FieldConfirmPassword(viewModel: UpdateUserViewModel) {
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
        placeholder = { Text(text = "Password", color = Color(R.color.gray_text)) },
        singleLine = true,
        maxLines = 1,
        leadingIcon = {
            Icon(
                modifier = Modifier
                    .size(16.dp),
                painter = painterResource(id = R.drawable.icon_password),
                contentDescription = "Icon Password",
            )
        },
        trailingIcon = {

            Icon(
                modifier = Modifier
                    .size(16.dp)
                    .clickable { isVisible = !isVisible },
                painter = painterResource(id = R.drawable.icon_hide),
                contentDescription = "Hide Icon"
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
fun DateInputField(viewModel: UpdateUserViewModel) {
    // Fetching the Local Context
    val mContext = LocalContext.current

    // Declaring integer values
    // for year, month and day
    val mYear: Int
    val mMonth: Int
    val mDay: Int

    // Initializing a Calendar
    val mCalendar = Calendar.getInstance()

    // Fetching current year, month and day
    mYear = mCalendar.get(Calendar.YEAR)
    mMonth = mCalendar.get(Calendar.MONTH)
    mDay = mCalendar.get(Calendar.DAY_OF_MONTH)

    mCalendar.time = Date()

    // Declaring a string value to
    // store date in string format
    // Declaring DatePickerDialog and setting
    // initial values as current values (present year, month and day)
    val mDatePickerDialog = DatePickerDialog(
        mContext,
        { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            viewModel._fechanac = "$mDayOfMonth/${mMonth+1}/$mYear"
        }, mYear, mMonth, mDay
    )

    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {

        // Creating a button that on
        // click displays/shows the DatePickerDialog

        Button(
            modifier = Modifier
                .fillMaxWidth(),
            onClick = {
                mDatePickerDialog.show()
            }, colors = ButtonDefaults.buttonColors(contentColor = colorResource(id = R.color.field)) ) {
            Text(text = "Seleccionar Fecha de Nacimiento", color = Color.LightGray)
        }

        // Adding a space of 100dp height
        Spacer(modifier = Modifier.padding(2.dp))

        // Displaying the mDate value in the Text
        Text(text = "Fecha de nacimiento: ${viewModel._fechanac}", textAlign = TextAlign.Center)
    }
}