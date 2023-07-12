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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.jder00138218.liftapp.R
import com.jder00138218.liftapp.ui.users.user.CustomInputField
import com.jder00138218.liftapp.ui.users.user.CustomSelectField
import com.jder00138218.liftapp.ui.users.user.CustomTypeSelectField
import com.jder00138218.liftapp.ui.users.user.HeaderBarBackArrowDumbell
import com.jder00138218.liftapp.ui.users.user.UserBottomMenu

@Composable
fun UserPersonalExerciseDetails(navController: NavController){
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
        .padding(8.dp)) {
        Column(modifier = Modifier
            .fillMaxSize()
            ) {

            HeaderBarBackArrowDumbell(title = stringResource(R.string.detalle_de_ejercicio), navController, backOnClick = {navController.popBackStack()})
            Column(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.9f),
                verticalArrangement = Arrangement.SpaceBetween
            ){
                Column(modifier = Modifier
                    .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    CustomInputField(hint = stringResource(R.string.nombre_del_ejercicio))
                    Spacer(modifier = Modifier.height(2.dp))
                    CustomTypeSelectField()
                    Spacer(modifier = Modifier.height(2.dp))
                    CustomSelectField()
                    Spacer(modifier = Modifier.height(2.dp))
                    CustomInputField(hint = stringResource(R.string.descripci_n))
                    Spacer(modifier = Modifier.height(2.dp))
                    CustomInputField(hint = "Sets")
                    Spacer(modifier = Modifier.height(2.dp))
                    CustomInputField(hint = stringResource(R.string.repeticiones))
                }

                Button(modifier = Modifier.fillMaxWidth() , onClick = {}, colors = ButtonDefaults.buttonColors(containerColor = colorResource(
                    id = R.color.buttonRed
                ), contentColor = Color.White)) {
                    Text(text = stringResource(R.string.remover_de_la_rutina))
                }
            }
                UserBottomMenu(navController)
            
        }
    }
}