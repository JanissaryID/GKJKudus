package com.example.gkjkudus

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.gkjkudus.navigation.Pages

var PAGE_STATE: Int by mutableStateOf(0)
var PAGE_ROUTE: String by mutableStateOf(Pages.Home.route)
var ITEM_CODE: String by mutableStateOf("")
var STAT_SCAN: Boolean by mutableStateOf(false)