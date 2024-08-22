package com.example.collegeapp.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog


@Composable
fun LoadingDialog(
    onDismissRequest : () -> Unit = {}
){
    Dialog(onDismissRequest = { onDismissRequest() } ) {
        Box(modifier = Modifier
            .size(120.dp)
            .background(Color.White, shape = RoundedCornerShape(8.dp)),
            contentAlignment = Alignment.Center) {
            Column(modifier = Modifier.align(Alignment.Center)) {

                Spacer(modifier = Modifier.height(20.dp))


                CircularProgressIndicator(
//                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Loading...",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                    )
            }
        }
    }
}

//@Preview(showSystemUi = true)
//@Composable
//fun prev(){
//    LoadingDialog()
//
//}