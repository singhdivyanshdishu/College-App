package com.example.collegeapp.models

data class FacultyModel(
    val imageUrl: String? =  "",
    val name: String? = "",
    val email: String? = "" ,
    val position: String? = "" ,//designation
    val mobile: String? = "" ,
    val catName : String?="",
    val docId : String? = ""
)