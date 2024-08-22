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
import com.example.collegeapp.itemview.FacultyItemView
import com.example.collegeapp.navigation.Routes
import com.example.collegeapp.ui.theme.Purple40
import com.example.collegeapp.utils.Constant.BANNER
import com.example.collegeapp.viewmodel.FacultyViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ManageFaculty(navController: NavController) {

    val context = LocalContext.current
    val facultyViewModel: FacultyViewModel = FacultyViewModel()
   // val facultyViewModel: FacultyViewModel = viewModel()

    val isUploaded by facultyViewModel.isPosted.observeAsState(false)
    val isDeleted by facultyViewModel.isPosted.observeAsState(false)
    //val bannerList by bannerViewModel.isPosted.observeAsState()
    val categoryList by facultyViewModel.categoryList.observeAsState(null)

    val option = arrayListOf<String>()

    facultyViewModel.getCategory()

    var imageUri by remember {
        mutableStateOf<Uri?>(null)
    }

    var isCategory by remember {
        mutableStateOf(false)
    }

    var mExpanded by remember {
        mutableStateOf(false)
    }

    var isTeacher by remember {
        mutableStateOf(false)
    }

    var name by remember {
        mutableStateOf("")
    }

    var email by remember {
        mutableStateOf("")
    }

    var position by remember {
        mutableStateOf("")
    }

    var mobile by remember {
        mutableStateOf("")
    }

    var category by remember {
        mutableStateOf("")
    }

    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) {
            imageUri = it
        }


    LaunchedEffect(isUploaded) {
        if (isUploaded) {
            Toast.makeText(context, "Data Added", Toast.LENGTH_SHORT).show()
            imageUri = null
            isCategory = false
            isTeacher = false
            category = ""
            name = ""
            email = ""
            mobile = ""
            position = ""
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
                Text(text = "Manage Faculty", color = Color.White)
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
                        isTeacher = false
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
                        isTeacher = true
                    }) {
                    Text(text = "Add Teacher",
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

                    Row {
                        Button(
                            onClick = {
//                            if(imageUri==null || title == null || link == null) {
                                if(category == ""){
                                    Toast.makeText(
                                        context,
                                        "Please Provide Category",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                                else
                                    facultyViewModel.uploadCategory(category)


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
                                isTeacher = false
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

            if (isTeacher)
                ElevatedCard(modifier = Modifier.padding(8.dp)) {


                    Column(horizontalAlignment = Alignment.CenterHorizontally){

                        Spacer(modifier = Modifier.height(5.dp))

                    Image(
                        painter = if (imageUri == null) painterResource(id = R.drawable.placeholderimage)
                        else rememberAsyncImagePainter(model = imageUri),
                        contentDescription = BANNER,
                        modifier = Modifier
                            .height(120.dp)
                            .width(120.dp)
                            .clip(CircleShape)
                            .clickable {
                                launcher.launch("image/*")
                            },
                        contentScale = ContentScale.Crop

                    )

                        Spacer(modifier = Modifier.height(5.dp))

                    OutlinedTextField(
                        value = name,
                        onValueChange = {
                            name = it
                        },
                        placeholder = { Text(text = "Name...") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(4.dp)
                    )


                        Spacer(modifier = Modifier.height(5.dp))


                    OutlinedTextField(
                        value = email,
                        onValueChange = {
                            email = it
                        },
                        placeholder = { Text(text = "Email...") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(4.dp)
                    )

                        Spacer(modifier = Modifier.height(5.dp))


                    OutlinedTextField(
                        value = mobile,
                        onValueChange = {
                            mobile = it
                        },
                        placeholder = { Text(text = "Mobile...") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(4.dp)
                    )

                        Spacer(modifier = Modifier.height(5.dp))

                    OutlinedTextField(
                        value = position,
                        onValueChange = {
                            position = it
                        },
                        placeholder = { Text(text = "Designation...") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(4.dp)
                    )

                        Spacer(modifier = Modifier.height(5.dp))

                        Box(modifier = Modifier.wrapContentSize(Alignment.TopStart)) {

                            OutlinedTextField(
                                value = category,
                                onValueChange = {
                                    category = it
                                },
                                readOnly = true,
                                placeholder = { Text(text = "Select Department") },
                                label = { Text(text = "Department") },
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
                                if (categoryList != null && categoryList!!.isNotEmpty()) {
                                    option.clear()
                                    for (data in categoryList!!) {
                                        option.add(data)
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
                                } else if (name == "" || email == "" || mobile == "" || position == "" || category == "") {
                                    Toast.makeText(
                                        context,
                                        "Please Provide All Fields",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                } else
                                    facultyViewModel.saveFaculty(
                                        imageUri!!,
                                        name,
                                        email,
                                        position,
                                        mobile,
                                        category
                                    )


                            }, modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)
                                .padding(4.dp)
                        ) {
                            Text(text = "Add Teacher")

                        }

                        OutlinedButton(
                            onClick = {
                                imageUri = null
                                isCategory = false
                                isTeacher = false
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
                items(categoryList ?: emptyList()) {
                    FacultyItemView( it, delete = { docId ->
                        facultyViewModel.deleteCategory(docId)
                    },
                        onClick = { categoryname ->
                            val routes=Routes.FacultyDetailScreen.route.replace("{catName}" , categoryname)
                            navController.navigate(routes)

                        }  )
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
