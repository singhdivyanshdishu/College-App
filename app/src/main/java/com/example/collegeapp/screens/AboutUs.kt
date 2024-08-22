package com.example.collegeapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.collegeapp.R
import com.example.collegeapp.viewmodel.CollegeInfoViewModel

@Composable
fun AboutUs() {
    val collegeInfoViewModel: CollegeInfoViewModel = viewModel()
    val collegeInfo by collegeInfoViewModel.collegeInfo.observeAsState(null)

    collegeInfoViewModel.getCollegeInfo()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        collegeInfo?.let { info ->
            // College Logo/Image
            info.imageUrl?.let {
//                logoUrl ->
                Card(
                    modifier = Modifier
                        //.size(150.dp)
                        .padding(8.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
//                    Image(
//                        painter = rememberAsyncImagePainter(model = logoUrl),
//                        contentDescription = "College Logo",
//                        modifier = Modifier.fillMaxSize(),
//                        contentScale = ContentScale.Crop
//                    )

                    Image(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = null,
                        modifier = Modifier.size(120.dp) // Adjust size as needed
                            .padding(bottom = 16.dp).background(Color.Transparent)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // College Name
            Text(
                text = info.name ?: "No Name",
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            // College Description
            Text(
                text = info.desc ?: "No Description",
                fontSize = 16.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            // College Address
            Text(
                text = "Address: ${info.address ?: "No Address"}",
                fontSize = 16.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Website Link
            info.websiteLink?.let { websiteLink ->
                ClickableText(
                    text = AnnotatedString(websiteLink),
                    onClick = { /* Handle website click, maybe open a browser */ },
                    modifier = Modifier.padding(8.dp),
                    style = androidx.compose.ui.text.TextStyle(
                        color = Color.Blue,
                        fontSize = 16.sp
                    )
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Contact Information
//            Text(
//                text = "Contact Us: ${info.contact ?: "No Contact Information"}",
//                fontSize = 16.sp,
//                textAlign = TextAlign.Center
//            )
        } ?: run {
            // Loading or no information available
            Text(text = "Loading college information...", fontSize = 16.sp)
        }
    }
}
