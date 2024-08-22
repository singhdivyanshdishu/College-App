package com.example.collegeapp.models

import androidx.compose.ui.graphics.vector.ImageVector
import com.example.collegeapp.navigation.Routes

data class NavItem(
    val title: String,
    val icon: Int

    )

data class BottomNavItem(
    val title: String,
    val icon: Int,
    val routes: String
)