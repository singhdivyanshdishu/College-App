package com.example.collegeapp.itemview

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.rememberAsyncImagePainter
import com.example.collegeapp.R
import com.example.collegeapp.models.BannerModel
import com.example.collegeapp.models.GalleryModel
import com.example.collegeapp.utils.Constant.isAdmin


@Composable
fun ImageItemView(
    imageUrl: String,
    cat: String,
    delete: (cat: String, image: String) -> Unit
) {
    OutlinedCard(modifier = Modifier.padding(4.dp)) {
        ConstraintLayout {
            val (image, delete) = createRefs()

            Image(
                painter = rememberAsyncImagePainter(model = imageUrl),
                contentDescription = null,
                modifier = Modifier
                    .height(150.dp)
                    .width(150.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )

            if (isAdmin)
                Card(
                    modifier = Modifier
                        .constrainAs(delete) {
                            top.linkTo(parent.top)
                            end.linkTo(parent.end)
                        }
                        .padding(4.dp)
                        .clickable {
                            delete(cat, imageUrl!!)
                        }


                ) {
                    Image(
                        painter = painterResource(id = R.drawable.baseline_delete_24),
                        contentDescription = null,
                        modifier = Modifier.padding(8.dp)
                    )
                }
        }
    }
}
