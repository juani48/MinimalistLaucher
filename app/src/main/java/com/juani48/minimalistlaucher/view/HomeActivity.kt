package com.juani48.minimalistlaucher.view

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.juani48.minimalistlaucher.R
import com.juani48.minimalistlaucher.viewmodel.AppObject
import com.juani48.minimalistlaucher.viewmodel.AppViewModel
import kotlinx.coroutines.delay
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format
import kotlinx.datetime.format.char
import kotlinx.datetime.toLocalDateTime

@Composable
fun HomeActivity(context: Context, appViewModel: AppViewModel){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.background))
    ){
        SetContent(context, appViewModel)
    }

}

@Composable
fun SetContent(context: Context, appViewModel: AppViewModel){
    var list by remember { mutableStateOf(appViewModel.loadInstalledApps(context)) }
    var text by remember { mutableStateOf("") }

    Column(
        Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp, vertical = 60.dp)
    ) {
        Date()

        Row (
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = text,
                onValueChange = {text = it; list = appViewModel.searchApp(text)},
                label = { Text("Search") },
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = colorResource(R.color.background),
                    unfocusedContainerColor = colorResource(R.color.background),
                    focusedIndicatorColor = colorResource(R.color.fronground),
                    cursorColor = colorResource(R.color.fronground),
                    focusedTextColor = colorResource(R.color.text),
                    focusedLabelColor = colorResource(R.color.fronground),
                    unfocusedLabelColor = colorResource(R.color.fronground),
                    unfocusedTextColor = colorResource(R.color.text)
                ),
            )
        }

        LazyColumn(
            Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            items(list){
                    item -> AppItem(item, { appViewModel.launchApp(context, item); text = "" })
            }
        }
    }
}

@Composable
fun Date(){
    val zone = TimeZone.currentSystemDefault()
    var time by remember { mutableStateOf("") }

    LaunchedEffect(true) {
        while (true) {
            time = Clock.System.now()
                .toLocalDateTime(zone)
                .format(
                    LocalDateTime
                        .Format {
                            hour()
                            char(':')
                            minute()
                            char(':')
                            second()
                        }
                )
            delay(1000L)
        }
    }

    Box(
        Modifier
            .fillMaxWidth()
            .padding(all = 10.dp)
    ){
        Text(
            modifier = Modifier.align(Alignment.Center),
            fontSize = 50.sp,
            text = time,
            color = colorResource(R.color.text),
        )
    }
}

@Composable
fun AppItem(item: AppObject, onClick: () -> Unit){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 10.dp)
            .clickable { onClick() }
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = item.getName(),
            fontSize = 30.sp,
            color = colorResource(R.color.text),

        )
    }
}