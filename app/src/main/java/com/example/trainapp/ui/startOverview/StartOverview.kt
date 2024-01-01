package com.example.trainapp.ui.startOverview

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.trainapp.R

/**
 * A composable function that displays the start overview screen.
 * This screen serves as a welcoming view for the application, featuring a logo and introductory text.
 *
 * @param innerPadding The padding to apply to the content. This is useful for adjusting the layout based on navigation components or other UI elements that may affect the overall padding.
 */
@Composable
fun StartOverview(innerPadding: PaddingValues) {
    Box(modifier = Modifier.padding(innerPadding), contentAlignment = Alignment.TopCenter) {
        Column(modifier = Modifier.verticalScroll(rememberScrollState()), horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo",
            )
            Box(contentAlignment = Alignment.Center) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "Welkom aan boord!",
                        style = TextStyle(
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        ),
                        modifier = Modifier.padding(8.dp)
                    )
                    Text(
                        text = "Ontdek de fascinerende wereld van treinen met de Treinliefhebber App. Uw reis door de rijke geschiedenis en indrukwekkende techniek van de spoorwegen begint hier!",
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color.Gray
                        ),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }
    }
}