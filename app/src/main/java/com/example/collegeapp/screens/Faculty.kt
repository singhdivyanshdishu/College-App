package com.example.collegeapp.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavController
import com.example.collegeapp.itemview.FacultyItemView
import com.example.collegeapp.navigation.Routes
import com.example.collegeapp.viewmodel.FacultyViewModel

@Composable
fun Faculty(navController: NavController){
    val facultyViewModel: FacultyViewModel = FacultyViewModel()
    val categoryList by facultyViewModel.categoryList.observeAsState(null)
    facultyViewModel.getCategory()


    LazyColumn {
        items(categoryList ?: emptyList()) {
            FacultyItemView( it, delete = { docId ->
                facultyViewModel.deleteCategory(docId)
            },
                onClick = { categoryname ->
                    val routes= Routes.FacultyDetailScreen.route.replace("{catName}" , categoryname)
                    navController.navigate(routes)

                }  )
        }
    }


}

