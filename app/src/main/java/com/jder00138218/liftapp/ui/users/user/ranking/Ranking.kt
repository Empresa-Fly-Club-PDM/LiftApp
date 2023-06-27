package com.jder00138218.liftapp.ui.users.user.ranking

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jder00138218.liftapp.R
import com.jder00138218.liftapp.ui.users.admin.Menu
import com.jder00138218.liftapp.ui.users.user.HeaderBarBackArrowAdd

@Preview(name = "Ranking User", showBackground = true)
@Composable
fun RankingUsers() {


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
                HeaderBarBackArrowAdd("Ranking")
            }


            Column( // 2
                Modifier
                    .align(Alignment.Center)
                    .fillMaxHeight(0.84f)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                RankingInfo()
            }

            Column( // 3
                Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxHeight(0.06f)
                    .fillMaxWidth()
                    .background(Color.White),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

            }

        }

    }


}

@Composable
fun RankingInfo() {

    ItemUser("Enrique")
    ItemUser("Juan Daniel Escobar Rivera")
    ItemUser("Mike Mentzer")
    ItemUser("Gerardo Rivera Rodriguez")
    ItemUser("Mike Mentzer")
    ItemUser("Eduardo Granados")

}

@Composable
fun ItemUser(name: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column() {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "1")
                Spacer(modifier = Modifier.padding(8.dp))
                Column() {
                    Text(text = name, style = TextStyle(fontSize = 14.sp), color = Color.Black)
                    Text(text = "1102", color = colorResource(id = R.color.gray_text))
                }
            }
            Spacer(modifier = Modifier.padding(8.dp))
            Divider(
                modifier = Modifier
                    .height(1.dp)
                    .fillMaxWidth(),
                color = Color.LightGray
            )
        }

    }
}

