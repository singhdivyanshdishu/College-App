package com.example.collegeapp.admin.screens

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.collegeapp.R
import com.example.collegeapp.itemview.GalleryItemView
import com.example.collegeapp.navigation.Routes
import com.example.collegeapp.ui.theme.Purple40
import com.example.collegeapp.utils.Constant.BANNER
import com.example.collegeapp.viewmodel.GalleryViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ManageGallery(navController:NavController) {

    val context = LocalContext.current
    val galleryViewModel: GalleryViewModel = GalleryViewModel()
    // val galleryViewModel: GalleryViewModel = viewModel()

    val isUploaded by galleryViewModel.isPosted.observeAsState(false)
    val isDeleted by galleryViewModel.isPosted.observeAsState(false)
    //val bannerList by bannerViewModel.isPosted.observeAsState()
    val galleryList by galleryViewModel.galleryList.observeAsState(null)

    val option = arrayListOf<String>()

    galleryViewModel.getGallery()

    var imageUri by remember {
        mutableStateOf<Uri?>(null)
    }

    var isCategory by remember {
        mutableStateOf(false)
    }

    var mExpanded by remember {
        mutableStateOf(false)
    }

    var isImage by remember {
        mutableStateOf(false)
    }



    var category by remember {
        mutableStateOf("")
    }

    val launcher =
        rememberLauncherForActivityResult(
            contract =
            ActivityResultContracts.GetContent()) {
            imageUri = it
        }


    LaunchedEffect(isUploaded) {
        if (isUploaded) {
            Toast.makeText(context, "Data Added", Toast.LENGTH_SHORT).show()
            imageUri = null
            isCategory = false
            isImage = false
            category = ""

        }
    }

    LaunchedEffect(isDeleted) {
        if (isDeleted) {
            Toast.makeText(context, "Data Deleted", Toast.LENGTH_SHORT).show()
        }
    }

    Scaffold(

        topBar = {
            TopAppBar(title = {
                Text(text = "Manage Gallery", color = Color.White)
            }, colors = TopAppBarDefaults.largeTopAppBarColors(containerColor = Purple40),

                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                })
        }
    )
    { padding ->
        Column(modifier = Modifier.padding(padding)) {

            Row(modifier = Modifier.padding(8.dp)){
                Card(modifier = Modifier
                    .weight(1f)
                    .padding(4.dp)
                    .clickable {
                        isCategory = true
                        isImage = false
                    }) {
                    Text(text = "Add Category",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center

                    )
                }

                Card(modifier = Modifier
                    .weight(1f)
                    .padding(4.dp)
                    .clickable {
                        isCategory = false
                        isImage = true
                    }) {
                    Text(text = "Add Image",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center

                    )
                }
            }

            if (isCategory)
                ElevatedCard(modifier = Modifier.padding(8.dp)){

                    OutlinedTextField(value = category,
                        onValueChange = {
                            category = it
                        },
                        placeholder = { Text(text = "Category...") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(4.dp)
                    )

                    Image(
                        painter = if (imageUri == null) painterResource(id = R.drawable.placeholderimage)
                        else rememberAsyncImagePainter(model = imageUri),
                        contentDescription = "banner_image",
                        modifier = Modifier
                            .height(220.dp)
                            .fillMaxWidth()
                            .clickable {
                                launcher.launch("image/*")
                            },
                        contentScale = ContentScale.Crop

                    )

                    Row {
                        Button(
                            onClick = {
//                            if(imageUri==null || title == null || link == null) {
                                if(category == "" && imageUri ==null){
                                    Toast.makeText(
                                        context,
                                        "Please Provide All Fields",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                                else
                                    galleryViewModel.saveGalleryImage(imageUri!!,category,true)


                            }, modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)
                                .padding(4.dp)
                        ) {
                            Text(text = "Add Category")

                        }

                        OutlinedButton(
                            onClick = {
                                imageUri = null
                                isCategory = false
                                isImage = false
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)
                                .padding(4.dp)
                        ) {
                            Text(text = "Cancel")

                        }
                    }

                }

            if (isImage)
                ElevatedCard(modifier = Modifier.padding(8.dp)) {


                    Column(horizontalAlignment = Alignment.CenterHorizontally){

                        Box(modifier = Modifier.wrapContentSize(Alignment.TopStart)) {

                            OutlinedTextField(
                                value = category,
                                onValueChange = {
                                    category = it
                                },
                                readOnly = true,
                                placeholder = { Text(text = "Select Gallery") },
                                label = { Text(text = "Gallery") },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(4.dp),
                                trailingIcon = {
                                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = mExpanded)

                                }
                            )


                            DropdownMenu(
                                expanded = mExpanded,
                                onDismissRequest = { mExpanded = false }) {
                                if (galleryList != null && galleryList!!.isNotEmpty()) {
                                    option.clear()
                                    for (data in galleryList!!) {
                                        option.add(data.category!!)
                                    }
                                }
                                option.forEach { selectedOption ->
                                    DropdownMenuItem(
                                        text = { Text(text = selectedOption) },
                                        onClick = {
                                            category = selectedOption
                                            mExpanded = false
                                        },
                                        modifier = Modifier.fillMaxWidth()
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.matchParentSize().padding(10.dp).clickable {
                                mExpanded = !mExpanded
                            })
                        }

                        Spacer(modifier = Modifier.height(5.dp))

                        Image(
                            painter = if (imageUri == null) painterResource(id = R.drawable.placeholderimage)
                            else rememberAsyncImagePainter(model = imageUri),
                            contentDescription = "banner_image",
                            modifier = Modifier
                                .height(220.dp)
                                .fillMaxWidth()
                                .clickable {
                                    launcher.launch("image/*")
                                },
                            contentScale = ContentScale.Crop

                        )



                        Spacer(modifier = Modifier.height(5.dp))



                        Row {
                            Button(
                                onClick = {
//                            if(imageUri==null || title == null || link == null) {
                                    if (imageUri == null) {
                                        Toast.makeText(
                                            context,
                                            "Please Provide Image",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    } else if (category == "") {
                                        Toast.makeText(
                                            context,
                                            "Please Provide All Category",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    } else
                                        galleryViewModel.saveGalleryImage(
                                            imageUri!!,
                                            category,
                                            false
                                        )


                                }, modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f)
                                    .padding(4.dp)
                            ) {
                                Text(text = "Add Image")

                            }

                            OutlinedButton(
                                onClick = {
                                    imageUri = null
                                    isCategory = false
                                    isImage = false
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f)
                                    .padding(4.dp)
                            ) {
                                Text(text = "Cancel")

                            }
                        }

                    }
                }
            LazyColumn {
                items(galleryList ?: emptyList()) {
                    GalleryItemView( it, delete = { docId ->
                        galleryViewModel.deleteGallery(docId)
                    },
                       deleteImage =  {
                           cat,
                           imageUrl ->
                           galleryViewModel.deleteImage(it.category!!,imageUrl)
                       })
                }
            }

//            LazyColumn {
//                items(bannerList) { banner ->
//                    BannerItemView(bannerModel = banner, delete = { docId ->
//                        bannerViewModel.deleteBanner(docId)
//                    })
//                }
//            }

        }
    }
}
